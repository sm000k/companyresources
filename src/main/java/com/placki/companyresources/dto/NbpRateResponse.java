package com.placki.companyresources.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class NbpRateResponse {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;
    @Data
    public static class Rate {
        private String no;
        private String effectiveDate;
        private BigDecimal mid;
    }
}
