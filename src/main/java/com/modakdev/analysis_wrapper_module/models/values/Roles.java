package com.modakdev.analysis_wrapper_module.models.values;

public enum Roles {
    USER("User");
    private final String stringValue;

    Roles(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
