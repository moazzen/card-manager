package com.challenge.packages.bank.remote_services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RemoteCardServiceFactory {

    private final RemoteCardServiceInterface cardService_1;

    private final RemoteCardServiceInterface cardService_2;

    @Autowired
    public RemoteCardServiceFactory(@Qualifier(value = "remoteCardServiceProvider_1") RemoteCardServiceInterface cardService_1, @Qualifier(value = "remoteCardServiceProvider_2") RemoteCardServiceInterface cardService_2) {
        this.cardService_1 = cardService_1;
        this.cardService_2 = cardService_2;
    }


    public RemoteCardServiceInterface getTransferProvider (String provider) {
        switch (provider) {
            case "6037":
                return cardService_1;
            default:
                return cardService_2;
        }
    }
}
