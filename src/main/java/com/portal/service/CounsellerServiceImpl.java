package com.portal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dtos.CounsellerResponse;
import com.portal.dtos.CourseDto;
import com.portal.dtos.DashboardResponse;
import com.portal.dtos.LoginDto;
import com.portal.dtos.RegisterDto;
import com.portal.entity.CounsellerEntity;
import com.portal.entity.Student;
import com.portal.repository.CounsellerRepo;

@Service
public class CounsellerServiceImpl implements CounsellerService {

	@Autowired
	private CounsellerRepo repo;

	@Override
	public CounsellerResponse loginCounseller(LoginDto dto) {
		// TODO Auto-generated method stub

		CounsellerResponse counsellerDto = new CounsellerResponse();

		BeanUtils.copyProperties(counsellerDto, repo.findByEmail(dto.getEmail()));
		return counsellerDto;
	}

	@Override
	public Boolean registerCounseller(RegisterDto dto) {
		// TODO Auto-generated method stub
		CounsellerEntity entity = new CounsellerEntity();
		BeanUtils.copyProperties(dto, entity);
		return repo.save(entity) != null ? true : false;
	}

	@Override
	public DashboardResponse getDashboardDetails(Integer id) {
		// TODO Auto-generated method stub
		Optional<CounsellerEntity> byId = repo.findById(id);

		CounsellerEntity counsellerEntity = null;

		if (byId.isPresent()) {
			counsellerEntity = byId.get();
		}

		DashboardResponse dashboardDto = new DashboardResponse();

		List<Student> students = counsellerEntity.getStudents();

		dashboardDto.setTotalEnquires(students.size());

		dashboardDto.setOpenEnquires(students.stream().filter(student -> student.getStatus().equals("new"))
				.collect(Collectors.toList()).size());

		dashboardDto.setEnrolledEnquires(students.stream().filter(student -> student.getStatus().equals("enrolled"))
				.collect(Collectors.toList()).size());

		dashboardDto.setLostEnquires(students.stream().filter(student -> student.getStatus().equals("lost"))
				.collect(Collectors.toList()).size());

		return dashboardDto;
	}

}
