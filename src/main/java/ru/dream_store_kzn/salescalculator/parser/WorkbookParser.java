package ru.dream_store_kzn.salescalculator.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.dream_store_kzn.salescalculator.enums.SaleStatusEnum;
import ru.dream_store_kzn.salescalculator.model.SaleItemModel;
import ru.dream_store_kzn.salescalculator.properties.SalesReportProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ru.dream_store_kzn.salescalculator.util.CellUtils.getAsDouble;
import static ru.dream_store_kzn.salescalculator.util.CellUtils.getAsString;

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
    public List<SaleItemModel> parseSaleReport(InputStream report) throws IOException {
        log.info("Parsing Sale Report ...");
        var workbook = new XSSFWorkbook(report);
        log.info("Converting report to workbook was successful");

        log.info("Getting the sales report sheet {}", salesReportProperties.getSheetName());
        var salesReportSheet = workbook.getSheet(salesReportProperties.getSheetName());
        log.info("Sales report sheet {} found and get", salesReportSheet.getSheetName());

        List<SaleItemModel> purchases = new ArrayList<>(salesReportSheet.getLastRowNum());
        log.info("Starting iterate from the sales report sheet {}", salesReportSheet.getSheetName());
        salesReportSheet.rowIterator().forEachRemaining(row -> {
            if (SaleStatusEnum.getByDescription(getAsString(row.getCell(0))) != null) {
                log.info("Sales report row {} found. Starting mapping to model", row.getRowNum());
                purchases.add(SaleItemModel.builder()
                        .id(UUID.randomUUID())
                        .status(SaleStatusEnum.getByDescription(getAsString(row.getCell(salesReportProperties.getSkuCellNumber()))))
                        .sku(getAsString(row.getCell(salesReportProperties.getSkuCellNumber())))
                        .quantity(getAsDouble(row.getCell(salesReportProperties.getQuantityCellNumber())))
                        .marketPlaceFee(getAsDouble(row.getCell(salesReportProperties.getMarketPlaceFeeCellNumber())))
                        .price(getAsDouble(row.getCell(salesReportProperties.getPriceCellNumber())))
                        .promo(getAsDouble(row.getCell(salesReportProperties.getPromoCellNumber())))
                        .netCost(getAsDouble(row.getCell(salesReportProperties.getNetCostCellNumber())))
                        .build());
                log.info("Ending mapping to model. Row {}", row.getRowNum());
            }

        });
        workbook.close();

        return purchases;
    }

    public Integer handlePaidStorageReport(InputStream report) {
        return 0;
    }
}
