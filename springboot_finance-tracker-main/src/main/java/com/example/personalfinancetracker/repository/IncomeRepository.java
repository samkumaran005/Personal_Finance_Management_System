package com.example.personalfinancetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.personalfinancetracker.model.Income;
@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
}
