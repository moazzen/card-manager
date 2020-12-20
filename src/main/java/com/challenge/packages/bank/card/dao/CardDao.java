package com.challenge.packages.bank.card.dao;

import com.challenge.packages.bank.card.dto.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface CardDao extends CrudRepository <Card, Integer> {
    Card findByPan(Long pan);
    List<Card> findAllByCustomerId (Integer customerId);
    @Transactional
    Integer deleteByPanAndCustomerId(Long pan, Integer customerId);
}
