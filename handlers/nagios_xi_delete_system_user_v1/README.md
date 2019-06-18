== Nagios XI Delete User V1

=== Detailed Description
This handler deletes a user object.  
[API Documentation](https://yournagiosxiserver.com/nagiosxi/help/api-system-reference.php#delete-user)  

=== Parameters
[error handling]  
  Required. Determine what to return if an error is encountered.  

[return response]  
  Required. true or false. Determines if Kinetic Task returns the Nagios response output.  

[user id]
  Required. User id, not name, to delete. This is a number value.
  
=== Sample Configuration  

error handling: Raise Error  

return response: yes  

user id: 2
  
    
=== Results  
[Handler Error Message]  
  Error message if set to return - blank if no errors.  

[Nagios XI Response]  
  Nagios response if set to return.  
