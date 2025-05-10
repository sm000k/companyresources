package com.placki.companyresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.placki.companyresources.model")
public class CompanyresourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyresourcesApplication.class, args);
    }
}



