package com.placki.companyresources.repositories;

import com.placki.companyresources.model.Resource;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResourcesRepository extends JpaRepository<Resource, UUID>, ResourcesRepositoryCustom {
}