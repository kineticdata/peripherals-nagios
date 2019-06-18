{
  'info' => {
    'api_server' => 'https://nagios.yourdomain.com/',
    'api_key' => '',
  },
  'parameters' => {
    'error_handling'   => 'Error Message',
    'return_response'  => 'yes',

    'host_name'             => 'localhost',
    'service_description'   => 'API Test - Can delete',
    'check_command'         => 'check_ping\!3000,80%\!5000,100%',
    'max_check_attempts'    => '5',
    'check_interval'        => '5',
    'check_period'          => '24x7',
    'retry_interval'        => '1',
    'contacts'              => 'nagiosadmin',
    'contact_groups'        => '',
    'notification_interval' => '5',
    'notification_period'   => '24x7',
    'applyconfig'           => '0'
  }
}
