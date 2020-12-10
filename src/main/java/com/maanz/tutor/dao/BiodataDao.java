package com.maanz.tutor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maanz.tutor.model.Biodata;

public interface BiodataDao extends JpaRepository<Biodata, Integer>{

}
