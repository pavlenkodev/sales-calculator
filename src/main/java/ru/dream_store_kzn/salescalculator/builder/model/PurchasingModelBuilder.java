package ru.dream_store_kzn.salescalculator.builder.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.dream_store_kzn.salescalculator.entity.PurchasingEntity;
import ru.dream_store_kzn.salescalculator.model.PurchasingModel;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PurchasingModelBuilder {

    public static PurchasingModel build(PurchasingEntity entity) {
        var model = new PurchasingModel();
//        model.setId(entity.getId());
//        model.setNumber(entity.getNumber());
//        model.setCostRub(entity.getCostRub());
//        model.setDeliveryCostRub(entity.getDeliveryCostRub());
//        model.setCostYuan(entity.getCostYuan());
//        model.setDeliveryCostYuan(entity.getDeliveryCostYuan());
//        model.setRateYuanRub(entity.getRateYuanRub());
//        model.setFrom(entity.getFrom());
//        model.setOrderDt(entity.getOrderDt());
//        model.setReceiveDt(entity.getReceiveDt());
//        model.setWeight(entity.getWeight());
//        model.setCostFromWeightKg(entity.getCostFromWeightKg());
//        model.setCreateDt(entity.getCreateDt());
//        model.setChangeDt(entity.getChangeDt());

        return model;
    }
}
