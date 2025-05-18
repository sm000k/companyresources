package com.placki.companyresources.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateResourceResponse {
    private String name;
    private LocalDate bookingDate;
    private BigDecimal priceUSD;
    private BigDecimal pricePLN;
}
