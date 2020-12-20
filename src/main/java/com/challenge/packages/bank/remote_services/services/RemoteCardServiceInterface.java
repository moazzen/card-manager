package com.challenge.packages.bank.remote_services.services;

import com.challenge.packages.bank.card.dto.Transfer;

public interface RemoteCardServiceInterface {
    Transfer transfer (Transfer transfer);
    Short getProviderNumber ();
}
