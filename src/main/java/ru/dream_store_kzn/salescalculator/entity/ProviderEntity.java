package ru.dream_store_kzn.salescalculator.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "provider", schema = "sales_calculator")
public class ProviderEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String name;
    private String phoneNumber;
    private Double price;


    private PurchasingEntity purchaseEntity;
}
