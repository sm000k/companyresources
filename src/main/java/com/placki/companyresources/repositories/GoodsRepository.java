package com.placki.companyresources.repositories;

import com.placki.companyresources.model.Goods;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;



public interface GoodsRepository extends JpaRepository<Goods, UUID>{

}
