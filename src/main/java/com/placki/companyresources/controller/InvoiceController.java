package com.placki.companyresources.controller;

import com.placki.companyresources.model.Resource;
import com.placki.companyresources.service.InvoiceService;
import com.placki.companyresources.utilities.XmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public String generateInvoiceXml() {
        try {
            String fileName = "invoice.xml";
            XmlGenerator.generateXmlFile(invoiceService.getResources(), fileName);
            return "Invoice XML generated successfully: " + fileName;
        } catch (Exception e) {
            return "Error generating XML: " + e.getMessage();
        }
    }

    @PostMapping
    public String addResources(@RequestBody List<Resource> resources) {
        invoiceService.addResources(resources);
        return "Resources added successfully!";
    }
}
