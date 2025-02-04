package ru.dream_store_kzn.salescalculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchasingModel {

    private Integer id;

    private String number;

    private Double costRub;

    private Double deliveryCostRub;

    private Double costYuan;

    private Double deliveryCostYuan;

    private Double rateYuanRub;

    private String from;

    private LocalDate orderDt;

    private LocalDate receiveDt;

    private Double weight;

    private Double costFromWeightKg;

    private LocalDateTime createDt;

    private LocalDateTime changeDt;
}
