package com.placki.companyresources.controller;

import com.placki.companyresources.model.Resource;
import com.placki.companyresources.service.InvoiceService;
import com.placki.companyresources.utilities.XmlGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class InvoiceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }

    @Test
    void generateInvoiceXml_ShouldReturnSuccessMessage() throws Exception {
        List<Resource> resources = List.of(
                Resource.builder().name("Resource A").priceUSD(new BigDecimal("100")).build(),
                Resource.builder().name("Resource B").priceUSD(new BigDecimal("200")).build()
        );
        when(invoiceService.getResources()).thenReturn(resources);

        try (MockedStatic<XmlGenerator> xmlGenMock = mockStatic(XmlGenerator.class)) {
            xmlGenMock.when(() -> XmlGenerator.generateXmlFile(any(), anyString())).thenAnswer(invocation -> null);

            mockMvc.perform(get("/api/invoices")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Invoice XML generated successfully"))
                .andExpect(jsonPath("$.filePath").exists());
        }
    }

    @Test
    void generateInvoiceXml_shouldReturnErrorMessageOnFailure() throws Exception {
        List<Resource> resources = List.of(
                Resource.builder().name("Resource A").priceUSD(new BigDecimal("100")).build()
        );
        when(invoiceService.getResources()).thenReturn(resources);

        try (MockedStatic<XmlGenerator> xmlGenMock = mockStatic(XmlGenerator.class)) {
            xmlGenMock.when(() -> XmlGenerator.generateXmlFile(any(), anyString()))
                    .thenThrow(new RuntimeException("Test Exception"));

            mockMvc.perform(get("/api/invoices")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value("Error generating XML"))
                    .andExpect(jsonPath("$.error").value("Test Exception"));
        }
    }
}