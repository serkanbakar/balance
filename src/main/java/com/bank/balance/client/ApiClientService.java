package com.bank.balance.client;

import com.bank.balance.model.BankTransaction;

import java.util.List;

public interface ApiClientService {
    List<BankTransaction> getTransactionsForCustomer(String customerId);
}
