package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.ATMDTO;

@Repository
public class ATMDAO {

	@PersistenceContext
	public EntityManager entitymanger;

	public Long add(ATMDTO dto) {
		entitymanger.persist(dto);
		return dto.getId();
	}

	public void update(ATMDTO dto) {
		entitymanger.merge(dto);

	}

	public void delete(ATMDTO dto) {
		entitymanger.remove(dto);

	}

	public ATMDTO findbyid(long id) {
		ATMDTO dto = entitymanger.find(ATMDTO.class, id);
		return dto;

	}

	public List<ATMDTO> search(ATMDTO dto, int pageNo, int pageSize) {

		List<ATMDTO> list = null;
		CriteriaBuilder builder = entitymanger.getCriteriaBuilder();
		CriteriaQuery<ATMDTO> cq = builder.createQuery(ATMDTO.class);
		Root<ATMDTO> qroot = cq.from(ATMDTO.class);
		List<Predicate> predicateList = new ArrayList<Predicate>();
		if (dto != null) {
			if (dto.getId() != null && dto.getId() > 0) {
				predicateList.add(builder.equal(qroot.get("id"), dto.getId()));
			}
		}
		cq.where(predicateList.toArray(new Predicate[(predicateList.size())]));
		TypedQuery<ATMDTO> tq = entitymanger.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);

		}
		list = tq.getResultList();
		return list;
	}

}
