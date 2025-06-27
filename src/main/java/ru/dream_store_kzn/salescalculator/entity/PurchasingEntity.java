package ru.dream_store_kzn.salescalculator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "calculator", name = "purchase")
public class PurchasingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    private Long id;
    //Будет формироваться при сохранении из даты + № + id
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

    //    @OneToOne(mappedBy = "purchasingEntity")
    private ProviderEntity providerEntity;
//    Добавить поставщика
}
