package com.portal.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "counseller")
public class CounsellerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer counseller_id;
	
	private String name;
	
	private String email;
	
	private String pwd;
	
	private Integer phno;
	
	@OneToMany(mappedBy = "counseller",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Student> students;
	
	@CreationTimestamp
	private LocalDate createdBy;
	
	@UpdateTimestamp
	private LocalDate updatedBy;

}
