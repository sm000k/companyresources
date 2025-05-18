package com.placki.companyresources.controller;

import com.placki.companyresources.service.InvoiceService;
import com.placki.companyresources.utilities.XmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> generateInvoiceXml() {
        Map<String, String> response = new HashMap<>();
        try {
            String directoryPath = "src/main/resources/invoices";
            String filePath = directoryPath + "/invoice.xml";

            File directory = new File(directoryPath);
            if (!directory.exists() && !directory.mkdirs()) {
                throw new IllegalStateException("Failed to create directory: " + directoryPath);
            }

            XmlGenerator.generateXmlFile(invoiceService.getResources(), filePath);

            response.put("message", "Invoice XML generated successfully");
            response.put("filePath", filePath);
        } catch (Exception e) {
            response.put("message", "Error generating XML");
            response.put("error", e.getMessage());
        }
        return response;
    }
}
