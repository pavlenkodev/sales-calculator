package ru.dream_store_kzn.salescalculator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream_store_kzn.salescalculator.builder.SalesProfitEntityBuilder;
import ru.dream_store_kzn.salescalculator.exception.SalesCalculatorException;
import ru.dream_store_kzn.salescalculator.model.ProfitModel;
import ru.dream_store_kzn.salescalculator.model.SaleItemModel;
import ru.dream_store_kzn.salescalculator.parser.WorkbookParser;
import ru.dream_store_kzn.salescalculator.properties.SalesReportProperties;
import ru.dream_store_kzn.salescalculator.repository.SalesProfitRepository;
import ru.dream_store_kzn.salescalculator.service.ProfitService;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Реализация сервиса, осуществляющего работу с прибылью
 */
@Service
@RequiredArgsConstructor
public class ProfitServiceImpl implements ProfitService {

    private final SalesProfitRepository salesProfitRepository;
    private final SalesReportProperties salesReportProperties;
    private final WorkbookParser workbookParser;

    /**
     * {@inheritDoc}
     */
    @Override
    public ProfitModel calculateProfit(InputStream salesReport, InputStream paidStorageReport) {
        List<SaleItemModel> purchases;
        try {
            purchases = workbookParser.parseSaleReport(salesReport);
        } catch (Exception e) {
            throw new SalesCalculatorException("An error occurred while parsing sales report", e);
        }

        var result = purchases.stream()
                .collect(Collectors.groupingBy(SaleItemModel::getSku,
                        Collectors.mapping(this::calculateProfit, Collectors.toList())));

        var profitModel = new ProfitModel();
        profitModel.setId(UUID.randomUUID());
        result.forEach((k, v) -> {
            var profitBySku = v.stream().reduce(0.0, Double::sum);
            profitModel.getProfitBySku().put(k, profitBySku);

            if (profitModel.getTotalProfit() != null) {
                profitModel.setTotalProfit(profitModel.getTotalProfit() + profitBySku);
            } else {
                profitModel.setTotalProfit(profitBySku);
            }
        });

        salesProfitRepository.save(SalesProfitEntityBuilder.build(profitModel));

        return profitModel;
    }

    private Double calculateProfit(SaleItemModel model) {
        var salePrice = model.getPrice() * model.getQuantity();
        var saleNetCost = model.getNetCost() * model.getQuantity();
        var tax = salePrice * (salesReportProperties.getTax() / 100);

        return salePrice - tax - model.getMarketPlaceFee() - saleNetCost;
    }

}
