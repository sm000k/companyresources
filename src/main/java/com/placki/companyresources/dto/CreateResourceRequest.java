package com.placki.companyresources.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateResourceRequest {
    private String name;
    private LocalDate bookingDate;
    private BigDecimal priceUSD; // Changed from double to BigDecimal
}
