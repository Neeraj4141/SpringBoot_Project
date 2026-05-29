package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.ATMDAO;
import com.rays.dto.ATMDTO;

@Service
public class ATMService {

	@Autowired
	ATMDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Long add(ATMDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(ATMDTO dto) {
		dao.update(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {

		try {
			ATMDTO dto = findbyid(id);
			dao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

	}

	@Transactional(readOnly = true)
	public ATMDTO findbyid(long id) {
		ATMDTO dto = dao.findbyid(id);
		return dto;

	}

	@Transactional(readOnly = true)
	public List<ATMDTO> search(ATMDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);

	}

}
