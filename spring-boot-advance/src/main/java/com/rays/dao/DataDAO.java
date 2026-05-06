package com.rays.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import com.rays.common.ORSResponse;
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

	public List<DataDTO> serach(DataDTO dto, int pageNo, int pageSize) {

		List<DataDTO> list = null;

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DataDTO> cq = builder.createQuery(DataDTO.class);
		Root<DataDTO> qroot = cq.from(DataDTO.class);
		cq.select(qroot);
		TypedQuery<DataDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		list = tq.getResultList();

		return list;
	}
}
