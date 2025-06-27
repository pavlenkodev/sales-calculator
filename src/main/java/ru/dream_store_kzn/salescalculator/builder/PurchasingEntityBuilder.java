package ru.dream_store_kzn.salescalculator.builder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.dream_store_kzn.salescalculator.entity.PurchasingEntity;
import ru.dream_store_kzn.salescalculator.model.PurchasingModel;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchasingEntityBuilder {

    public static PurchasingEntity build(PurchasingModel model) {
        var entity = buildCommon(model);
        entity.setCreateDt(LocalDateTime.now());

        return entity;
    }

    public static PurchasingEntity buildEdit(PurchasingModel model) {
        var entity = buildCommon(model);
        entity.setId(Long.valueOf(model.getId()));

        return entity;
    }


    private static PurchasingEntity buildCommon(PurchasingModel model) {
        var entity = new PurchasingEntity();
        entity.setNumber(model.getNumber());
        entity.setCostRub(model.getCostRub());
        entity.setDeliveryCostRub(model.getDeliveryCostRub());
        entity.setCostYuan(model.getCostYuan());
        entity.setDeliveryCostYuan(model.getDeliveryCostYuan());
        entity.setRateYuanRub(model.getRateYuanRub());
        entity.setFrom(model.getFrom());
        entity.setOrderDt(model.getOrderDt());
        entity.setReceiveDt(model.getReceiveDt());
        entity.setWeight(model.getWeight());
        entity.setCostFromWeightKg(model.getCostFromWeightKg());
        entity.setChangeDt(LocalDateTime.now());

        return entity;
    }
}
