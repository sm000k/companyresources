package com.placki.companyresources.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Goods {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private LocalDate bookingDate;
    private double priceUSD;
    private double pricePLN;
}
