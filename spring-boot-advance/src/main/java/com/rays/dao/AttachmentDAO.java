package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rays.dto.AttachmentDTO;

@Repository
public class AttachmentDAO {

	@PersistenceContext
	public EntityManager entitymanager;

	public long add(AttachmentDTO dto) {
		entitymanager.persist(dto);
		return dto.getId();

	}

	public void update(AttachmentDTO dto) {
		entitymanager.merge(dto);

	}

	public void delete(AttachmentDTO dto) {
		entitymanager.remove(dto);

	}

	public AttachmentDTO findByPk(long pk) {
		AttachmentDTO dto = entitymanager.find(AttachmentDTO.class, pk);
		return dto;

	}

}
