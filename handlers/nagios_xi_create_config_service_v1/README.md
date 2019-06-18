== Nagios XI Create Service V1  

=== Detailed Description  
This handler creates a new service object.  
[API Documentation](https://yournagiosxiserver.com/nagiosxi/help/api-config-reference.php#add-service)  

=== Parameters  
[error handling]
  Required. Determine what to return if an error is encountered. Valid values: Error Message or Raise Error
  
[return_response]
  Required. Determines if Kinetic Task sets the Nagios XI Response output. Valid values: true or false
  
[host name]  
  Required. Host name to apply to the new service with  
  
[service description]  
  Required. Service description for the new service  
  
[check command]  
  Required. The command to run with command parameters.  
  
[max check attempts]  
  Required. Max number of attempts for retrying the service command to revalidate.  
  
[check interval]  
  Required. Time in minutes for how often the service is checked.  
  
[check period]  
  Required. The time period name for when the monitoring occurs.  
  
[retry interval]  
  Required. Time in minutes for how often the service is checked after a failed check.  
  
[contacts]  
  Optional - sort of. Nagios usernames to notify for alerts with this service. Comma seperated. Use this parameter **or** the contact groups parameter.  
  
[contact groups]  
  Optional - sort of. Nagios group names to notify for alerts with this service. Comma seperated. Use this parameter **or** the contacts parameter.  
  
[notification interval]  
  Required. Interval check period in minutes for alert notifications on this service.  
  
[notification period]  
  Required. Time period name in which the notifications will only take place.  
  
[apply config]  
  Required. 1 or 0 for true or false.  
  By default the Nagios Core service is not restarted when an object is created or deleted via the API.  
  You may change the default behavior by setting this parameter to 1 which will import the data into your configuration and will restart the Nagios Core service.  

=== Sample Configuration  

error handling: Raise Error
  
return response: yes
  
host name: testapihostapply
  
service description: PING
  
check command: check_ping\!3000,80%\!5000,100%
  
max check attempts: 2
  
check interval: 5
  
check period: 24x7
  
retry interval: 5
  
contacts: nagiosadmin
  
contact groups:
  
notification interval: 5
  
notification period: 24x7
  
apply config: 0
  
=== Results  
[Handler Error Message]  
  Error message if set to return - blank if no errors.  

[Nagios XI Response]  
  Nagios response if set to return.  

