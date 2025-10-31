package com.finance.personalfinancemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finance.personalfinancemanager.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> { }
