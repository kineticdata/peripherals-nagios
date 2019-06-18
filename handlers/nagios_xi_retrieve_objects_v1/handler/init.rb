# Require the dependencies file to load the vendor libraries
require File.expand_path(File.join(File.dirname(__FILE__), "dependencies"))

class NagiosXiRetrieveObjectsV1
  # Prepare for execution by building Hash objects for necessary values and
  # validating the present state.  This method sets the following instance
  # variables:
  # * @input_document - A REXML::Document object that represents the input Xml.
  # * @info_values - A Hash of info names to info values.
  # * @parameters - A Hash of parameter names to parameter values.
  #
  # This is a required method that is automatically called by the Kinetic Task
  # Engine.
  #
  # ==== Parameters
  # * +input+ - The String of Xml that was built by evaluating the node.xml
  #   handler template.
  def initialize(input)
    
    # Retrieve all of the handler info values and store them in a hash variable named @info_values.
    @info_values = {}
    
    # Set the input document attribute
    @input_document = REXML::Document.new(input)
    REXML::XPath.each(@input_document, "/handler/infos/info") do |item|
      @info_values[item.attributes["name"]] = item.text.to_s.strip
    end

    @enable_debug_logging = @info_values['enable_debug_logging'].strip.downcase == 'yes'

    # Retrieve all of the handler parameters and store them in a hash variable named @parameters.
    @parameters = {}
    REXML::XPath.each(@input_document, "/handler/parameters/parameter") do |item|
      @parameters[item.attributes["name"]] = item.text.to_s.strip
    end

    # Retrieve all of the handler parameters and store them in a hash variable named @parameters.
    @url_parameters = {}
    REXML::XPath.each(@input_document, "/handler/api/urlParameters/parameter") do |item|
      if item.text.to_s.strip.empty? == false
        @url_parameters[item.attributes["name"]] = item.text.to_s.strip 
      end
    end

    @column_qualifiers = {}
    @column_qualifiers = JSON.parse(@parameters['columns'].to_s) if @parameters['columns'].to_s.strip.empty? == false

    @api_route = REXML::XPath.first(@input_document, "/handler/api/route").text
    
  end

  # The execute method gets called by the task engine when the handler's node is processed. It is
  # responsible for performing whatever action the name indicates.
  # If it returns a result, it will be in a special XML format that the task engine expects. These
  # results will then be available to subsequent tasks in the process.
  def execute()
    return_response = @parameters['return_response'].to_s
    api_server  = @info_values["api_server"]
    api_address = "#{api_server.chomp("/")}"
    api_address << "#{@api_route}/#{@parameters['object_type']}?"

    @url_parameters.each do |key, value|
      api_address << "#{key}=#{URI.encode(value)}&"
    end
    @column_qualifiers.each do |key,value|
      if value.is_a?(Array) then
        value.each do |qualifier|
          api_address << "#{key}=#{URI.encode(qualifier)}&"
        end
      elsif value.is_a?(String) then
        api_address << "#{key}=#{URI.encode(value)}&"
      end
    end

    api_address.chomp!("&")

    puts "API URL: #{api_address}" if @enable_debug_logging

    response = RestClient::Request.execute(
      method: :get, 
      url: api_address,
      headers: {accept: :json}
    )

    # Default, JSON
    formatted_response = response.body

    if @parameters['return_type'] == "XML" then
      formatted_response = convert_json_to_xml(JSON.parse(response.body))
    elsif @parameters['return_type'] == "YAML"
      formatted_response =  YAML::dump(
        JSON.parse(response.body)
      )
    end

    # Build the results to be returned by this handler
    <<-RESULTS
    <results>
      <result name="Handler Error Message"></result>
      <result name="Nagios XI Response">#{escape(formatted_response)}</result>
    </results>
    RESULTS

    rescue RestClient::Exception => error
      error_message = JSON.parse(error.response)
      if error_handling == "Raise Error"
        raise error_message
      else
        <<-RESULTS
        <results>
          <result name="Handler Error Message">#{error.http_code}: #{escape(error_message)}</result>
          <result name="Nagios XI Response"></result>
        </results>
        RESULTS
      end
  end

  ##############################################################################
  # General handler utility functions
  ##############################################################################

  # This method converts a Ruby JSON Hash to a REXML::Element object.  The REXML::Element
  # that is returned is the root node of the XML structure and all of the resulting
  # XML data will be nested within that single element.
  def convert_json_to_xml(data, label=nil)
    if data.is_a?(Hash)
      element = REXML::Element.new("node")
      element.add_attribute("type", "Object")
      element.add_attribute("name", label) if label
      data.keys.each do |key|
        element.add_element(convert_json_to_xml(data[key], key))
      end
      element
    elsif data.is_a?(Array)
      element = REXML::Element.new("node")
      element.add_attribute("type", "Array")
      element.add_attribute("name", label) if label
      data.each do |child_data|
        element.add_element(convert_json_to_xml(child_data))
      end
      element
    else
      element = REXML::Element.new("node")
      element.add_attribute("type", data.class.name)
      element.add_attribute("name", label) if label
      element.add_text(data.to_s)
      element
    end
  end

  # This is a template method that is used to escape results values (returned in
  # execute) that would cause the XML to be invalid.  This method is not
  # necessary if values do not contain character that have special meaning in
  # XML (&, ", <, and >), however it is a good practice to use it for all return
  # variable results in case the value could include one of those characters in
  # the future.  This method can be copied and reused between handlers.
  def escape(string)
    # Globally replace characters based on the ESCAPE_CHARACTERS constant
    string.to_s.gsub(/[&"><]/) { |special| ESCAPE_CHARACTERS[special] } if string
  end
  # This is a ruby constant that is used by the escape method
  ESCAPE_CHARACTERS = {'&'=>'&amp;', '>'=>'&gt;', '<'=>'&lt;', '"' => '&quot;'}
end
