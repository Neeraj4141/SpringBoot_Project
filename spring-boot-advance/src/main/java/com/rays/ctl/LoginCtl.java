package com.rays.ctl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.LoginForm;
import com.rays.form.UserForm;
import com.rays.service.UserService;

@RestController
@RequestMapping("Auth")
public class LoginCtl extends BaseCtl {

	@Autowired
	UserService service;

	@PostMapping("/login")
	public ORSResponse login(@RequestBody @Validated LoginForm form, BindingResult buindingresult,
			HttpSession session) {

		ORSResponse res = new ORSResponse();

		res = validate(buindingresult);
		if (res.isSuccess() == false) {
			return res;
		}

		UserDTO dto = new UserDTO();

		dto.setLogin(form.getLogin());
		dto.setPassword(form.getPassword());

		dto = service.Authenticate(dto.getLogin(), dto.getPassword());

		if (dto != null) {
			session.setAttribute("user", dto);
			res.addData(dto);
			res.setSuccess(true);

		} else {
			res.addMessage("Invalid Login Id And Password");
		}
		return res;

	}

}
