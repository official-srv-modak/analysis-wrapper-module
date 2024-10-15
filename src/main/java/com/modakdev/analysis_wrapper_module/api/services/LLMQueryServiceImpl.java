package com.modakdev.analysis_wrapper_module.api.services;

import com.modakdev.analysis_wrapper_module.api.client.AnalysisModuleClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import com.modakdev.response.MDBaseResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.LinkedHashMap;

import static com.modakdev.analysis_wrapper_module.LibraryFunctions.convertToJSONObject;

@Service
public class LLMQueryServiceImpl implements LLMQueryService{

    private final AnalysisModuleClient analysisModuleClient;
    private final WebClient webClient;


    @Autowired
    public LLMQueryServiceImpl(AnalysisModuleClient analysisModuleClient, @Value("${flask.base-url}") String baseUrl, WebClient.Builder webClientBuilder) {
        this.analysisModuleClient = analysisModuleClient;
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();    }

    @Override
    public MDBaseResponse getSingleQueryResponse(String query) {
        MDBaseResponse baseResponse = new MDBaseResponse();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", query);
            Object response = analysisModuleClient.getSingleQueryResponse(jsonObject);
            JSONObject predObj = convertToJSONObject((LinkedHashMap<String, Object>) response);
            baseResponse.setMessage((String) predObj.get("llm_output"));
            baseResponse.setStatus(HttpStatus.OK);

        } catch (Exception e) {
            baseResponse.setMessage("Failed to request " + e.getMessage());
            baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        finally {
            return baseResponse;
        }
    }

    public Flux<String> getChatStream(String query) {
        return webClient.post()
                .uri("/chat-single-stream") // Replace with the actual endpoint
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_PLAIN) // Expect plain text response
                .bodyValue("{\"query\": \"" + query + "\"}") // Adjust body as needed
                .retrieve()
//                .bodyToMono(String.class) // Expect a single text response

                .bodyToFlux(String.class); // Expecting a Flux of String
    }
    public Flux<String> getChatStreamSample(String query) {
        return webClient.post()
                .uri("/chat-single-stream-sample") // Replace with the actual endpoint
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"query\": \"" + query + "\"}") // Adjust body as needed
                .retrieve()
                .bodyToFlux(String.class); // Expecting a Flux of String
    }
}
