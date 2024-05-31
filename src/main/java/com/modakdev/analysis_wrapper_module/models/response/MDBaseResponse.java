package com.modakdev.analysis_wrapper_module.models.response;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class MDBaseResponse {

    protected final static String txnId = UUID.randomUUID().toString();
    protected static String txnDate = String.valueOf(java.time.LocalDate.now());
    protected static String txnTime = String.valueOf(java.time.LocalTime.now());
    protected HttpStatus status;
    protected String message = "";

    public MDBaseResponse() {

    }

    public MDBaseResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getTxnId() {
        return txnId;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
