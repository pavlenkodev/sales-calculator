package ru.dream_store_kzn.report_parser.model;

import lombok.*;
import ru.dream_store_kzn.report_parser.enums.SaleStatusEnum;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesItemModel {

    private UUID id;

    private SaleStatusEnum status;

    private String sku;

    private Double quantity;

    private Double price;

    private Double marketPlaceFee;

    private Double promo;

    private Double netCost;
}
