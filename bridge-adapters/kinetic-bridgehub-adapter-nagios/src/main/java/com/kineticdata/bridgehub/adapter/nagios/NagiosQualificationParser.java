package com.kineticdata.bridgehub.adapter.nagios;

import com.kineticdata.bridgehub.adapter.QualificationParser;
import java.net.URLEncoder;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NagiosQualificationParser extends QualificationParser {
    
    public static final Map<String, String> jsonPathMapping = Collections
        .unmodifiableMap(
            Stream.of(
                new SimpleEntry<>("objects/hoststatus", "$.hostsatuslist.hoststatus"),
                new SimpleEntry<>("objects/servicestatus", "$.servicestatuslist.servicestatus"),
                new SimpleEntry<>("objects/logentries", "$.logentries.logentry"),
                new SimpleEntry<>("objects/statehistory", "$.statehistory.stateentry"),
                new SimpleEntry<>("objects/comment", "$.comments.comment"),
                new SimpleEntry<>("objects/downtime", "$.scheduleddowntimelist.scheduleddowntime"),
                new SimpleEntry<>("objects/contact", "$.contactlist.contact"),
                new SimpleEntry<>("objects/host", "$.hostlist.host"),
                new SimpleEntry<>("objects/service", "$.servicelist.service"),
                new SimpleEntry<>("objects/hostgroup", "$.hostgrouplist.hostgroup"),
                new SimpleEntry<>("objects/servicegroup", "$.servicegrouplist.servicegroup"),
                new SimpleEntry<>("objects/contactgroup", "$.contactgrouplist.contactgroup"),
                new SimpleEntry<>("objects/hostgroupmembers", "$.hostgrouplist.hostgroup"),
                new SimpleEntry<>("objects/servicegroupmembers", "$.servicegrouplist.servicegroup"),
                new SimpleEntry<>("objects/contactgroupmembers", "$.contactgrouplist.contactgroup")
            ).collect(
                Collectors.toMap(
                    (e) -> e.getKey(),
                    (e) -> e.getValue()
                )
            )
        );
    
    @Override
    public String encodeParameter(String name, String value) {
        String result = null;
        if (value != null) {
            result = URLEncoder.encode(value);
        }
        return result;
    }
}
