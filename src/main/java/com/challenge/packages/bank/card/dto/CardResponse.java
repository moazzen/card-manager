package com.challenge.packages.bank.card.dto;

import com.challenge.packages.bank.general.dto.ServiceResponse;

public class CardResponse extends ServiceResponse {

    public CardResponse(int status, String description) {
        super(status, description);
    }

    public CardResponse() {
    }
}
