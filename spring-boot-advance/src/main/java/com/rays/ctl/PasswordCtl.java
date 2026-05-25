package com.rays.ctl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.PasswordDTO;
import com.rays.form.PasswordForm;
import com.rays.service.PasswordService;

@RestController
@RequestMapping("password")
public class PasswordCtl extends BaseCtl {

	@Autowired
	public PasswordService service;

	@PostMapping("save")
	public ORSResponse add(@RequestBody @Valid PasswordForm form, BindingResult bindingresult) {

		ORSResponse res = new ORSResponse();
		res = validate(bindingresult);
		if (res.isSuccess() == false) {
			return res;

		}

		PasswordDTO dto = new PasswordDTO();
		dto.setPasswordCode(form.getPasswordCode());
		dto.setUserName(form.getUserName());
		dto.setPasswordValue(form.getPasswordValue());
		dto.setStatus(form.getStatus());
		long id = service.add(dto);
		if (id != 0 && id > 0) {
			res.addData(dto);
			res.addMessage("Password Added Successfully");
			res.setSuccess(true);
		} else {
			res.addMessage("Error in add password");
		}
		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody PasswordForm form) {

		ORSResponse res = new ORSResponse();
		PasswordDTO dto = new PasswordDTO();

		dto.setId(form.getId());
		dto.setPasswordCode(form.getPasswordCode());
		dto.setPasswordValue(form.getPasswordValue());
		dto.setUserName(form.getUserName());
		dto.setStatus(form.getStatus());

		service.update(dto);
		res.addData(dto);
		res.setSuccess(true);
		res.addMessage("Password Updated Successfully");
		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();
		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				service.delete(id);
				res.setSuccess(true);
				res.addMessage("Password Deleted Successfully");
			}
		}

		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse findByPk(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();
		PasswordDTO dto = new PasswordDTO();

		if (id != 0 && id > 0) {
			dto = service.findByPk(id);
			res.setSuccess(true);
			res.addData(dto);
		} else {
			res.addMessage("Record Not Found Of Password");
		}
		return res;

	}

}
