package com.placki.companyresources.controllers;

import com.placki.companyresources.dto.CreateResourceRequest;
import com.placki.companyresources.dto.CreateResourceResponse;
import com.placki.companyresources.model.Resource;
import com.placki.companyresources.repositories.ResourcesRepository;
import com.placki.companyresources.services.ResourcesService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReactController {


    private final ResourcesService resourcesService;

    public ReactController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @GetMapping("/api/message")
    public String getMessage() {
        return "Hello from Spring Boot!";
    }

    @GetMapping("api/resource")
    public ResponseEntity<Iterable<Resource>> getAllResourcesContainingKeyword(@RequestParam String keyword, @RequestParam String searchType, @RequestParam String sortBy, @RequestParam String order) {

        return new ResponseEntity<>(resourcesService.getAllResourcesContainingKeyword(keyword, searchType, sortBy, order), HttpStatus.OK);
    }

    @PostMapping("api/resource/")
    public ResponseEntity<CreateResourceResponse> addResource(@RequestBody CreateResourceRequest resource) {
        return new ResponseEntity<>(resourcesService.createResource(resource), HttpStatus.CREATED);
    }

    @GetMapping("/api/resources/")
    public Iterable<Resource> getAllResources(@RequestParam(defaultValue = "name") String sortBy,
                                              @RequestParam(defaultValue = "asc") String order) {
        return resourcesService.getAllResources(sortBy, order);
    }
    
}

