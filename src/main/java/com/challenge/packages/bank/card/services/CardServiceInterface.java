package com.challenge.packages.bank.card.services;

import com.challenge.packages.bank.card.dto.Card;
import com.challenge.packages.bank.card.dto.CardTransactions;
import com.challenge.packages.bank.card.dto.CustomerTransactionCount;
import com.challenge.packages.bank.card.dto.Transfer;

import java.time.LocalDate;
import java.util.List;

public interface CardServiceInterface {
    public void addCard(Card card);
    public Integer deleteCardByPanAndCustomerId(Long pan, Integer customerId);
    public List<Card> getCardsByCustomerId (Integer customerId);
    public Card getCardByPan (Long pan);
    public Transfer addTransfer (Transfer transfer);
    public List<CardTransactions> getAllTransfersByCustomerIdAndInsertDate(Integer customerId, LocalDate fromDate, LocalDate toDate);
}
