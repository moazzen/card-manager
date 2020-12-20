package com.challenge.packages.bank.card.dto;

import com.challenge.packages.bank.general.dto.ServiceResponse;

import java.util.List;

public class CustomerTransactionCount extends ServiceResponse {

    Integer customerId;
    List<CardTransactions> panTransactions;

    public CustomerTransactionCount() {
    }

    public CustomerTransactionCount(int status, String description) {
        super(status, description);
    }

    public CustomerTransactionCount(Integer customerId, List<CardTransactions> panTransactions) {
        this.customerId = customerId;
        this.panTransactions = panTransactions;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<CardTransactions> getPanTransactions() {
        return panTransactions;
    }

    public void setPanTransactions(List<CardTransactions> panTransactions) {
        this.panTransactions = panTransactions;
    }

    @Override
    public String toString() {
        return "CustomerTransactionCount{" +
                "customerId=" + customerId +
                ", panTransactions=" + panTransactions +
                "} " + super.toString();
    }
}
