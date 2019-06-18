== Nagios XI Apply Config V1

=== Detailed Description  
This handler runs the apply config command which triggers a Nagios Core restart.  
[API Documentation](https://yournagiosxiserver.com/nagiosxi/help/api-system-reference.php#system-applyconfig)  

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
