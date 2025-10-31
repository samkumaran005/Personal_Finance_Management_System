package com.finance.personalfinancemanager.controller;

import com.finance.personalfinancemanager.model.Income;
import com.finance.personalfinancemanager.service.FinanceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/income")
@CrossOrigin(origins = "*")
public class IncomeController {

    private final FinanceService financeService;

    public IncomeController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping
    public ResponseEntity<?> list(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(401).body("unauthenticated");
        List<Income> list = financeService.getAllIncome(userId);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Income income, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(401).body("unauthenticated");
        // ensure date present
        if (income.getDate() == null) income.setDate(LocalDate.now());
        Income saved = financeService.addIncome(userId, income);
        return ResponseEntity.ok(saved);
    }
}
