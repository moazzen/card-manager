package com.challenge.config;

import com.challenge.packages.bank.card.dto.Transfer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Constants {

    //EndPoints
    public static final String BANK_CARD_ADD_SERVICE = "/bank/card/add";
    public static final String BANK_CARD_LIST_SERVICE = "/bank/card/list";
    public static final String BANK_CARD_REMOVE_SERVICE = "/bank/card/delete";
    public static final String BANK_CARD_TRANSFER_SERVICE = "/bank/card/transfer";
    public static final String BANK_CARD_TRANSFER_HISTORY_SERVICE = "/bank/card/transfer_history";

    //RabbitMQ queue names
    public static final String SMS_QUEUE_1 = "rabbitMqSmsQueue1";

    //Remote services
    public static final String TRANSFER_HOST_1 = "first-payment-provider";
    public static final int TRANSFER_PORT_1 = 8081;
    public static final String TRANSFER_URL_1 = "/payments/transfer";
    public static final String TRANSFER_SERVICE_1 = "http://"+ TRANSFER_HOST_1 + ":" + TRANSFER_PORT_1 + TRANSFER_URL_1;

    public static final String TRANSFER_HOST_2 = "second-payment-provider";
    public static final int TRANSFER_PORT_2 = 8082;
    public static final String TRANSFER_URL_2 = "/cards/pay";
    public static final String TRANSFER_SERVICE_2 = "http://"+ TRANSFER_HOST_2 + ":" + TRANSFER_PORT_2 + TRANSFER_URL_2;

    public static final String SMS_PROVIDER_HOST_1 = "sms-provider";
    public static final int SMS_PROVIDER_PORT_1 = 8083;
    public static final String SMS_PROVIDER_URL_1= "/messages/send-sms";
    public static final String SMS_PROVIDER_SERVICE_1 = "http://"+ SMS_PROVIDER_HOST_1 + ":" + SMS_PROVIDER_PORT_1 + SMS_PROVIDER_URL_1;

}
