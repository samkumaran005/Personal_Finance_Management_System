package com.finance.personalfinancemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.finance.personalfinancemanager.model.Income;
import com.finance.personalfinancemanager.service.FinanceService;

@Controller
public class IncomeController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/income")
    public String showIncome(Model model) {
        model.addAttribute("incomeList", financeService.getAllIncomes());
        model.addAttribute("income", new Income());
        return "income";
    }

    @PostMapping("/income")
    public String addIncome(@ModelAttribute Income income) {
        financeService.addIncome(income);
        return "redirect:/income";
    }
}
