package com.placki.companyresources.service;

import com.placki.companyresources.model.Resource;
import com.placki.companyresources.utilities.Invoice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private final List<Resource> resources = new ArrayList<>();

    public Invoice generateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setComputers(resources);
        return invoice;
    }

    public void addResources(List<Resource> newResources) {
        resources.addAll(newResources);
    }

    public List<Resource> getResources() {
        return resources; // Expose the list of resources for XML generation
    }
}
