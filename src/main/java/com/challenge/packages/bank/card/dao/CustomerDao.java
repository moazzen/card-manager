package com.challenge.packages.bank.card.dao;

import com.challenge.packages.bank.card.dto.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, Integer> {
    Customer getCustomerById(Integer id);
}
