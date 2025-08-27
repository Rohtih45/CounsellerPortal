package com.portal.service;

import java.util.List;

import com.portal.dtos.EnqueryFilterResponse;
import com.portal.dtos.StudentResponse;

public interface StudentService {
	
	Boolean addStudent(StudentResponse dto,Integer counsellerId);
	
	StudentResponse getStudent(Integer studentId);
	
	List<StudentResponse> getStudents();
	
	List<StudentResponse> getFilteredEnqueries(EnqueryFilterResponse enqueryFilterResponse, Integer counsellerId);
	
	Boolean updateStudent(StudentResponse studentDto,Integer stuId,Integer counselletId);

}
