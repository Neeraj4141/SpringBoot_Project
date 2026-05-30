package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rays.dto.InsurenceDTO;

@Repository
public class InsurenceDAO {

	@PersistenceContext
	EntityManager entityManager;

	public Long add(InsurenceDTO dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	public void update(InsurenceDTO dto) {
		entityManager.merge(dto);

	}

	public void delete(InsurenceDTO dto) {
		entityManager.remove(dto);

	}

	public InsurenceDTO findbyid(long id) {
		InsurenceDTO dto = entityManager.find(InsurenceDTO.class, id);
		return dto;
	}

}