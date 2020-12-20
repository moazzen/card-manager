package com.challenge.packages.bank.card.dao;

import com.challenge.packages.bank.card.dto.CardTransactions;
import com.challenge.packages.bank.card.dto.Transfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface TransferDao extends CrudRepository <Transfer, Integer> {
    List<Transfer> getTransfersByCustomerIdAndInsertDateBetween(Integer customerId, LocalDate fromDate, LocalDate toDate);
}
