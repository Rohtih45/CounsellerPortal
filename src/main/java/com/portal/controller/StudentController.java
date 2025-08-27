package com.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.portal.dtos.CourseDto;
import com.portal.dtos.EnqueryFilterResponse;
import com.portal.dtos.StudentResponse;
import com.portal.service.CourseService;
import com.portal.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

	private static final String Integer = null;

	@Autowired
	private StudentService service;

	@Autowired
	private HttpSession session;

	@Autowired
	private CourseService courseService;

	public String getCourses() {

		return null;
	}

	@GetMapping("/add-enq")
	public String getAddStudent(Model model) {
		model.addAttribute("student", new StudentResponse());

		List<String> courses = courseService.getCourses();

		model.addAttribute("course", courses);

		return "add-enq";
	}

	@PostMapping("/add-enq")
	public String addStudent(@ModelAttribute("student") StudentResponse dto, Model model) {
		Integer counsellerId = (Integer) session.getAttribute("CID");

		Boolean status = service.addStudent(dto, counsellerId);

		if (status) {
			model.addAttribute("smsg", "Student added successfully!");
		} else {
			model.addAttribute("emsg", "Failed to add student!");
		}

		model.addAttribute("student", new StudentResponse());

		return "add-enq";
	}

	@GetMapping("/update-enq/{id}")
	public String getStudent(@PathVariable("id") Integer id, Model model) {

		StudentResponse student = service.getStudent(id);

		model.addAttribute("student", student);

		List<String> courses = courseService.getCourses();

		model.addAttribute("course", courses);

		return "update-enq";
	}

	@PostMapping("/update-enq/{id}")
	public String UpdateStudent(@PathVariable("id") Integer id, @ModelAttribute("student") StudentResponse dto,
			Model model) {

		Integer counsellerId = (Integer) session.getAttribute("CID");
		Boolean status = service.updateStudent(dto, id, counsellerId);
		if (status) {
			model.addAttribute("smsg", "Student added successfully!");
		} else {
			model.addAttribute("emsg", "Failed to add student!");
		}

		model.addAttribute("student", new StudentResponse());
		return "update-enq";
	}

	@GetMapping("/view-enq")
	public String getStudents(Model model) {
		List<StudentResponse> students = service.getStudents();
		model.addAttribute("filter", new EnqueryFilterResponse());

		model.addAttribute("students", students);

		List<String> courses = courseService.getCourses();
		model.addAttribute("course", courses);
		return "view-enq";
	}

	@PostMapping("/view-enq")
	public String getFilteredEnqueries(@ModelAttribute("filter") EnqueryFilterResponse dto, Model model) {
		Integer counsellerId = (Integer) session.getAttribute("CID");

		List<StudentResponse> filteredEnqueries = service.getFilteredEnqueries(dto, counsellerId);
		if (dto.getClassMode().equals("") && dto.getCourse().equals("") && dto.getStatus().equals("")) {
			List<StudentResponse> students = service.getStudents();
			model.addAttribute("students", students);
		} else {
			model.addAttribute("students", filteredEnqueries);
		}

		List<String> courses = courseService.getCourses();
		model.addAttribute("course", courses);

		return "view-enq";
	}

}
