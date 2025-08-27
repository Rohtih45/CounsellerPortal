package com.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.portal.dtos.EnqueryFilterResponse;
import com.portal.dtos.StudentResponse;
import com.portal.entity.CounsellerEntity;
import com.portal.entity.CourseEntity;
import com.portal.entity.Student;
import com.portal.repository.CounsellerRepo;
import com.portal.repository.CourseRepo;
import com.portal.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo repo;

	@Autowired
	private CounsellerRepo counsellerRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Override
	public Boolean addStudent(StudentResponse dto, Integer counsellerId) {
		// TODO Auto-generated method stub

		CourseEntity courseEntity = courseRepo.findByName(dto.getCourse().getName());
		Student student = new Student();

		BeanUtils.copyProperties(dto, student);

		CounsellerEntity counsellerEntity = counsellerRepo.findById(counsellerId).get();

		student.setCounseller(counsellerEntity);

		student.setCourse(courseEntity);

		return repo.save(student) != null ? true : false;
	}

	@Override
	public StudentResponse getStudent(Integer studentId) {
		// TODO Auto-generated method stub
		Student student = repo.findById(studentId).get();

		StudentResponse dto = new StudentResponse();

		BeanUtils.copyProperties(student, dto);

		return dto;
	}

	@Override
	public List<StudentResponse> getStudents() {
		// TODO Auto-generated method stub
		List<StudentResponse> studentResponses = new ArrayList<>();

		List<Student> list = repo.findAll();

		for (Student student : list) {
			StudentResponse dto = new StudentResponse();

			BeanUtils.copyProperties(student, dto);

			studentResponses.add(dto);
		}
		return studentResponses;
	}

	@Override
	public List<StudentResponse> getFilteredEnqueries(EnqueryFilterResponse enqueryFilterResponse,
			Integer counsellerId) {
		// TODO Auto-generated method stub
		CounsellerEntity counsellerEntity = counsellerRepo.findById(counsellerId).get();

		// Query By Example

		Student studentEnq = new Student();

		if (!"".equals(enqueryFilterResponse.getClassMode()) && enqueryFilterResponse.getClassMode() != null) {

			studentEnq.setClassMode(enqueryFilterResponse.getClassMode());
		}

		if (!"".equals(enqueryFilterResponse.getCourse()) && enqueryFilterResponse.getCourse() != null) {

			studentEnq.setCourse(courseRepo.findByName(enqueryFilterResponse.getCourse()));
		}

		if (!"".equals(enqueryFilterResponse.getStatus()) && enqueryFilterResponse.getStatus() != null) {
			studentEnq.setStatus(enqueryFilterResponse.getStatus());

		}

		List<Student> studentsEnq = repo.findAll(Example.of(studentEnq));

		List<StudentResponse> dtos = new ArrayList<>();

		for (Student student : studentsEnq) {
			StudentResponse dto = new StudentResponse();

			BeanUtils.copyProperties(student, dto);

			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public Boolean updateStudent(StudentResponse studentDto, Integer stuId, Integer counselletId) {

		CourseEntity courseEntity = courseRepo.findByName(studentDto.getCourse().getName());

		CounsellerEntity counsellerEntity = counsellerRepo.findById(counselletId).orElseThrow();
		// TODO Auto-generated method stub
		Student entity = repo.findById(stuId).orElseThrow();

		BeanUtils.copyProperties(studentDto, entity, "s_id");

		entity.setCounseller(counsellerEntity);

		entity.setCourse(courseEntity);

		return repo.save(entity) != null ? true : false;
	}

}
