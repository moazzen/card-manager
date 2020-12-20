package com.challenge.packages.bank.remote_services.services;

import com.challenge.config.Constants;
import com.challenge.packages.bank.card.dto.Transfer;
import com.challenge.packages.bank.remote_services.dto.RemoteCardServiceObject_1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteCardServiceProvider_1 implements RemoteCardServiceInterface {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Transfer transfer(Transfer transfer) {

        transfer.setProvider(getProviderNumber());

        //mapping to service object
        RemoteCardServiceObject_1 serviceObject = new RemoteCardServiceObject_1();
        serviceObject.setSource(transfer.getFromPan().toString());
        serviceObject.setDest(transfer.getToPan().toString());
        serviceObject.setPin(transfer.getPin());
        serviceObject.setCvv2(transfer.getCvv2());
        serviceObject.setExpDate(transfer.getExpDateYear().concat(transfer.getExpDateMonth()));
        serviceObject.setAmount(transfer.getAmount());

        // calling service
        HttpEntity<RemoteCardServiceObject_1> request = new HttpEntity<>(serviceObject, new HttpHeaders());
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(Constants.TRANSFER_SERVICE_1, HttpMethod.POST, request, new ParameterizedTypeReference<String>() {});
            transfer.setHttpResponseCode(exchange.getStatusCodeValue());
        } catch (Exception e) {
            transfer.setHttpResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return transfer;
    }

    @Override
    public Short getProviderNumber() {
        return 1;
    }
}
