package ru.dream_store_kzn.report_parser.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dream_store_kzn.report_parser.exception.SalesCalculatorException;
import ru.dream_store_kzn.report_parser.model.ProfitModel;
import ru.dream_store_kzn.report_parser.model.SalesItemModel;
import ru.dream_store_kzn.report_parser.parser.WorkbookParser;
import ru.dream_store_kzn.report_parser.properties.SalesReportProperties;
import ru.dream_store_kzn.report_parser.service.ProfitService;

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

    private final SalesReportProperties salesReportProperties;
    private final WorkbookParser workbookParser;

    /**
     * {@inheritDoc}
     */
    @Override
    public ProfitModel calculateProfit(InputStream salesReport, InputStream paidStorageReport) {
        List<SalesItemModel> purchases;
        try {
            purchases = workbookParser.parseSalesReport(salesReport);
        } catch (Exception e) {
            throw new SalesCalculatorException("An error occurred while parsing sales report", e);
        }

        var result = purchases.stream()
                .collect(Collectors.groupingBy(SalesItemModel::getSku,
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


        return profitModel;
    }

    private Double calculateProfit(SalesItemModel model) {
        var salePrice = model.getPrice() * model.getQuantity();
        var saleNetCost = model.getNetCost() * model.getQuantity();
        var tax = salePrice * (salesReportProperties.getTax() / 100);

        return salePrice - tax - model.getMarketPlaceFee() - saleNetCost;
    }

}
