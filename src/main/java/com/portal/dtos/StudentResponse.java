package com.portal.dtos;

import com.portal.entity.CounsellerEntity;
import com.portal.entity.CourseEntity;

import lombok.Data;

@Data
public class StudentResponse {
	
	private Integer s_id;
	
	private String name;
	
	private Integer phno;
	
	private String classMode;
	
	private String status;
	
	private String course;
	
	private CounsellerEntity counseller;

}
