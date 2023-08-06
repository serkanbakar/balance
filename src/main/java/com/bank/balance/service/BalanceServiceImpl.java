package com.bank.balance.service;

import com.bank.balance.model.BankTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final ApiClientService apiClientService;

    @Autowired
    public BalanceServiceImpl(ApiClientService apiClientService) {
        this.apiClientService = apiClientService;
    }

    @Override
    public double getMonthlyBalance(String customerId, int year, int month) {
        List<BankTransaction> bankTransactions = apiClientService.getTransactionsForCustomer(customerId);
        return bankTransactions.stream()
                .filter(t -> t.getDate().getYear() == year && t.getDate().getMonthValue() == month)
                .map(BankTransaction::getAmount)
                .mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public double getCumulativeBalance(String customerId) {
        List<BankTransaction> bankTransactions = apiClientService.getTransactionsForCustomer(customerId);
        return bankTransactions.stream()
                .map(BankTransaction::getAmount)
                .mapToDouble(Double::doubleValue).sum();
    }
}

