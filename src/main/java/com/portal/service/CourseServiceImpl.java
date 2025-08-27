package com.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dtos.CourseDto;
import com.portal.entity.CourseEntity;
import com.portal.repository.CourseRepo;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepo repo;

	public List<String> getCourses() {
		// TODO Auto-generated method stub

		List<String> courseName = new ArrayList<>();

		List<CourseEntity> all = repo.findAll();

		for (CourseEntity courseEntity : all) {

			courseName.add(courseEntity.getName());
		}
		return courseName;
	}

}
