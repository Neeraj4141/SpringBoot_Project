package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.InsurenceDAO;
import com.rays.dto.InsurenceDTO;

@Service
public class InsurenceService {

	@Autowired
	InsurenceDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Long add(InsurenceDTO dto) {
		long pk = dao.add(dto);
		return pk;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(InsurenceDTO dto) {
		dao.update(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			InsurenceDTO dto = findbyid(id);
			dao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public InsurenceDTO findbyid(long id) {
		InsurenceDTO dto = dao.findbyid(id);
		return dto;
	}

}
