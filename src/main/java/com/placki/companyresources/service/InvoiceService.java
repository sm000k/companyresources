package com.placki.companyresources.service;

import com.placki.companyresources.model.Resource;
import com.placki.companyresources.repositories.ResourcesRepository;
import com.placki.companyresources.utilities.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final ResourcesRepository resourcesRepository;

    public InvoiceService(ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }

    public Invoice generateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setResources(getResources());
        return invoice;
    }

    public List<Resource> getResources() {
        return resourcesRepository.findAll();
    }
}
