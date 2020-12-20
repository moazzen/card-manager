package com.challenge.packages.bank.card.constants;

import org.springframework.http.HttpStatus;

public enum HttpStatusResponse {

    SUCCESS(HttpStatus.BAD_REQUEST.value(), 0, "Successfully done"),

    INVALID_PAN(HttpStatus.BAD_REQUEST.value(), 1, "Pan is invalid"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 1, "We have a error");

    private int httpCode;
    private int statusCode;
    private String description;

    HttpStatusResponse(int httpCode, int statusCode, String description) {
        this.httpCode = httpCode;
        this.statusCode = statusCode;
        this.description = description;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }
    
    public static HttpStatusResponse getStatusByHttpStatus (int httpCode) {
        for (HttpStatusResponse item: HttpStatusResponse.values()) {
            if (item.getHttpCode() == httpCode)
                return item;
        }
        return INTERNAL_ERROR;
    }
}
