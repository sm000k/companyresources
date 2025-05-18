package com.placki.companyresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Ensure this scans the package containing ResourcesRepository
public class CompanyResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyResourcesApplication.class, args);
    }
}



