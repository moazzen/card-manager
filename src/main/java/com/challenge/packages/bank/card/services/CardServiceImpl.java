package com.challenge.packages.bank.card.services;

import com.challenge.config.AppConf;
import com.challenge.config.Constants;
import com.challenge.packages.bank.card.dao.CardDao;
import com.challenge.packages.bank.card.dao.TransferDao;
import com.challenge.packages.bank.card.dto.Card;
import com.challenge.packages.bank.card.dto.CardTransactions;
import com.challenge.packages.bank.card.dto.Notification;
import com.challenge.packages.bank.card.dto.Transfer;
import com.challenge.packages.bank.general.services.RabbitmqService;
import com.challenge.packages.bank.remote_services.services.RemoteCardServiceFactory;
import com.challenge.packages.bank.remote_services.services.RemoteCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CardServiceImpl implements CardServiceInterface {

    private final CardDao cardDao;
    private final TransferDao transferDao;
    private final RemoteCardServiceFactory cardServiceFactory;
    private final RabbitmqService rabbitmqService;
    private final AppConf appConf;

    @Autowired
    public CardServiceImpl(CardDao cardDao, TransferDao transferDao, RemoteCardServiceFactory cardServiceFactory, RabbitmqService rabbitmqService, AppConf appConf) {
        this.cardDao = cardDao;
        this.transferDao = transferDao;
        this.cardServiceFactory = cardServiceFactory;
        this.rabbitmqService = rabbitmqService;
        this.appConf = appConf;
    }

    ////////////////////////////// Add Card //////////////////////////////

    @Override
    public void addCard(Card card) {
        ;
        card.setInsertDateTime(new Timestamp(new Date().getTime()));
        cardDao.save(card);
    }

    ////////////////// Delete Card By Pan And CustomerId //////////////////

    @Override
    public Integer deleteCardByPanAndCustomerId(Long pan, Integer customerId) {
        return cardDao.deleteByPanAndCustomerId(pan, customerId);
    }

    /////////////////////// Get card by customerId ///////////////////////

    @Override
    public List<Card> getCardsByCustomerId(Integer customerId) {
        return cardDao.findAllByCustomerId(customerId);
    }

    /////////////////////////// Get card by pan ///////////////////////////

    @Override
    public Card getCardByPan(Long pan) {
        return cardDao.findByPan(pan);
    }

    /////////////////////////////// Transfer //////////////////////////////

    @Override
    public Transfer addTransfer(Transfer transfer) {
        transfer.setInsertDate(LocalDate.now());
        transfer.setInsertDateTime(new Timestamp(new Date().getTime()));

        //select provider by factory design pattern
        String panFirst4digit = transfer.getFromPan().toString().substring(0, 4);
        RemoteCardServiceInterface transferProvider = cardServiceFactory.getTransferProvider(panFirst4digit);
        //call provider
        Transfer transferResponse = transferProvider.transfer(transfer);

        try {
            if (transferResponse.getHttpResponseCode() == HttpStatus.OK.value()) {
                transferDao.save(transfer);
                Notification notification = new Notification(transfer.getCustomerId(), appConf.getTransferMessageText());
                rabbitmqService.pushInQueue(Constants.SMS_QUEUE_1, notification);
            }
        } catch (Exception e) {
            //log error
        }
        return transfer;
    }

    //////////////////// Get all transfers by customer ////////////////////

    @Override
    public List<CardTransactions> getAllTransfersByCustomerIdAndInsertDate(Integer customerId, LocalDate fromDate, LocalDate toDate) {
        List<Transfer> transfersByCustomerIdAndInsertDateBetween = transferDao.getTransfersByCustomerIdAndInsertDateBetween(customerId, fromDate, toDate);

        List<CardTransactions> customerTransactions = new ArrayList<>();
        HashMap<Long, CardTransactions> list = new HashMap<>();
        for (Transfer item : transfersByCustomerIdAndInsertDateBetween) {
            if (list.containsKey(item.getFromPan())) {
                CardTransactions cardTransactions = list.get(item.getFromPan());
                if (item.getHttpResponseCode() == HttpStatus.OK.value()) {
                    cardTransactions.setSuccessCount(cardTransactions.getSuccessCount() + 1);
                } else {
                    cardTransactions.setFailedCount(cardTransactions.getFailedCount() + 1);
                }
            } else {
                if (item.getHttpResponseCode() == HttpStatus.OK.value()) {
                    list.put(item.getFromPan(), new CardTransactions(item.getFromPan(), 1, 0));
                } else {
                    list.put(item.getFromPan(), new CardTransactions(item.getFromPan(), 0, 1));
                }
            }
        }

        for (Long item : list.keySet()) {
            customerTransactions.add(list.get(item));
        }

        return customerTransactions;
    }
}
