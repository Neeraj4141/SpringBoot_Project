package com.rays.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.UserService;

@RestController
@RequestMapping("user")
public class UserCtl {

	@Autowired
	public UserService service;

	@PostMapping("save")
	public ORSResponse save(@RequestBody UserForm form) {

		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLogin(form.getLogin());
		dto.setPassword(form.getPassword());
		dto.setDob(form.getDob());
		dto.setRoleId(form.getRoleId());
		dto.setRoleName(form.getRoleName());

		long id = service.add(dto);
		if (id != 0 && id > 0) {
			res.setSuccess(true);
			res.addMessage("User added successfully");
			res.addData(dto);
		} else {
			res.addMessage("error in user add");
		}
		return res;

	}

}
