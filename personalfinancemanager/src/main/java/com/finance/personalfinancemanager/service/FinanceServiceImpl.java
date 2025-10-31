package com.finance.personalfinancemanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.finance.personalfinancemanager.model.Income;
import com.finance.personalfinancemanager.model.Expense;
import com.finance.personalfinancemanager.model.User;
import com.finance.personalfinancemanager.repository.IncomeRepository;
import com.finance.personalfinancemanager.repository.ExpenseRepository;
import com.finance.personalfinancemanager.repository.UserRepository;

@Service
public class FinanceServiceImpl implements FinanceService {

    private final IncomeRepository incomeRepo;
    private final ExpenseRepository expenseRepo;
    private final UserRepository userRepo;

    public FinanceServiceImpl(IncomeRepository incomeRepo, ExpenseRepository expenseRepo, UserRepository userRepo) {
        this.incomeRepo = incomeRepo;
        this.expenseRepo = expenseRepo;
        this.userRepo = userRepo;
    }

    @Override
    public double getTotalIncome(Long userId) {
        return incomeRepo.findByUserId(userId).stream().mapToDouble(Income::getAmount).sum();
    }

    @Override
    public double getTotalExpense(Long userId) {
        return expenseRepo.findByUserId(userId).stream().mapToDouble(Expense::getAmount).sum();
    }

    @Override
    public double getBalance(Long userId) {
        return getTotalIncome(userId) - getTotalExpense(userId);
    }

    @Override
    public List<Income> getAllIncome(Long userId) {
        return incomeRepo.findByUserId(userId);
    }

    @Override
    public List<Expense> getAllExpense(Long userId) {
        return expenseRepo.findByUserId(userId);
    }

    @Override
    public Income addIncome(Long userId, Income income) {
        User user = userRepo.findById(userId).orElseThrow();
        income.setUser(user);
        return incomeRepo.save(income);
    }

    @Override
    public Expense addExpense(Long userId, Expense expense) {
        User user = userRepo.findById(userId).orElseThrow();
        expense.setUser(user);
        return expenseRepo.save(expense);
    }
}
