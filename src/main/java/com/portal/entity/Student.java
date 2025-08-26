package com.portal.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer s_id;
	
	private String name;
	
	private Integer phno;
	
	private String classMode;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "c_id")
	private CourseEntity course;
	
	@ManyToOne
	@JoinColumn(name = "counseller_id")
	private CounsellerEntity counseller;
	
	@CreationTimestamp
	private LocalDate createdAt;
	
	@UpdateTimestamp
	private LocalDate updatedAt;

	

}
