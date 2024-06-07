package com.modakdev.analysis_wrapper_module.api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

@Component
public class AnalysisModuleClient {

    private final RestTemplate restTemplate;

    private final String baseUrl;

    public AnalysisModuleClient(@Value("${flask.base-url}") String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;
    }

    public Object getSingleQueryResponse(Object requestData){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestData, headers);

        String requestUrl = baseUrl + "/chat-single";
        ResponseEntity<Object> response = restTemplate.postForEntity(requestUrl, requestEntity, Object.class);
        return response.getBody();
    }
}
