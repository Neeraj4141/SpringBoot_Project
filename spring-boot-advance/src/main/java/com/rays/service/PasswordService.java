package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.PasswordDAO;
import com.rays.dto.PasswordDTO;

@Service
public class PasswordService {

	@Autowired
	public PasswordDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(PasswordDTO dto) {
		long id = dao.add(dto);
		return id;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(PasswordDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			PasswordDTO dto = dao.findByPk(id);
			dao.delete(dto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public PasswordDTO findByPk(long id) {
		PasswordDTO dto = dao.findByPk(id);
		return dto;

	}
}
