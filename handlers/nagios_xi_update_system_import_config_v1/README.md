== Nagios XI Import Config V1

=== Detailed Description  
This handler runs the import command which imports all configuration files into the CCM. Nagios Core is **not** restarted.  
[API Documentation](https://yournagiosxiserver.com/nagiosxi/help/api-system-reference.php#system-importconfig)  

=== Parameters
[error handling]  
  Required. Determine what to return if an error is encountered.  

[return response]  
  Required. true or false. Determines if Kinetic Task returns the Nagios response output.  

=== Sample Configuration  
  
error handling: Raise Error
  
return response: yes
  
  
=== Results  
[Handler Error Message]  
  Error message if set to return - blank if no errors.  

[Nagios XI Response]  
  Nagios response if set to return.  
