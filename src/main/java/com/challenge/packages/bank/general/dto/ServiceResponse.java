package com.challenge.packages.bank.general.dto;

public class ServiceResponse {
    private int status;
    private String description;

    public ServiceResponse(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public ServiceResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
