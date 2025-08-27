package com.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.portal.dtos.CounsellerResponse;
import com.portal.dtos.DashboardResponse;
import com.portal.dtos.LoginDto;
import com.portal.dtos.RegisterDto;
import com.portal.entity.CounsellerEntity;
import com.portal.service.CounsellerService;
import com.portal.service.CourseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellerController {

	private static final String Integer = null;

	@Autowired
	private CounsellerService service;

	private CourseService courseService;

	public String getCourses() {
		courseService.getCourses();
		return null;
	}

	@GetMapping("/")
	public String getIndex(Model model) {

		CounsellerResponse dto = new CounsellerResponse();
		model.addAttribute("counseller", dto);

		return "index";
	}

	@PostMapping("/login")
	public String loginCounseller(CounsellerResponse reqDto, Model model) {

		LoginDto l_dto = LoginDto.builder().email(reqDto.getEmail()).pwd(reqDto.getPwd()).build();

		String msg = service.loginCounseller(l_dto);

		if (msg.equals("success")) {

			return "redirect:/dashboard";
		} else {
			model.addAttribute("msg", msg);
			model.addAttribute("counseller", new CounsellerResponse());
			return "index";
		}
	}

	@GetMapping("/register")
	public String registerCounseller(Model model) {
		model.addAttribute("counseller", new RegisterDto());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(RegisterDto req, Model model) {
		Boolean status = service.registerCounseller(req);
		
		if(status) {
			return "redirect:/";
		}
		model.addAttribute("msg", "Error");
		return "register";
	}

	@GetMapping("/dashboard")
	public String getDashBoard(Model model, HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		Integer counselletId = (Integer) session.getAttribute("CID");

		DashboardResponse dashboardResponse = service.getDashboardDetails(counselletId);

		model.addAttribute("dashboardinfo", dashboardResponse);

		return "dashboard";
	}

}
