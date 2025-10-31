package com.finance.personalfinancemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finance.personalfinancemanager.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> { }
