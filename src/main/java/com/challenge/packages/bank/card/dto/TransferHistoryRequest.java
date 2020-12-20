package com.challenge.packages.bank.card.dto;

public class TransferHistoryRequest {

    Integer customerId;
    String fromDate;
    String toDate;

    public TransferHistoryRequest() {
    }

    public TransferHistoryRequest(Integer customerId, String fromDate, String toDate) {
        this.customerId = customerId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
