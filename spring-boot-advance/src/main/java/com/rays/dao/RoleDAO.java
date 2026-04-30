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

	public void update(RoleDTO dto) {
		entitymanager.merge(dto);

	}
	
	public void delete(RoleDTO dto) {
		entitymanager.remove(dto);
		
	}
	public RoleDTO findByPk(long pk) {
		RoleDTO dto = entitymanager.find(RoleDTO.class, pk); // find by id
		return dto;
	} 

}
