package ru.dream_store_kzn.report_parser.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("sales.report")
public class SalesReportProperties {

    private Double tax;
    private String sheetName;
    private Integer statusCellNumber;
    private Integer skuCellNumber;
    private Integer quantityCellNumber;
    private Integer marketPlaceFeeCellNumber;
    private Integer priceCellNumber;
    private Integer promoCellNumber;
    private Integer netCostCellNumber;
}
