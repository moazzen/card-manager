package com.challenge.packages.bank.remote_services.dto;

public class RemoteCardServiceObject_2 {

    private String source;
    private String target;
    private String cvv2;
    private String expire;
    private String pin2;
    Integer amount;

    public RemoteCardServiceObject_2() {
    }

    public RemoteCardServiceObject_2(String source, String target, String cvv2, String expire, String pin2, Integer amount) {
        this.source = source;
        this.target = target;
        this.cvv2 = cvv2;
        this.expire = expire;
        this.pin2 = pin2;
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getPin2() {
        return pin2;
    }

    public void setPin2(String pin2) {
        this.pin2 = pin2;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
