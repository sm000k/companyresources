package com.placki.companyresources.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.util.UUID;


@AllArgsConstructor
@Data
@EntityScan
public class GoodsModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private LocalDate bookingDate;
    private double priceUSD;
    private double pricePLN;

}
