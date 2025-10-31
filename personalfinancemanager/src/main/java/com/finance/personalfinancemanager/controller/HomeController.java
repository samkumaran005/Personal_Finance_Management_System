package com.finance.personalfinancemanager.controller;

import com.finance.personalfinancemanager.service.FinanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HomeController {

    private final FinanceService financeService;

    public HomeController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/home")
    public ResponseEntity<?> home(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(401).body(Map.of("message","unauthenticated"));

        double totalIncome = financeService.getTotalIncome(userId);
        double totalExpense = financeService.getTotalExpense(userId);
        double balance = financeService.getBalance(userId);
        List<?> incomes = financeService.getAllIncome(userId);
        List<?> expenses = financeService.getAllExpense(userId);

        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("title","Personal Finance Manager");
        resp.put("totalIncome", totalIncome);
        resp.put("totalExpense", totalExpense);
        resp.put("balance", balance);
        resp.put("incomes", incomes);
        resp.put("expenses", expenses);
        return ResponseEntity.ok(resp);
    }
}
