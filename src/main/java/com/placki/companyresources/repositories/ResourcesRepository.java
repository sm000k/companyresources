package com.placki.companyresources.repositories;

import com.placki.companyresources.model.Resource;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourcesRepository extends JpaRepository<Resource, UUID> {


    @Query("SELECT r FROM Resource r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Resource> findByNameContainingIgnoreCase(String keyword);


    @Query("SELECT r FROM Resource r WHERE r.bookingDate = :keyword")
    List<Resource> findByBookingDate(LocalDate keyword);

    @Query("SELECT r FROM Resource r WHERE r.bookingDate =:localDate")
    List<Resource> findByBookingDate(LocalDate localDate, Sort asc);

    @Query("SELECT r FROM Resource r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Resource> findByNameContainingIgnoreCase(String keyword, Sort asc);
}
