package ru.dream_store_kzn.salescalculator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.dream_store_kzn.salescalculator.builder.PurchasingEntityBuilder;
import ru.dream_store_kzn.salescalculator.builder.model.PurchasingModelBuilder;
import ru.dream_store_kzn.salescalculator.exception.NotFoundException;
import ru.dream_store_kzn.salescalculator.model.PurchasingModel;
import ru.dream_store_kzn.salescalculator.repository.PurchasingRepository;
import ru.dream_store_kzn.salescalculator.service.PurchasingService;

/**
 * Реализация сервиса для работы с закупками
 */
@Service
@RequiredArgsConstructor
public class PurchasingServiceImpl implements PurchasingService {

    private final PurchasingRepository repository;

    @Override
    public void save(PurchasingModel model) {
        repository.save(PurchasingEntityBuilder.build(model));
    }

    @Override
    public void edit(PurchasingModel model) {
        repository.save(PurchasingEntityBuilder.buildEdit(model));
    }

    @Override
    public PurchasingModel getById(Integer id) {
        return PurchasingModelBuilder.build(
                repository.findById(id).orElseThrow(() -> new NotFoundException("Закупка не найдена. Идентификатор - " + id)));
    }

    @Override
    public Page<PurchasingModel> getAll() {
        PageRequest of = PageRequest.of(0, 20);
        return repository.findAll(of).map(PurchasingModelBuilder::build);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
