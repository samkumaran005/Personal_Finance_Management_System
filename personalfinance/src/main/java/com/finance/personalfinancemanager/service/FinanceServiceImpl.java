package com.finance.personalfinancemanager.service;

import com.finance.personalfinancemanager.model.Expense;
import com.finance.personalfinancemanager.model.Income;
import com.finance.personalfinancemanager.repository.ExpenseRepository;
import com.finance.personalfinancemanager.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private IncomeRepository incomeRepo;

    @Autowired
    private ExpenseRepository expenseRepo;

    @Override
    public List<Income> getAllIncomes() {
        return incomeRepo.findAll();
    }

    @Override
    public void addIncome(Income income) {
        incomeRepo.save(income);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    @Override
    public void addExpense(Expense expense) {
        expenseRepo.save(expense);
    }

    @Override
    public double getTotalIncome() {
        return incomeRepo.findAll().stream().mapToDouble(Income::getAmount).sum();
    }

    @Override
    public double getTotalExpense() {
        return expenseRepo.findAll().stream().mapToDouble(Expense::getAmount).sum();
    }

    @Override
    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }
}
