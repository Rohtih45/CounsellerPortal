package com.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portal.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
	
	

}
