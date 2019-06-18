{
  'info' => {
    'api_server' => 'https://your.nagiosserver.com/',
    'api_key' => '',
    'enable_debug_logging' => 'yes'
  },
  'parameters' => {
    'error_handling'    => 'Error Message',
    'object_type'       => 'contactgroupmembers',
    'return_type'       => 'JSON',
    'starttime'         => '',
    'endtime'           => '',
    'records'           => '',
    'orderby'           => '',
    'columns'           => {
      'last_notification' => ['lt:1970-12-31 18:00:00', 'gt:1967-12-31 18:00:00']
    }.to_json,
  }
}
