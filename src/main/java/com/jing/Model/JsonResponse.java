package com.jing.Model;

/**
 * Created by jingjing on 8/2/15.
 */
public class JsonResponse {
    private String status = "";
    private String errorMessage = "";

    public JsonResponse(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
