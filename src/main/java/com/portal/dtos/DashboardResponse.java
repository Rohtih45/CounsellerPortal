package com.portal.dtos;

import lombok.Data;

@Data
public class DashboardResponse {
	
	private Integer totalEnquires;
	
	private Integer openEnquires;
	
	private Integer enrolledEnquires;
	
	private Integer lostEnquires;

}
