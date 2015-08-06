package com.jing.Model;

import java.io.Serializable;

/**
 * Created by jingjing on 8/2/15.
 */
public class JsonResponse implements Serializable {
    private String status = "";
    private String errorMessage = "";

    public JsonResponse(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    ;

    public String getErrorMessage() {
        return errorMessage;
    }

    ;
}
