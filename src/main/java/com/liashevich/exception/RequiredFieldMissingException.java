package com.liashevich.exception;

import java.util.List;

public class RequiredFieldMissingException extends RuntimeException {
    private List<String> missingFields;
    public RequiredFieldMissingException(List<String> missingFields) {
        this.missingFields = missingFields;
    }


    public List<String> getMissingFields() {
        return missingFields;
    }

    public void setMissingFields(List<String> missingFields) {
        this.missingFields = missingFields;
    }
}
