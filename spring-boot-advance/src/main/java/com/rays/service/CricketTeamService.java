package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.CricketTeamDAO;
import com.rays.dto.CricketTeamDTO;

@Service
public class CricketTeamService {

	@Autowired
	public CricketTeamDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(CricketTeamDTO dto) {
		long id = dao.add(dto);
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(CricketTeamDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			CricketTeamDTO dto = findByPk(id);
			dao.delete(dto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public CricketTeamDTO findByPk(long id) {
		CricketTeamDTO dto = dao.findByPk(id);
		return dto;

	}

	public List<CricketTeamDTO> search(CricketTeamDTO dto, int pageNo, int pageSize) {
		return dao.search(dto, pageNo, pageSize);

	}

}
