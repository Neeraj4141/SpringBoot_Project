package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rays.dto.PasswordDTO;

@Repository
public class PasswordDAO {

	@PersistenceContext
	public EntityManager entitymanager;

	public long add(PasswordDTO dto) {
		entitymanager.persist(dto);
		return dto.getId();
	}

	public void update(PasswordDTO dto) {
		entitymanager.merge(dto);
	}

	public void delete(PasswordDTO dto) {
		entitymanager.remove(dto);
	}

	public PasswordDTO findByPk(long id) {
		PasswordDTO dto = entitymanager.find(PasswordDTO.class, id);
		return dto;
	}

}
