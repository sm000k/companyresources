package com.placki.companyresources.repositories;

import com.placki.companyresources.model.Resource;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourcesRepository extends JpaRepository<Resource, UUID>, ResourcesRepositoryCustom {
}