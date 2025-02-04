package ru.dream_store_kzn.salescalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesProfitRepository extends JpaRepository<SalesProfitEntity, Integer> {
}
