package com.bng.elastic.util;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    FAIL(1000, "Failure", "Something went wrong"),
    SUCCESS(1001, "Success", "Success");

    private int code;
    private String status;
    private String reason;


    ErrorCodes(int i, String status, String reason) {
        this.code = i;
        this.status = status;
        this.reason = reason;
    }

}
