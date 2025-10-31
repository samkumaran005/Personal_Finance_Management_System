package com.finance.personalfinancemanager.service;

import com.finance.personalfinancemanager.model.Expense;
import com.finance.personalfinancemanager.model.Income;
import java.util.List;

public interface FinanceService {
    // Incomes
    List<Income> getAllIncomes();
    void addIncome(Income income);

    // Expenses
    List<Expense> getAllExpenses();
    void addExpense(Expense expense);

    // Calculations
    double getTotalIncome();
    double getTotalExpense();
    double getBalance();
}
