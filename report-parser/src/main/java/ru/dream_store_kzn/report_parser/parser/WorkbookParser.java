package ru.dream_store_kzn.report_parser.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.dream_store_kzn.report_parser.enums.SaleStatusEnum;
import ru.dream_store_kzn.report_parser.model.SalesItemModel;
import ru.dream_store_kzn.report_parser.properties.SalesReportProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ru.dream_store_kzn.report_parser.util.CellUtils.getAsDouble;
import static ru.dream_store_kzn.report_parser.util.CellUtils.getAsString;

/**
 * Парсер xlsx файлов
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WorkbookParser {

    private final SalesReportProperties salesReportProperties;

    /**
     * Парсинг файла содержащего отчет по продажам
     *
     * @param report Файл, содержащий отчет по продажам
     * @return Список моделей, содержащих информацию о каждый продаже из файла
     */
    public List<SalesItemModel> parseSalesReport(InputStream report) throws IOException {
        log.debug("Parsing sales report ...");
        var workbook = new XSSFWorkbook(report);
        log.debug("Converting report to workbook was successful");

        log.debug("Getting the sales report sheet {}", salesReportProperties.getSheetName());
        var salesReportSheet = workbook.getSheet(salesReportProperties.getSheetName());
        log.debug("Sales report sheet {} found and get", salesReportSheet.getSheetName());

        List<SalesItemModel> purchases = new ArrayList<>(salesReportSheet.getLastRowNum());
        log.debug("Starting iterate from the sales report sheet {}", salesReportSheet.getSheetName());
        salesReportSheet.rowIterator().forEachRemaining(row -> {
            if (SaleStatusEnum.getByDescription(getAsString(row.getCell(0))) != null) {
                log.debug("Sales report row {} found. Starting mapping to model", row.getRowNum());
                purchases.add(SalesItemModel.builder()
                        .id(UUID.randomUUID())
                        .status(SaleStatusEnum.getByDescription(getAsString(row.getCell(salesReportProperties.getSkuCellNumber()))))
                        .sku(getAsString(row.getCell(salesReportProperties.getSkuCellNumber())))
                        .quantity(getAsDouble(row.getCell(salesReportProperties.getQuantityCellNumber())))
                        .marketPlaceFee(getAsDouble(row.getCell(salesReportProperties.getMarketPlaceFeeCellNumber())))
                        .price(getAsDouble(row.getCell(salesReportProperties.getPriceCellNumber())))
                        .promo(getAsDouble(row.getCell(salesReportProperties.getPromoCellNumber())))
                        .netCost(getAsDouble(row.getCell(salesReportProperties.getNetCostCellNumber())))
                        .build());

                log.debug("Ending mapping to model. Row {}", row.getRowNum());
            }

        });
        workbook.close();

        return purchases;
    }
}
