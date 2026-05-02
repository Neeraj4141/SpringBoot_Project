package com.rays.service;

import java.util.List;

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

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(RoleDTO dto) {
		roledao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {

		try {
			RoleDTO dto = findById(id);
			roledao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public RoleDTO findById(long pk) {
		RoleDTO dto = roledao.findByPk(pk);
		return dto;
	}

	public List<RoleDTO> search(RoleDTO dto, int pageNo, int pageSize) {
		return roledao.search(dto, pageNo, pageSize);

	}

}
