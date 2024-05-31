package com.modakdev.analysis_wrapper_module.api.services;

import com.modakdev.analysis_wrapper_module.api.client.AnalysisModuleClient;
import com.modakdev.analysis_wrapper_module.models.response.MDBaseResponse;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

import static com.modakdev.analysis_wrapper_module.LibraryFunctions.convertToJSONObject;

@Service
public class LLMQueryServiceImpl implements LLMQueryService{

    private final AnalysisModuleClient analysisModuleClient;

    @Autowired
    public LLMQueryServiceImpl(AnalysisModuleClient analysisModuleClient) {
        this.analysisModuleClient = analysisModuleClient;
    }

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
}
