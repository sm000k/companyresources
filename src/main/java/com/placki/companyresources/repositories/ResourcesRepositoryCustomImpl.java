package com.placki.companyresources.repositories;

import com.placki.companyresources.model.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ResourcesRepositoryCustomImpl implements ResourcesRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Resource> getResourcesByBookingDate(LocalDate date, String sortBy, String order) {
        String jpql = "SELECT r FROM Resource r WHERE r.bookingDate = :date ORDER BY r." + sortBy + " " + order;
        return entityManager.createQuery(jpql, Resource.class)
                .setParameter("date", date)
                .getResultList();
    }
    @Override
    public List<Resource> getResourcesByName(String keyword, String sortBy, String order) {
        String jpql = "SELECT r FROM Resource r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY r." + sortBy + " " + order;
        return entityManager.createQuery(jpql, Resource.class)
            .setParameter("keyword", keyword)
            .getResultList();
    }

}