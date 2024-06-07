package com.modakdev.analysis_wrapper_module.api.services;


import com.modakdev.response.MDBaseResponse;

public interface LLMQueryService {
    public MDBaseResponse getSingleQueryResponse(String query);
}
