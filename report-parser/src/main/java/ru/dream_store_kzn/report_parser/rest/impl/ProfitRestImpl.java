package ru.dream_store_kzn.report_parser.rest.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.dream_store_kzn.report_parser.model.ProfitModel;
import ru.dream_store_kzn.report_parser.rest.ProfitRest;
import ru.dream_store_kzn.report_parser.service.ProfitService;

@RestController
@RequiredArgsConstructor
public class ProfitRestImpl implements ProfitRest {

    private final ProfitService profitService;

    @SneakyThrows
    @Override
    public ProfitModel calculate(@NotNull MultipartFile salesReport,  MultipartFile paidStorageReport) {
        return profitService.calculateProfit(salesReport.getInputStream(), paidStorageReport.getInputStream());
    }
}
