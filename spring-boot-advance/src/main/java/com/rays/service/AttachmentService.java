package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.AttachmentDAO;
import com.rays.dto.AttachmentDTO;

@Service
@Transactional
public class AttachmentService {

	@Autowired
	public AttachmentDAO attdao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(AttachmentDTO dto) {
		long pk = attdao.add(dto);
		return pk;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AttachmentDTO dto) {
		attdao.update(dto);

	}

	public void delete(long id) {

		try {
			AttachmentDTO dto = findByPk(id);
			attdao.delete(dto);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	public AttachmentDTO findByPk(long pk) {
		AttachmentDTO dto = attdao.findByPk(pk);
		return dto;
	}

	public long save(AttachmentDTO dto) {

		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);

		} else {
			id = add(dto);
		}
		return id;

	}
}
