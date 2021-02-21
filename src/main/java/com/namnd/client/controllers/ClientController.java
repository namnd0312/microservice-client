package com.namnd.client.controllers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author nam.nd
 * @created 21/02/2021 - 3:19 PM
 */

@RestController
public class ClientController {
    @Autowired
    private EurekaClient client;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @RequestMapping("/getHello")
    public String callService(){
        InstanceInfo instance = client.getNextServerFromEureka("service-1", false);
        String url = instance.getHomePageUrl();

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return response.getBody();
    }
}
