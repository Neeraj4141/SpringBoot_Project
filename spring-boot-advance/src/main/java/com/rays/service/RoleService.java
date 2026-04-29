package com.rays.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.RoleDAO;
import com.rays.dto.RoleDTO;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleDAO roledao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(RoleDTO dto) {
		long pk = roledao.add(dto);
		return pk;

	}

}
