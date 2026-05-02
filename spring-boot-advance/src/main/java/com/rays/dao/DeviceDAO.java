package com.rays.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.DeviceDTO;
import com.rays.dto.RoleDTO;

@Repository
public class DeviceDAO {

	@PersistenceContext
	public EntityManager entitymanager;

	public long add(DeviceDTO dto) {
		entitymanager.persist(dto);
		return dto.getId();

	}

	public void update(DeviceDTO dto) {
		entitymanager.merge(dto);
	}

	public void delete(DeviceDTO dto) {
		entitymanager.remove(dto);

	}

	public DeviceDTO findByPk(long id) {
		DeviceDTO dto = entitymanager.find(DeviceDTO.class, id);
		return dto;

	}

	public List<DeviceDTO> search(DeviceDTO dto, int pageNo, int pageSize) {

		List<DeviceDTO> list = null;

		CriteriaBuilder builder = entitymanager.getCriteriaBuilder();

		CriteriaQuery<DeviceDTO> cq = builder.createQuery(DeviceDTO.class);

		Root<DeviceDTO> qroot = cq.from(DeviceDTO.class);

		cq.select(qroot);

		TypedQuery<DeviceDTO> tq = entitymanager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		list = tq.getResultList();

		return list;
	}
}
