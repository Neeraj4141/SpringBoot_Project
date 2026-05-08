package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.StudentDAO;
import com.rays.dto.StudentDTO;

@Service
public class StudentService {

	@Autowired
	public StudentDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(StudentDTO dto) {
		long id = dao.add(dto);
		return id;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(StudentDTO dto) {
		dao.update(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {

		try {
			StudentDTO dto = findByPk(id);
			dao.delete(dto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Transactional(readOnly = true)
	public StudentDTO findByPk(long pk) {
		StudentDTO dto = dao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) {

		// return dao.search(dto, pageNo, pageSize);

		List<StudentDTO> list = dao.search(dto, pageNo, pageSize);
		return list;
	}

}
