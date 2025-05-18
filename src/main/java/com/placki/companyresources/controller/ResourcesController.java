package com.placki.companyresources.controller;

import com.placki.companyresources.dto.CreateResourceRequest;
import com.placki.companyresources.dto.CreateResourceResponse;
import com.placki.companyresources.model.Resource;
import com.placki.companyresources.service.ResourcesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class ResourcesController {


    private final ResourcesService resourcesService;

    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @PostMapping("api/resources")
    public ResponseEntity<CreateResourceResponse> addResource(@RequestBody CreateResourceRequest resource) {
        return new ResponseEntity<>(resourcesService.createResource(resource), HttpStatus.CREATED);
    }

    @GetMapping("/api/resources")
    public ResponseEntity<Iterable<Resource>> getAllResources(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "default") String searchType,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Iterable<Resource> resources;

        if (keyword != null && !keyword.isEmpty()) {
            if (searchType.equals("date")) {
                resources = resourcesService.getResourcesByBookingDateDynamicSorting(LocalDate.parse(keyword), sortBy, order);
            }else{
                resources = resourcesService.getResourcesByName(keyword, sortBy, order);
            }

        } else {
            resources = resourcesService.getAllResources(sortBy, order);
        }

        return ResponseEntity.ok(resources);
    }

}