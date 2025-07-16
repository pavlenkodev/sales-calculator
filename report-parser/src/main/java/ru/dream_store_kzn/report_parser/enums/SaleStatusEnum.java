package ru.dream_store_kzn.report_parser.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Статусы продаж
 */
@Getter
@AllArgsConstructor
public enum SaleStatusEnum {

    IN_PROGRESS("В обработке"),
    CANCELED("Отменен"),
    COMPLETED("Завершен");

    private final String description;


    public static SaleStatusEnum getByDescription(String description) {
        for (SaleStatusEnum value : SaleStatusEnum.values()) {
            if (value.getDescription().equals(description)) {
                return value;
            }
        }

        return null;
    }
}
