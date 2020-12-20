package com.challenge.packages.bank.card.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="transfer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "from_pan")
    private Long fromPan;

    @Column(name = "to_pan")
    private Long toPan;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "http_response_code")
    private Integer httpResponseCode;

    @Column(name = "response_code")
    private Integer responseCode;

    @Column(name = "insert_date_time")
    private Date insertDateTime;

    @Column(name = "insert_date")
    private LocalDate insertDate;

    @Column(name = "provider")
    private Short provider;

    @Transient
    private String pin;
    @Transient
    private String cvv2;
    @Transient
    private String expDateYear;
    @Transient
    private String expDateMonth;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getFromPan() {
        return fromPan;
    }

    public void setFromPan(Long fromPan) {
        this.fromPan = fromPan;
    }

    public Long getToPan() {
        return toPan;
    }

    public void setToPan(Long toPan) {
        this.toPan = toPan;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getHttpResponseCode() {
        return httpResponseCode;
    }

    public void setHttpResponseCode(Integer httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Date getInsertDateTime() {
        return insertDateTime;
    }

    public void setInsertDateTime(Date insertDateTime) {
        this.insertDateTime = insertDateTime;
    }

    public LocalDate getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }

    public Short getProvider() {
        return provider;
    }

    public void setProvider(Short provider) {
        this.provider = provider;
    }


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpDateYear() {
        return expDateYear;
    }

    public void setExpDateYear(String expDateYear) {
        this.expDateYear = expDateYear;
    }

    public String getExpDateMonth() {
        return expDateMonth;
    }

    public void setExpDateMonth(String expDateMonth) {
        this.expDateMonth = expDateMonth;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", fromPan=" + fromPan +
                ", toPan=" + toPan +
                ", customerId=" + customerId +
                ", amount=" + amount +
                ", httpResponseCode=" + httpResponseCode +
                ", responseCode=" + responseCode +
                ", insertDateTime=" + insertDateTime +
                ", insertDate=" + insertDate +
                ", provider=" + provider +
                '}';
    }
}
