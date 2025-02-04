package ru.dream_store_kzn.salescalculator.service;

import org.springframework.data.domain.Page;
import ru.dream_store_kzn.salescalculator.model.PurchasingModel;

/**
 * Сервис для работы с закупками
 */
public interface PurchasingService {

    /**
     * Сохранение информации о закупке
     */
    void save(PurchasingModel model);

    void edit(PurchasingModel model);

    PurchasingModel getById(Integer id);

    Page<PurchasingModel> getAll();

    void delete (Integer id);
}
