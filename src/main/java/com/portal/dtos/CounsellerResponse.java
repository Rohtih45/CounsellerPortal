package com.portal.dtos;

import java.util.List;

import com.portal.entity.Student;

import lombok.Data;

@Data
public class CounsellerResponse {
	
	private Integer counseller_id;
	
	private String name;
	
	private String email;
	
	private String pwd;
	
	private List<Student> students;

}
