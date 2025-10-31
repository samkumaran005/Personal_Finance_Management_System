package com.finance.personalfinancemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.finance.personalfinancemanager.model.Expense;
import com.finance.personalfinancemanager.service.FinanceService;

@Controller
public class ExpenseController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/expense")
    public String showExpense(Model model) {
        model.addAttribute("expenseList", financeService.getAllExpenses());
        model.addAttribute("expense", new Expense());
        return "expense";
    }

    @PostMapping("/expense")
    public String addExpense(@ModelAttribute Expense expense) {
        financeService.addExpense(expense);
        return "redirect:/expense";
    }
}
