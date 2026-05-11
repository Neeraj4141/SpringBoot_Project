package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.CricketTeamDTO;

@Repository
public class CricketTeamDAO {

	@Autowired
	public EntityManager entitymanager;

	public long add(CricketTeamDTO dto) {
		entitymanager.persist(dto);
		return dto.getId();
	}

	public void update(CricketTeamDTO dto) {
		entitymanager.merge(dto);

	}

	public void delete(CricketTeamDTO dto) {
		entitymanager.remove(dto);

	}

	public CricketTeamDTO findByPk(long id) {
		CricketTeamDTO dto = entitymanager.find(CricketTeamDTO.class, id);
		return dto;

	}

	public List<CricketTeamDTO> search(CricketTeamDTO dto, int pageNo, int pageSize) {

		List<CricketTeamDTO> list = null;

		CriteriaBuilder builder = entitymanager.getCriteriaBuilder();
		CriteriaQuery<CricketTeamDTO> cq = builder.createQuery(CricketTeamDTO.class);
		Root<CricketTeamDTO> qroot = cq.from(CricketTeamDTO.class);
		List<Predicate> predicatelist = new ArrayList<Predicate>();
		if (dto != null) {
			if (dto.getId() != null && dto.getId() > 0) {
				predicatelist.add(builder.equal(qroot.get("id"), dto.id));
			}
			if (dto.getPlayerName() != null && dto.getPlayerName().length() > 0) {
				predicatelist.add(builder.like(qroot.get("playerName"), dto.getPlayerName() + "%"));
			}
		}

		cq.where(predicatelist.toArray(new Predicate[predicatelist.size()]));

		TypedQuery<CricketTeamDTO> tq = entitymanager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}
		list = tq.getResultList();
		return list;

	}

}
