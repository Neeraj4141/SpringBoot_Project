package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.DataDAO;
import com.rays.dto.DataDTO;

@Service
public class DataService {

	@Autowired
	public DataDAO datadao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(DataDTO dto) {
		long id = datadao.add(dto);
		return id;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(DataDTO dto) {
		datadao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			DataDTO dto = findByPk(id);
			datadao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

	}

	@Transactional(readOnly = true)
	public DataDTO findByPk(long pk) {
		DataDTO dto = datadao.findByPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<DataDTO> search(DataDTO dto, int pageNo, int pageSize) {
		return datadao.serach(dto, pageNo, pageSize);

	}

}
