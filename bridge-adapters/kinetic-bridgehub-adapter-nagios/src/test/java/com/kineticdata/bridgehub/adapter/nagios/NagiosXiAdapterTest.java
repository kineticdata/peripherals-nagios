/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kineticdata.bridgehub.adapter.nagios;

import com.kineticdata.bridgehub.adapter.BridgeError;
import com.kineticdata.bridgehub.adapter.BridgeRequest;
import com.kineticdata.bridgehub.adapter.Count;
import com.kineticdata.bridgehub.adapter.Record;
import com.kineticdata.bridgehub.adapter.RecordList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class NagiosXiAdapterTest {
    
    private final String BRIDGE_ERROR_MSG_INVALID_KEY = "The Nagios XI server responding with the following API error message: Invalid API Key";
    
    private final String NAGIOS_URL = "http://127.0.0.1/nagiosxi/";
    private final String API_KEY = "REPLACE_API_KEY";
    private StringBuilder expectedUrl;
    private NagiosQualificationParser parser;
    private NagiosXiAdapter bridgeAdapter;
    private BridgeRequest bridgeRequest;
    private Map<String, String> bridgeConfig;
    private Map<String, String> bridgeMetadata;
    private Map<String, String> bridgeParameters;
    private String bridgeStructure = "objects/service";
    private String bridgeQuery = "";
    
    @Before
    public void setVariables(){
        expectedUrl = new StringBuilder();
        expectedUrl
            .append(NAGIOS_URL)
            .append("api/v1/");
        
        parser = new NagiosQualificationParser();
        
        bridgeAdapter = new NagiosXiAdapter();
        bridgeConfig = new HashMap();
        bridgeConfig.put("API Key", API_KEY);
        bridgeConfig.put("Nagios XI URL", NAGIOS_URL);
        bridgeMetadata = new HashMap();
        bridgeParameters = new HashMap();
        bridgeQuery = "";
        bridgeRequest = new BridgeRequest();
        
        bridgeAdapter.setProperties(bridgeConfig);
        
    }
    
    @Test
    public void test_e2e_initializaAuthPass() throws Exception {
        bridgeAdapter.initialize();
    }
    
    @Test
    public void test_e2e_initializaAuthFail() throws Exception {
        bridgeConfig.put("API Key", "");
        bridgeAdapter.setProperties(bridgeConfig);
        String apiError = null;
        
        try {
            bridgeAdapter.initialize();
        } catch (BridgeError err) {
            apiError = err.getMessage();
        }
        
        assertEquals(BRIDGE_ERROR_MSG_INVALID_KEY, apiError);
    }
    
    @Test
    public void test_e2e_verifyCountResult() throws Exception {
        
        bridgeAdapter.initialize();
        bridgeQuery = "address=127.0.0.1";
        bridgeStructure = "objects/host";
        setBridgeRequest();
        
        Count bridgeCount = bridgeAdapter.count(bridgeRequest);
        
        assertEquals(
            Integer.valueOf(1),
            bridgeCount.getValue()
        );
        
    }
    
    @Test
    public void test_e2e_verifyRetrieveResult() throws Exception {
        
        bridgeAdapter.initialize();
        bridgeQuery = "address=127.0.0.1";
        bridgeStructure = "objects/host";
        setBridgeRequest();
        bridgeRequest.setFields(
            Arrays.asList(
                "address"
            )
        );
        
        Record bridgeRecord = bridgeAdapter.retrieve(bridgeRequest);
        
        assertEquals(
            "127.0.0.1",
            bridgeRecord.getValue("address")
        );
        
    }
    
    @Test
    public void test_e2e_verifySearchResult() throws Exception {
        
        bridgeAdapter.initialize();
        bridgeStructure = "objects/service";
        setBridgeRequest();
        bridgeRequest.setFields(
            Arrays.asList(
                "host_name",
                "service_description"
            )
        );
        
        RecordList bridgeRecords = bridgeAdapter.search(bridgeRequest);
        
        assertTrue(bridgeRecords.getRecords().size() > 1);
        
    }
    
    @Test
    public void test_parametersUrlEncoded() throws Exception {
        String serviceName = "This & that. Right?";
        assertEquals(
            "This+%26+that.+Right%3F", 
            parser.encodeParameter("service name", serviceName)
        );
    }
    
    @Test
    public void test_urlPathVerifyCount() throws Exception {
        
        bridgeAdapter.initialize_noAuth();
        bridgeQuery = "name=<%= parameter[\"Parameter 1\"] %>&name=<%= parameter[\"Parameter 2\"] %>&starttime=<%= parameter[\"Parameter 3\"] %>";
        bridgeParameters.put("Parameter 1", "Parameter value 1");
        bridgeParameters.put("Parameter 2", "Parameter value 2");
        bridgeParameters.put("Parameter 3", "Parameter value 3");
        
        setBridgeRequest();
        setExpectedUrl();
        
        expectedUrl.append(
            "&name=Parameter+value+1&name=Parameter+value+2&starttime=Parameter+value+3"
        );
        
        assertEquals(
            expectedUrl.toString(),
            bridgeAdapter.buildUrl("count", bridgeRequest, parser)
        );
    }
    

    /*
    * PRIVATE HELPER METHODS
    */
    private void setExpectedUrl() {
        expectedUrl
            .append(bridgeStructure)
            .append(String.format("?apikey=%s", API_KEY));
    }
    
    private void setBridgeRequest() {
        bridgeRequest.setQuery(bridgeQuery);
        bridgeRequest.setStructure(bridgeStructure);
        bridgeRequest.setParameters(bridgeParameters);
        bridgeRequest.setMetadata(bridgeMetadata);
    }
}
