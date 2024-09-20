package com.modakdev.analysis_wrapper_module.api;

import com.modakdev.analysis_wrapper_module.api.client.AnalysisModuleClient;
import com.modakdev.analysis_wrapper_module.api.services.LLMQueryServiceImpl;
import com.modakdev.request.LLMBaseRequest;
import com.modakdev.response.MDBaseResponse;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.modakdev.analysis_wrapper_module.LibraryFunctions.convertToJSONObject;

@RestController
public class Controller {

    private final LLMQueryServiceImpl llmQueryService;

    public Controller(LLMQueryServiceImpl llmQueryService) {
        this.llmQueryService = llmQueryService;
    }


    @GetMapping("/healthcheck")
    public MDBaseResponse healthCheck(){
        MDBaseResponse baseResponse = new MDBaseResponse();
        baseResponse.setMessage("All system up and running");
        baseResponse.setStatus(HttpStatus.OK);
        return baseResponse;
    }

    @PostMapping("/get_analysis_report")
    public MDBaseResponse getSingleQueryResponse(@RequestBody LLMBaseRequest requestData){
        MDBaseResponse response =llmQueryService.getSingleQueryResponse(requestData.getQuery());
        return response;
    }

    @PostMapping(value = "/chat-single-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatSingleStream(@RequestBody Map<String, String> request) {
        String query = request.get("query");

        // Call the external service via WebClient and return the streaming response
        return llmQueryService.getStream(query);
    }
}
