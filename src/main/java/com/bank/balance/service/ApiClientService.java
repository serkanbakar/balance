package com.bank.balance.service;

import com.bank.balance.model.BankTransaction;

import java.util.List;

public interface ApiClientService {
    List<BankTransaction> getTransactionsForCustomer(String customerId);
}
