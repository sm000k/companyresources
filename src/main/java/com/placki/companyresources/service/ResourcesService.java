package com.placki.companyresources.service;

import com.placki.companyresources.controller.CurrencyRateController;
import com.placki.companyresources.dto.CreateResourceRequest;
import com.placki.companyresources.dto.CreateResourceResponse;
import com.placki.companyresources.model.Resource;
import com.placki.companyresources.repositories.ResourcesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ResourcesService {
    private final ResourcesRepository resourcesRepository;
    private final CurrencyRateController currencyRateController;

    public CreateResourceResponse createResource(CreateResourceRequest resourceRequest) {

        Mono<ResponseEntity<BigDecimal>> pricePLNMono = currencyRateController
                .convertUsdPLn(resourceRequest.getBookingDate().toString(), resourceRequest.getPriceUSD());

        BigDecimal pricePLN = pricePLNMono.map(ResponseEntity::getBody).block();

        if (pricePLN == null) {
            throw new IllegalStateException("Unable to fetch PLN value for the provided data.");
        }
        Resource resource = Resource.builder()
                .name(resourceRequest.getName())
                .bookingDate(resourceRequest.getBookingDate())
                .priceUSD(resourceRequest.getPriceUSD())
                .pricePLN(pricePLN)
                .build();

        resourcesRepository.save(resource);

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

    public List<Resource> getResourcesByBookingDateDynamicSorting(LocalDate date, String sortBy, String order) {
        return resourcesRepository.getResourcesByBookingDate(date, sortBy, order);
    }
    public List<Resource> getResourcesByName(String keyword, String sortBy, String order) {
        return resourcesRepository.getResourcesByName(keyword, sortBy, order);
    }
}