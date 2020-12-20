package com.challenge.packages.bank.card.dto;


public class CardTransactions {
    Long pan;
    Integer successCount;
    Integer failedCount;

    public CardTransactions() {
    }

    public CardTransactions(Long pan, Integer successCount, Integer failedCount) {
        this.pan = pan;
        this.successCount = successCount;
        this.failedCount = failedCount;
    }

    public Long getPan() {
        return pan;
    }

    public void setPan(Long pan) {
        this.pan = pan;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    @Override
    public String toString() {
        return "CardTransactions{" +
                "pan=" + pan +
                ", successCount=" + successCount +
                ", failedCount=" + failedCount +
                '}';
    }
}
