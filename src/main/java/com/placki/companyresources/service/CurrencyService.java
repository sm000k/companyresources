package com.placki.companyresources.service;

import com.placki.companyresources.dto.NbpRateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
@Service
public class CurrencyService {
    public ResponseEntity<BigDecimal> getPlnValue(String date, BigDecimal usdAmount) {
        String uri = String.format("https://api.nbp.pl/api/exchangerates/rates/A/USD/%s/?format=json", date);

        RestTemplate restTemplate = new RestTemplate();
        NbpRateResponse response = restTemplate.getForObject(uri, NbpRateResponse.class);

        if (response != null && response.getRates() != null && !response.getRates().isEmpty()) {
            BigDecimal rate = response.getRates().get(0).getMid();
            BigDecimal plnValue = usdAmount.multiply(rate);
            return ResponseEntity.ok(plnValue);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
