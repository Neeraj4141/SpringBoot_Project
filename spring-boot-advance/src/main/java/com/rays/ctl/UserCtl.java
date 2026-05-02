package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.RoleDTO;
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

	@PostMapping("update")
	public ORSResponse update(@RequestBody UserForm form) {

		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLogin(form.getLogin());
		dto.setPassword(form.getPassword());
		dto.setDob(form.getDob());
		dto.setRoleId(form.getRoleId());
		dto.setRoleName(form.getRoleName());

		service.update(dto);

		res.setSuccess(true);
		res.addData(dto);
		res.addMessage("User Updated Successfully");

		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				service.delete(id);
				res.addMessage("User deleted successfully");
				res.setSuccess(true);
			}
		} else {
			res.addMessage("error in delete user");

		}
		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse findById(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();
		UserDTO dto = service.findById(id);
		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("User not found by id");
		}
		return res;

	}

	@GetMapping("search/{pageNo}")
	public ORSResponse search(@PathVariable(required = false) int pageNo) {

		int pageSize = 5;

		List<UserDTO> list = null;

		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();

		list = service.search(dto, pageNo, pageSize);

		if (list != null) {
			res.addData(list);
			res.setSuccess(true);
		} else {
			res.addMessage("Record Note Found ");
		}
		return res;

	}

}
