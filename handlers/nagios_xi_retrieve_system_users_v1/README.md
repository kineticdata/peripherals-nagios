== Nagios XI Retrieve Users V1

=== Detailed Description  
This handler retrieves user records from Nagios XI.  
[API Documentation](https://your.nagiosserver.com/nagiosxi/help/api-system-reference.php#user)  

=== Parameters
[error handling]  
  Determine what to return if an error is encountered.  

[return type]  
  The format that the results should be returned in.  

[user id]  
  Optional. User id of the user to retrieve.  
  

=== Sample Configuration  
  
error handling: Error Message
  
return type: JSON
  
user id: 
  
  
=== Sample Configuration  
  
error handling: Error Message
  
return type: XML
  
user id: 1
  
  
  
=== Results  
[Handler Error Message]  
  Error message if set to return - blank if no errors.  

[Nagios XI Response]  
  Nagios response if set to return.  
