package com.challenge.packages.bank.remote_services.dto;

public class SmsObject {
    String msg;
    String target;

    public SmsObject() {
    }

    public SmsObject(String msg, String target) {
        this.msg = msg;
        this.target = target;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
