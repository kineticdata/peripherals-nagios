== Nagios XI Create Servicegroup V1

=== Detailed Description  
This handler creates a new servicegroup object.  
[API Documentation](https://yournagiosxiserver.com/nagiosxi/help/api-config-reference.php#add-servicegroup)  

=== Parameters  
[error handling]  
  Required. Determine what to return if an error is encountered.  

[return response]  
  Required. true or false. Determines if Kinetic Task returns the Nagios response output.  

[servicegroup name]  
  Required. New servicegroup name.  
  
[alias]
  Required.  

[apply config]
  Required. 1 or 0 for true or false.  
  By default the Nagios Core service is not restarted when an object is created or deleted via the API.  
  You may change the default behavior by setting this parameter to 1 which will import the data into your configuration and will restart the Nagios Core service.


=== Sample Configuration

error handling: Raise Error

return response: yes

servicegroup name: testapiservicegroup

alias: 127.0.0.1
  
apply config: 0
  
  
=== Results
[Handler Error Message]
  Error message if set to return - blank if no errors.  

[Nagios XI Response]
  Nagios response if set to return.
