== Nagios XI Delete Service V1

=== Detailed Description  
This handler deletes a service object.  
[API Documentation](https://yournagiosxiserver.com/nagiosxi/help/api-config-reference.php#delete-service)  

=== Parameters  
[error handling]  
  Required. Determine what to return if an error is encountered.  

[return response]  
  Required. true or false. Determines if Kinetic Task returns the Nagios response output.  

[host name]  
  Required. Service name associated with the service  
  
[service description]  
  Required. Service description to be removed.  

[apply config]  
  Required. 1 or 0 for true or false.  
  By default the Nagios Core service is not restarted when an object is created or deleted via the API.  
  You may change the default behavior by setting this parameter to 1 which will import the data into your configuration and will restart the Nagios Core service.  



=== Sample Configuration  

error handling: Raise Error  

return response: yes  

host name: testapihost  

service description: PING  
  
apply config: 0  
  
  
=== Results  
[Handler Error Message]  
  Error message if set to return - blank if no errors.  

[Nagios XI Response]  
  Nagios response if set to return.
