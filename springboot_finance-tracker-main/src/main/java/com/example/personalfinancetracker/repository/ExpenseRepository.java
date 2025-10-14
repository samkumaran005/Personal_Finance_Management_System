package com.example.personalfinancetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.personalfinancetracker.model.Expense;

import java.util.List;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
}
