package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.DeviceDAO;
import com.rays.dto.DeviceDTO;


@Service
public class DeviceService {

	@Autowired
	public DeviceDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(DeviceDTO dto) {
		long pk = dao.add(dto);
		return pk;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(DeviceDTO dto) {
		dao.update(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			DeviceDTO dto = findByPk(id);
			dao.delete(dto);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public DeviceDTO findByPk(long id) {
		DeviceDTO dto = dao.findByPk(id);
		return dto;
	}


	public List<DeviceDTO> search(DeviceDTO dto, int pageNo, int pageSize){
		return dao.search(dto, pageNo, pageSize);
	
		
	}
}
