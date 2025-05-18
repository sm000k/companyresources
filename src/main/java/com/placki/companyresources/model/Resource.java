package com.placki.companyresources.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Resource {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private LocalDate bookingDate;
    private BigDecimal priceUSD;
    private BigDecimal pricePLN;
}
