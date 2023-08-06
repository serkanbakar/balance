package com.bank.balance.service;

import com.bank.balance.model.BankTransaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiClientServiceImpl implements ApiClientService {
    @Override
    public List<BankTransaction> getTransactionsForCustomer(String customerId) {
        // Dummy transactions
        List<BankTransaction> bankTransactions = new ArrayList<>();
        bankTransactions.add(BankTransaction.builder().amount(100.0).date(LocalDate.of(2023, 7, 15)).build());
        bankTransactions.add(BankTransaction.builder().amount(-50.0).date(LocalDate.of(2023, 7, 20)).build());
        bankTransactions.add(BankTransaction.builder().amount(200.0).date(LocalDate.of(2023, 8, 5)).build());
        return bankTransactions;
    }
}
