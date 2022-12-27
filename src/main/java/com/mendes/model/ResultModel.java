package com.mendes.model;

import java.io.Serializable;

/**
 * @author mendes
 */

public class ResultModel implements Serializable {

    private boolean error;
    private String referenceNumber;
    public ResultModel() {
    }

    public ResultModel(boolean error, String referenceNumber) {
        this.error = error;
        this.referenceNumber = referenceNumber;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
