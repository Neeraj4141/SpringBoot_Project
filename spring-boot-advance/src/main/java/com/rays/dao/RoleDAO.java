package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rays.dto.RoleDTO;

@Repository
public class RoleDAO {

	@PersistenceContext
	public EntityManager entitymanager;

	public long add(RoleDTO dto) {
		entitymanager.persist(dto);
		return dto.getId();

	}

}
