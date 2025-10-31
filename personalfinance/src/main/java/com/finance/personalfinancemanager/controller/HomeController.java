package com.finance.personalfinancemanager.controller;

import com.finance.personalfinancemanager.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Personal Finance Manager");
        model.addAttribute("totalIncome", financeService.getTotalIncome());
        model.addAttribute("totalExpense", financeService.getTotalExpense());
        model.addAttribute("balance", financeService.getBalance());
        return "home";
    }
}
