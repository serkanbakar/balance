package com.bank.balance.controller;

import com.bank.balance.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("/monthly/{customerId}/{year}/{month}")
    public ResponseEntity<Double> getMonthlyBalance(@PathVariable String customerId,
                                                    @PathVariable int year, @PathVariable int month) {
        double monthlyBalance = balanceService.getMonthlyBalance(customerId, year, month);
        return ResponseEntity.ok(monthlyBalance);
    }

    @GetMapping("/cumulative/{customerId}")
    public ResponseEntity<Double> getCumulativeBalance(@PathVariable String customerId) {
        double cumulativeBalance = balanceService.getCumulativeBalance(customerId);
        return ResponseEntity.ok(cumulativeBalance);
    }
}
