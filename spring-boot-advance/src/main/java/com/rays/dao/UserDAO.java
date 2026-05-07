package com.rays.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;

@Repository
public class UserDAO {

	@Autowired
	public RoleDAO roledao;

	@PersistenceContext
	public EntityManager entityManager;

	public long add(UserDTO dto) {
		dto = populate(dto);
		entityManager.persist(dto);
		return dto.getId();

	}

	public void update(UserDTO dto) {
		entityManager.merge(dto);
	}

	public void delete(UserDTO dto) {
		entityManager.remove(dto);

	}

	public UserDTO findById(long pk) {
		UserDTO dto = entityManager.find(UserDTO.class, pk);
		return dto;
	}

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {

		List<UserDTO> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);
		Root<UserDTO> qroot = cq.from(UserDTO.class);
		cq.select(qroot);

		TypedQuery<UserDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
			list = tq.getResultList();
		}
		return list;

	}

	public UserDTO populate(UserDTO dto) {
		if (dto.getRoleId() != null && dto.getRoleId() > 0) {
			RoleDTO rdto = roledao.findByPk(dto.getRoleId());
			dto.setRoleName(rdto.getName());
		}
		return dto;
	}

	public UserDTO findByUniqueKey(String attribute, String value) {
		List<UserDTO> list = null;

		UserDTO dto = new UserDTO();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);
		Root<UserDTO> qroot = cq.from(UserDTO.class);
		Predicate condition = builder.equal(qroot.get(attribute), value);
		cq.where(condition);
		TypedQuery<UserDTO> tq = entityManager.createQuery(cq);
		list = tq.getResultList();
		if (list.size() == 1) {
			dto = list.get(0);
		}
		return dto;

	}

}
