package com.syntech.api;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author bipan
 */
public class RestResponse {

    private String success;
    private String code;
    private String message;
    private String result;

    public RestResponse() {
    }

    public RestResponse(String success, String code, String message, String result) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static Response responseBuilder(String success, String code, String message, String result) {
        JsonObject json = Json.createObjectBuilder()
                .add("success", success)
                .add("code", code)
                .add("message", message)
                .add("result", result == null ? "" : result).build();

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
