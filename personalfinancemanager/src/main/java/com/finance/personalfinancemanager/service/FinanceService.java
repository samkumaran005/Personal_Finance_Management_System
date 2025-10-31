package com.finance.personalfinancemanager.service;

import com.finance.personalfinancemanager.model.Income;
import com.finance.personalfinancemanager.model.Expense;
import java.util.List;

public interface FinanceService {
    double getTotalIncome(Long userId);
    double getTotalExpense(Long userId);
    double getBalance(Long userId);
    List<Income> getAllIncome(Long userId);
    List<Expense> getAllExpense(Long userId);
    Income addIncome(Long userId, Income income);
    Expense addExpense(Long userId, Expense expense);
}
