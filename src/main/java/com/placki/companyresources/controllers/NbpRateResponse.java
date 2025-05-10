package com.placki.companyresources.controllers;

import lombok.Data;

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
        private double mid;
    }
}
