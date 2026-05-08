package com.rays.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.mysql.cj.xdevapi.Schema.CreateCollectionOptions;
import com.rays.dto.StudentDTO;

@Repository
public class StudentDAO {

	@PersistenceContext
	public EntityManager entitymanager;

	public long add(StudentDTO dto) {
		entitymanager.persist(dto);
		return dto.getId();

	}

	public void update(StudentDTO dto) {
		entitymanager.merge(dto);

	}

	public void delete(StudentDTO dto) {
		entitymanager.remove(dto);

	}

	public StudentDTO findByPk(long pk) {
		StudentDTO dto = entitymanager.find(StudentDTO.class, pk);
		return dto;

	}

	public List<StudentDTO> search(StudentDTO dto, int pageNo, int pageSize) {

		List<StudentDTO> list = null;
		CriteriaBuilder builder = entitymanager.getCriteriaBuilder();
		CriteriaQuery<StudentDTO> cq = builder.createQuery(StudentDTO.class);
		Root<StudentDTO> qroot = cq.from(StudentDTO.class);
		cq.select(qroot);

		TypedQuery<StudentDTO> tq = entitymanager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
			list = tq.getResultList();

		}
		return list;

	}

}
