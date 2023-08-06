package com.bank.balance;

import com.bank.balance.client.ApiClientService;
import com.bank.balance.model.BankTransaction;
import com.bank.balance.service.BalanceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BalanceServiceTest {
    @Autowired
    private BalanceService balanceService;

    @MockBean
    private ApiClientService apiClientService;

    private List<BankTransaction> bankTransactions;
    private static final double DELTA = 0.001;
    private static final String customerId = "customerId";

    @BeforeAll
    public void init()  {
        bankTransactions = new ArrayList<>();
        bankTransactions.add(BankTransaction.builder().amount(100.0).date(LocalDate.of(2023, 7, 15)).build());
        bankTransactions.add(BankTransaction.builder().amount(-50.0).date(LocalDate.of(2023, 7, 20)).build());
        bankTransactions.add(BankTransaction.builder().amount(200.0).date(LocalDate.of(2023, 8, 5)).build());
    }

    @Test
    void testGetMonthlyBalance() {
        when(apiClientService.getTransactionsForCustomer(customerId)).thenReturn(bankTransactions);
        // Test for August 2023
        double monthlyBalance = balanceService.getMonthlyBalance(customerId, 2023, 8);
        assertEquals(200.0, monthlyBalance, DELTA);

        // Test for July 2023
        monthlyBalance = balanceService.getMonthlyBalance(customerId, 2023, 7);
        assertEquals(50.0, monthlyBalance, DELTA);
    }

    @Test
    void testGetCumulativeBalance() {
        when(apiClientService.getTransactionsForCustomer(customerId)).thenReturn(bankTransactions);
        double cumulativeBalance = balanceService.getCumulativeBalance(customerId);
        assertEquals(250.0, cumulativeBalance, DELTA);
    }
}

