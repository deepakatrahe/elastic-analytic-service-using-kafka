package com.bng.elastic.response;


import com.bng.elastic.enums.RequestStatus;
import com.bng.elastic.util.ErrorCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericResponse {

    private int statusCode;
    private String status;
    private String reason;
//	private Subscription subscription;

    public static GenericResponse getFailure(String reason) {

        GenericResponse gr = new GenericResponse();
        gr.setReason(reason);
        gr.setStatus(RequestStatus.FAILURE.toString());
        return gr;
    }

    public static GenericResponse getSuccess(String reason) {
        GenericResponse gr = new GenericResponse();
        gr.setStatusCode(200);
        gr.setReason(reason);
        gr.setStatus(RequestStatus.SUCCESS.toString());

        return gr;

    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setMBErrorCode(ErrorCodes mec) {
        this.statusCode=mec.getCode();
        this.status=mec.getStatus();
    }

}
