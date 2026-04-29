package com.rays.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.RoleDTO;
import com.rays.form.RoleForm;
import com.rays.service.RoleService;

@RestController
@RequestMapping(value = "Role")
public class RoleCtl {

	@Autowired
	RoleService roleservice;

	@PostMapping("Save")
	public ORSResponse save(@RequestBody RoleForm form) {

		ORSResponse res = new ORSResponse();

		RoleDTO dto = new RoleDTO();

		dto.setName(form.getName());
		dto.setDescription(form.getDiscription());

		long id = roleservice.add(dto);

		if (id != 0 && id > 0) {

			res.setSuccess(true);
			res.addData(dto);
			res.addMessage("role added sucessfully");

		} else {
			System.out.println("error in role");
		}
		return res;

	}

}
