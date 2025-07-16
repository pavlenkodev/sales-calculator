package ru.dream_store_kzn.report_parser.service;

import ru.dream_store_kzn.report_parser.model.ProfitModel;

import java.io.InputStream;

/**
 * Сервис, осуществляющий работу с прибылью
 */
public interface ProfitService {

    /**
     * Подсчет прибыли из отчета по продажам
     *
     * @param salesReport Файл, содержащий отчет по продажам
     * @param paidStorageReport Файл, содержащий отчет по платному хранению
     * @return Модель, содержащая результаты подсчета прибыли
     */
    ProfitModel calculateProfit(InputStream salesReport, InputStream paidStorageReport);
}
