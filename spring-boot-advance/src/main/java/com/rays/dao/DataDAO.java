package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import com.rays.dto.DataDTO;
import com.rays.dto.UserDTO;

@Repository
public class DataDAO {

	@PersistenceContext
	public EntityManager entityManager;

	public long add(DataDTO dto) {
		entityManager.persist(dto);
		return dto.getId();

	}

	public void update(DataDTO dto) {
		entityManager.merge(dto);
	}

	public void delete(DataDTO dto) {
		entityManager.remove(dto);
	}

	public DataDTO findByPk(long pk) {
		DataDTO dto = entityManager.find(DataDTO.class, pk);
		return dto;

	}
}
