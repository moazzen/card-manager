package com.challenge.packages.bank.remote_services.services;

import com.challenge.config.Constants;
import com.challenge.packages.bank.card.dto.Notification;
import com.challenge.packages.bank.remote_services.dto.SmsObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsProviderImpl implements SmsSenderInterface {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Integer send(Notification notification) {

        //mapping to service object
        SmsObject sms = new SmsObject();
        sms.setMsg(notification.getMessage());
        sms.setTarget(notification.getRecipient());

        // calling service
        HttpEntity<SmsObject> request = new HttpEntity<>(sms, new HttpHeaders());
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(Constants.SMS_PROVIDER_SERVICE_1, HttpMethod.POST, request, new ParameterizedTypeReference<String>() {});
            return exchange.getStatusCodeValue();
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }
}
