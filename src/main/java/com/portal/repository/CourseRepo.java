package com.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portal.entity.CourseEntity;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, Integer>{

	public CourseEntity findByName(String name);

}
