package com.challenge.packages.bank.card.dto;

import com.challenge.packages.bank.general.dto.ServiceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class CardListResponse extends ServiceResponse {

    List<Card> cardList;

    public CardListResponse() {
    }

    public CardListResponse(int status, String description) {
        super(status, description);
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    @Override
    public String toString() {
        return "CardListResponse{" +
                "cardList=" + cardList +
                "} " + super.toString();
    }
}
