package com.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.portal.dtos.EnqueryFilterResponse;
import com.portal.dtos.StudentResponse;
import com.portal.entity.CounsellerEntity;
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
		Student student = new Student();

		BeanUtils.copyProperties(dto, student);

		CounsellerEntity counsellerEntity = counsellerRepo.findById(counsellerId).get();

		student.setCounseller(counsellerEntity);

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

		List<Student> students = counsellerEntity.getStudents();

		List<StudentResponse> enqueries = new ArrayList<>();

		// Query By Example

		Student studentEnq = new Student();
		studentEnq.setClassMode(enqueryFilterResponse.getClassMode());
		studentEnq.setCourse(courseRepo.findByName(enqueryFilterResponse.getCourse()));
		studentEnq.setStatus(enqueryFilterResponse.getStatus());

		Example<Student> obj = Example.of(studentEnq);

		List<Student> studentEnqs = repo.findBy(obj, student -> student.all());

		List<StudentResponse> dtos = new ArrayList<>();

		for (Student student : studentEnqs) {
			StudentResponse dto = new StudentResponse();

			BeanUtils.copyProperties(student, dto);

			dtos.add(dto);
		}

		if (!"".equals(enqueryFilterResponse.getClassMode()) && enqueryFilterResponse.getClassMode() != null) {
			List<Student> classModes = students.stream()
					.filter(student -> student.getClassMode().equals(enqueryFilterResponse.getClassMode()))
					.collect(Collectors.toList());
			for (Student student : classModes) {
				StudentResponse dto = new StudentResponse();
				BeanUtils.copyProperties(student, dto);
				enqueries.add(dto);
			}
		}

		if (!"".equals(enqueryFilterResponse.getCourse()) && enqueryFilterResponse.getCourse() != null) {  
			List<Student> Courses = students.stream()
					.filter(student -> student.getCourse().equals(enqueryFilterResponse.getCourse()))
					.collect(Collectors.toList());

			for (Student student : Courses) {
				StudentResponse dto = new StudentResponse();

				BeanUtils.copyProperties(student, dto);

				enqueries.add(dto);
			}

		}

		if (!"".equals(enqueryFilterResponse.getCourse()) && enqueryFilterResponse.getCourse() != null) {

			List<Student> Courses = students.stream()
					.filter(student -> student.getCourse().equals(enqueryFilterResponse.getCourse()))
					.collect(Collectors.toList());

			for (Student student : Courses) {
				StudentResponse dto = new StudentResponse();

				BeanUtils.copyProperties(student, dto);

				enqueries.add(dto);
			}

		}

		return enqueries;
	}

	@Override
	public Boolean updateStudent(StudentResponse studentDto) {
		// TODO Auto-generated method stub
		Student student = new Student();

		BeanUtils.copyProperties(studentDto, student);
		return repo.save(student) != null ? true : false;
	}

}
