package ru.dream_store_kzn.salescalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dream_store_kzn.salescalculator.entity.PurchasingEntity;

public interface PurchasingRepository extends JpaRepository<PurchasingEntity, Integer> {
}
