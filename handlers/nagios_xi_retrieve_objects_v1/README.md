== Nagios XI Retrieve Objects V1

=== Detailed Description  
This handler retrieves object records from Nagios XI.  
[API Documentation](https://your.nagiosserver.com/nagiosxi/help/api-object-reference.php)  

=== Parameters
[error handling]  
  Determine what to return if an error is encountered.  

[return type]  
  The format that the results should be returned in.  

[object type]  
  The type of the object to retrieve.  

[start time]  
  Optional. Epoch start time for object record creation. Defaults to 24 hours from the current time.  

[end time]  
  Optional. Epoch end time for object record creation. Defaults to now.  

[records]  
  Optional. Amount of records and optionally starting at. 10:20 Displays the the next 10 records after the 20th record. Defaults to all.  

[order by]  
  Optional. Specifies a column to sort on along with sort type. Example: name:d  

[columns]  
  Optional. Simple JSON object that has Nagios object reocrd columns as keys and equality expressions as values in either string or array format. See below for examples.   
  The keys available to use will vary depending on the object type. For example, comment_time is a valid key when the object type is comment but not when the object type is host. When the object type is host, check_interval is a valid key but not for when the object type is comment.  

  The Nagios XI API documention page specifies which columns are available for each object type under the example returned response for each object type.  

  Column qualifications are OR concatenated.  e.g. {'is_active': ['1', '0]} would result in is_active = 1 OR is_active = 0  
  
  

=== Sample Configuration  
  
error handling: Error Message
  
return type: JSON
  
object type: comment
  
start time: 
  
end time: 
  
records: 10
  
order by: comment_time:d
  
columns: <%= {"comment_time" => 'gt:2017-12-01 00:00:00'}.to_json %>
  
  

=== Sample Configuration  
  
error handling: Error Message
  
return type: XML
  
object type: contact
  
start time: 
  
end time: 
  
records: 10:200
  
order by: 
  
columns: <%= {"is_active" => "1"}.to_json %>
  
  
  
The following sample is for Kinetic Task 3 when JSON was not natively included in the Task engine.  
  
=== Sample Configuration  
  
error handling: Error Message
  
return type: JSON
  
object type: service
  
start time: 
  
end time: 
  
records: 
  
order by: 
  
columns: {"service_description": <%= "lk:#{@results['some echo node']['output']}".inspect } %>}
  
  
  
=== Results  
[Handler Error Message]  
  Error message if set to return - blank if no errors.  

[Nagios XI Response]  
  Nagios response if set to return.  
