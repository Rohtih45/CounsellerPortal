package com.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.portal.dtos.LoginDto;
import com.portal.dtos.RegisterDto;
import com.portal.service.CounsellerService;
import com.portal.service.CourseService;

@Controller
public class CounsellerController {

	@Autowired
	private CounsellerService service;

	private CourseService courseService;

	public String getCourses() {
		courseService.getCourses();
		return null;
	}

	public String loginCounseller(LoginDto dto) {
		return null;
	}

	public String registerCounseller(RegisterDto dto) {
		return null;
	}

	public String getDashBoard(Integer counsellerId) {
		return null;
	}

}
