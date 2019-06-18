== Nagios XI Create Host V1

=== Detailed Description
This handler creates a new host object.
[API Documentation](https://yournagiosxiserver.com/nagiosxi/help/api-config-reference.php#add-host)  

=== Parameters
[host name]
  Required. Host name to apply to the new host
  
[address]
  Required. IP address of the new host
  

[max check attempts]
  Required. Max number of attempts to try adding the host.
  

[check period]
  Required. Time period name for when checks take place for this host.
  

[contacts]
  Optional - sort of. Nagios usernames to notify for alerts with this host. Comma seperated. Use this parameter **or** the contact groups parameter.
  

[contact groups]
  Optional - sort of. Nagios group names to notify for alerts with this host. Comma seperated. Use this parameter **or** the contacts parameter.
  

[notification interval]
  Required. Interval check period in minutes for alert notifications on this host.
  

[notification period]
  Required. 
  

[apply config]
  Required. 1 or 0 for true or false.
  By default the Nagios Core service is not restarted when an object is created or deleted via the API. 
  You may change the default behavior by setting this parameter to 1 which will import the data into your configuration and will restart the Nagios Core service.

  
=== Results
[Handler Error Message]
  Error message if set to return - blank if no errors.  

[Nagios XI Response]
  Nagios response if set to return.  

