package com.modakdev.analysis_wrapper_module.models.request;

public class LLMBaseRequest {
    String query, role;

    public LLMBaseRequest(String query, String role) {
        this.query = query;
        this.role = role;
    }

    public LLMBaseRequest() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LLMBaseRequest{" +
                "query='" + query + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
