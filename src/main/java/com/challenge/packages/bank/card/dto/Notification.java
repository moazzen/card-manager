package com.challenge.packages.bank.card.dto;

import java.io.Serializable;

public class Notification implements Serializable{
    Integer customerId;
    String message;
    String recipient;

    public Notification() {
    }

    public Notification(String message, String recipient) {
        this.message = message;
        this.recipient = recipient;
    }

    public Notification(Integer customerId, String message) {
        this.customerId = customerId;
        this.message = message;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}
