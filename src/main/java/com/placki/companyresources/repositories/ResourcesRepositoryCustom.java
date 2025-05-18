package com.placki.companyresources.repositories;

import com.placki.companyresources.model.Resource;

import java.time.LocalDate;
import java.util.List;

public interface ResourcesRepositoryCustom {
    List<Resource> getResourcesByBookingDate(LocalDate date, String sortBy, String order);
    List<Resource> getResourcesByName(String keyword, String sortBy, String order);
}
