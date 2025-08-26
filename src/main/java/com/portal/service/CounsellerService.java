package com.portal.service;

import com.portal.dtos.CounsellerResponse;
import com.portal.dtos.DashboardResponse;
import com.portal.dtos.LoginDto;
import com.portal.dtos.RegisterDto;
import com.portal.entity.CounsellerEntity;

public interface CounsellerService {
	
	CounsellerResponse loginCounseller(LoginDto dto);
	
	Boolean registerCounseller(RegisterDto dto);
	
	DashboardResponse getDashboardDetails(Integer id);

}
