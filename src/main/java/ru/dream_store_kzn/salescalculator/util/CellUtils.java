package ru.dream_store_kzn.salescalculator.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Утилитарный класс для работы с ячейками xlsx файла
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CellUtils {

    /**
     * Получение содержимого ячейки в виде строки
     *
     * @param cell Ячейка
     * @return Строковое представление значения из ячейки
     */
    public static String getAsString(Cell cell) {
        if (cell == null) {
            return StringUtils.EMPTY;
        }
        return cell.getStringCellValue();
    }

    /**
     * Получение содержимого ячейки в виде {@link Double}
     *
     * @param cell Ячейка
     * @return Значение ячейки в формате  {@link Double}
     */
    public static Double getAsDouble(Cell cell) {
        if (cell == null) {
            return null;
        }
        return cell.getNumericCellValue();
    }
}
