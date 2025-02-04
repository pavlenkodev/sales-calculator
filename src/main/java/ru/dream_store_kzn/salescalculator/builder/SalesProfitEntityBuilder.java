package ru.dream_store_kzn.salescalculator.builder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.dream_store_kzn.salescalculator.model.ProfitModel;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SalesProfitEntityBuilder {

    public static SalesProfitEntity build(ProfitModel profitModel) {
        var entity = new SalesProfitEntity();
        entity.setTotalProfit(profitModel.getTotalProfit());


        entity.setPeriodBeginDt(LocalDateTime.now());
        entity.setPeriodEndDt(LocalDateTime.now());
        return entity;
    }
}
