package ru.dream_store_kzn.report_parser.model;

import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfitModel {

    private UUID id;

    private Double totalProfit;

    private Map<String, Double> profitBySku = new HashMap<>();
}
