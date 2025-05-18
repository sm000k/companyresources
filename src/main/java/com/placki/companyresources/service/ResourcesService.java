package com.placki.companyresources.service;

import com.placki.companyresources.controller.CurrencyRateController;
import com.placki.companyresources.dto.CreateResourceRequest;
import com.placki.companyresources.dto.CreateResourceResponse;
import com.placki.companyresources.model.Resource;
import com.placki.companyresources.repositories.ResourcesRepository;
import com.placki.companyresources.utilities.DateConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class ResourcesService {
    private final ResourcesRepository resourcesRepository;
    private final CurrencyRateController currencyRateController;

    public CreateResourceResponse createResource(CreateResourceRequest resourceRequest) {
        // Pobieranie wartości PLN na podstawie daty i kwoty USD
        Mono<ResponseEntity<Double>> pricePLNMono = currencyRateController
                .ConvertUsdPln(resourceRequest.getBookingDate().toString(), resourceRequest.getPriceUSD());

        // Blokowanie reaktywne, by pobrać wartość PLN (obecne uproszczenie)
        Double pricePLN = pricePLNMono.map(ResponseEntity::getBody).block();

        if (pricePLN == null) {
            throw new IllegalStateException("Nie można pobrać wartości PLN dla podanych danych.");
        }

        // Budowanie obiektu Resource z odpowiednimi wartościami
        Resource resource = Resource.builder()
                .name(resourceRequest.getName())
                .bookingDate(resourceRequest.getBookingDate()) // Użycie faktycznej daty z requestu
                .priceUSD(resourceRequest.getPriceUSD()) // Ustawienie priceUSD jako przeliczonej wartości PLN
                .pricePLN(pricePLN) // Ustawienie również pricePLN
                .build();

        // Zapis do repozytorium
        resourcesRepository.save(resource);

        // Zwracanie odpowiedzi
        return CreateResourceResponse.builder()
                .name(resource.getName())
                .bookingDate(resource.getBookingDate())
                .priceUSD(resource.getPriceUSD())
                .pricePLN(resource.getPricePLN())
                .build();
    }

    public Iterable<Resource> getAllResources(String sortBy, String order) {
        return resourcesRepository.findAll(order.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
    }

    public List<Resource> getAllResourcesContainingKeyword(String keyword, String searchType, String sortBy, String order) {
        return switch (searchType) {
            case "date" -> resourcesRepository.findByBookingDate(
                    DateConverter.stringToLocalDate(keyword),
                    order.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
            default ->
                    resourcesRepository.findByNameContainingIgnoreCase(keyword, order.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        };

    }

}