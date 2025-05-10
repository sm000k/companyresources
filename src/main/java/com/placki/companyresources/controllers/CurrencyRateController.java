package com.placki.companyresources.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/api/exchange-rate")
public class CurrencyRateController {
    private final WebClient webClient;


    public CurrencyRateController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.nbp.pl/").build();
    }

    @GetMapping("/usd/{date}/{usdAmount}")
    public Mono<ResponseEntity<String>> getUsdPlnExchangeRate(@PathVariable String date, @PathVariable double usdAmount) {
        String uri = String.format("/api/exchangerates/rates/A/USD/%s/?format=json", date);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(NbpRateResponse.class)
                .map(response -> {
                    double rate = response.getRates().get(0).getMid();
                    double plnValue = usdAmount * rate;
                    String formatted = String.format("USD to PLN on %s: %.4f", date, plnValue);
                    return ResponseEntity.ok(formatted);
                });
    }

}

