== Nagios XI Create User V1

=== Detailed Description  
This handler creates a new Nagios XI user object.
[API Documentation](https://yournagiosxiserver.com/nagiosxi/help/api-config-reference.php#add-user)  

=== Parameters  
[error handling]  
  Required. Determine what to return if an error is encountered. Valid values: Error Message or Raise Error
  
[return_response]  
  Required. Determines if Kinetic Task sets the Nagios XI Response output. Valid values: true or false
  
[username]  
  New user username.
  
[password]  
  Password for the new user.
  
[name]  
  Name of the new user.
  
[email]  
  Email address of the new user.
  
[force password change]  
  Determines if user must change password on first login. Default is 1 (yes)
  
[email info]  
  Send e-mail to user with their new account information. Default is 1 (yes)
  
[monitoring contact]  
  Determines if a monitoring contact is made for this user. Default is 1 (yes)
  
[enable notifications]  
  Determines if the user will get notifications. Default is 1 (yes)
  
[language]  
  Sets the users preferred language. Default is xi default.
  
[date format]  
  Sets the users preferred date format. Default is 1 (YYYY-MM-DD)
  
[number format]  
  Sets the users preferred number format. Default is 1 (1000.00)
  
[authorization level]  
  Sets the users authorization level. Default is user. Other option is admin.
  
[can see all objects]  
  Allows a user to view all objects that are configured no matter what what contact group they are in or what objects they are contacts of. Default value is 0 (no).
  
[can control all hosts and services]  
  Allows a user to configure - acknowledge problems, schedule downtime, toggle notifications and force checks on all objects. Default value is 0 (no).
  
[can reconfigure all hosts and services]  
  Allows a user to be able to re-configure a host/service from the status screen under by using the re-configure option to set contacts, update check intervals, amount of checks for hard state, etc. Default value is 0 (no).
  
[can control engine]  
  Allows a user to restart the Nagios Core engine. Default value is 0 (no).
  
[can use advanced features]  
  Allows the user to see the CCM links. Shows the check_command in the re-configure host/service page. Shows advanced tab with advanced commands in the host/service detail pages. Allows setting parents during wizards and re-configuration. Default value is 0 (no).
  
[read only]  
  Set if the user only has read access. Default value is 0 (no).
  
[apply config]  
  Required. 1 or 0 for true or false.
  By default the Nagios Core service is not restarted when an object is created or deleted via the API. 
  You may change the default behavior by setting this parameter to 1 which will import the data into your configuration and will restart the Nagios Core service.


=== Sample Configuration

error handling: Raise Error
  
return response: yes
  
username: jmcdouglas
  
password: test
  
name: Jordan McDouglas
  
email: jmcdouglas@localhost
  
force password change: 
  
email info: 
  
monitoring contact: 
  
enable notifications: 
  
language: 
  
date format: 
  
number format: 
  
authorization level: 
  
can see all objects: 
  
can control all hosts and services: 
  
can reconfigure all hosts and services: 
  
can control engine: 
  
can use advanced features: 
  
read only: 
  
=== Results  
[Handler Error Message]  
  Error message if set to return - blank if no errors.  

[Nagios XI Response]  
  Nagios response if set to return.  

