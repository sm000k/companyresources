package com.placki.companyresources.controller;

import com.placki.companyresources.dto.CreateResourceRequest;
import com.placki.companyresources.dto.CreateResourceResponse;
import com.placki.companyresources.model.Resource;
import com.placki.companyresources.service.ResourcesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ResourcesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ResourcesService resourcesService;

    @InjectMocks
    private ResourcesController resourcesController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(resourcesController).build();
    }

    @Test
    void addResource_ShouldReturnCreatedResource() throws Exception {
        // Arrange
        CreateResourceRequest request = CreateResourceRequest.builder()
                .name("Test Resource")
                .bookingDate(LocalDate.of(2023, 10, 1))
                .priceUSD(new BigDecimal("100"))
                .build();

        CreateResourceResponse response = CreateResourceResponse.builder()
                .name("Test Resource")
                .bookingDate(LocalDate.of(2023, 10, 1))
                .priceUSD(new BigDecimal("100"))
                .pricePLN(new BigDecimal("450"))
                .build();

        when(resourcesService.createResource(request)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Test Resource",
                                    "bookingDate": "2023-10-01",
                                    "priceUSD": 100
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Resource"))
                .andExpect(jsonPath("$.priceUSD").value(100))
                .andExpect(jsonPath("$.pricePLN").value(450));
    }

    @Test
    void getAllResources_ShouldReturnAllResources() throws Exception {
        // Arrange
        when(resourcesService.getAllResources("name", "asc"))
                .thenReturn(List.of(
                        Resource.builder().name("Resource A").priceUSD(new BigDecimal("100")).build(),
                        Resource.builder().name("Resource B").priceUSD(new BigDecimal("200")).build()
                ));

        // Act & Assert
        mockMvc.perform(get("/api/resources")
                        .param("sortBy", "name")
                        .param("order", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Resource A"))
                .andExpect(jsonPath("$[0].priceUSD").value(100))
                .andExpect(jsonPath("$[1].name").value("Resource B"))
                .andExpect(jsonPath("$[1].priceUSD").value(200));
    }

    @Test
    void getAllResources_FilteredByName_ShouldReturnFilteredResources() throws Exception {
        // Arrange
        when(resourcesService.getResourcesByName("Test", "name", "asc"))
                .thenReturn(List.of(
                        Resource.builder().name("Test Resource A").priceUSD(new BigDecimal("100")).build(),
                        Resource.builder().name("Test Resource B").priceUSD(new BigDecimal("200")).build()
                ));

        // Act & Assert
        mockMvc.perform(get("/api/resources")
                        .param("keyword", "Test")
                        .param("searchType", "name")
                        .param("sortBy", "name")
                        .param("order", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Resource A"))
                .andExpect(jsonPath("$[1].name").value("Test Resource B"));
    }

    @Test
    void getAllResources_FilteredByDate_ShouldReturnFilteredResources() throws Exception {
        // Arrange
        when(resourcesService.getResourcesByBookingDateDynamicSorting(LocalDate.of(2023, 10, 1), "priceUSD", "asc"))
                .thenReturn(List.of(
                        Resource.builder().name("Resource A").priceUSD(new BigDecimal("100")).build(),
                        Resource.builder().name("Resource B").priceUSD(new BigDecimal("200")).build()
                ));

        // Act & Assert
        mockMvc.perform(get("/api/resources")
                        .param("keyword", "2023-10-01")
                        .param("searchType", "date")
                        .param("sortBy", "priceUSD")
                        .param("order", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Resource A"))
                .andExpect(jsonPath("$[1].name").value("Resource B"));
    }
}