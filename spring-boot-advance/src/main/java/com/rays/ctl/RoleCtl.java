package com.rays.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PostMapping("Update")
	public ORSResponse update(@RequestBody RoleForm form) {

		ORSResponse res = new ORSResponse();

		RoleDTO dto = new RoleDTO();

		dto.setName(form.getName());
		dto.setDescription(form.getDiscription());

		roleservice.update(dto);

		res.setSuccess(true);
		res.addData(dto);
		res.addMessage("role updated successfully");

		return res;

	}

	@PostMapping("Delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {
		
		ORSResponse res = new ORSResponse();
		
		if (ids != null && ids.length > 0) {
	
		for (long id : ids) {
			roleservice.delete(id);
			res.addMessage("role deleted successfully");
			res.setSuccess(true);
		}
		}else {
			res.addMessage("select at least one record");
		}
		return res;
	}
	
	@GetMapping("Get/{id}")
	public ORSResponse get (@PathVariable(required = false) long id) {
		ORSResponse res = new ORSResponse();
		RoleDTO dto = roleservice.findById(id);
		if(dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		}else {
			res.addMessage("Role not found im DataBase");
		}
		return res;
	}
	

}
