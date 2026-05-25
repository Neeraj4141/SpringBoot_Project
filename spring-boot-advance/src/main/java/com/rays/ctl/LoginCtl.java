package com.rays.ctl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserService;

@RestController
@RequestMapping("Auth")
public class LoginCtl extends BaseCtl {

	@Autowired
	UserService service;
	
	@PostMapping("/signup")
	public ORSResponse save(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;
		}

		UserDTO dto = (UserDTO) form.getDto();

		long pk = service.add(dto);
		res.addData(dto);
		res.addMessage("User Register successfully");
		res.setSuccess(true);

		return res;
	}

	@PostMapping("/login")
	public ORSResponse login(@RequestBody @Validated LoginForm form, BindingResult buindingresult,
			HttpSession session) {

		ORSResponse res = new ORSResponse(true);

		res = validate(buindingresult);
		if (res.isSuccess() == false) {
			return res;
		}

		UserDTO dto = new UserDTO();

		dto.setLoginId(form.getLogin());
		dto.setPassword(form.getPassword());

		dto = service.Authenticate(dto.getLoginId(), dto.getPassword());

		if (dto != null) {
			session.setAttribute("user", dto);
			res.addData(dto);
			res.setSuccess(true);

		} else {
			res.addMessage("Invalid Login Id And Password");
		}
		return res;

	}

	@PostMapping("/logout")
	public ORSResponse logOut(HttpSession session) {

		ORSResponse res = new ORSResponse();
		session.invalidate();
		res.addMessage("User LogOut Successfully");
		return res;

	}

}
