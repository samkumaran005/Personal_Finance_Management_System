package com.finance.personalfinancemanager.controller;

import com.finance.personalfinancemanager.model.Expense;
import com.finance.personalfinancemanager.service.FinanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final FinanceService financeService;

    public ExpenseController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping
    public ResponseEntity<?> list(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(401).body("unauthenticated");
        List<Expense> list = financeService.getAllExpense(userId);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Expense expense, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(401).body("unauthenticated");
        if (expense.getDate() == null) expense.setDate(LocalDate.now());
        Expense saved = financeService.addExpense(userId, expense);
        return ResponseEntity.ok(saved);
    }
}
