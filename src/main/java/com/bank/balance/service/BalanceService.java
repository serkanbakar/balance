package com.bank.balance.service;

public interface BalanceService {
    double getMonthlyBalance(String customerId, int year, int month);
    double getCumulativeBalance(String customerId);
}

