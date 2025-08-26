package com.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portal.entity.CounsellerEntity;

@Repository
public interface CounsellerRepo extends JpaRepository<CounsellerEntity, Integer>{
	
	public CounsellerEntity findByEmail(String email);

}
