package com.challenge.packages.bank.remote_services.services;

import com.challenge.packages.bank.card.dto.Notification;

public interface SmsSenderInterface {
     Integer send (Notification notification);
}
