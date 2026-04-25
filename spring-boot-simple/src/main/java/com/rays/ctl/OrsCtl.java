package com.rays.ctl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.TestDTO;

@RestController
@RequestMapping(value = "ors")
public class OrsCtl {

	@GetMapping("display")
	public ORSResponse display() {
		ORSResponse res = new ORSResponse();
		return res;
	}

	@GetMapping("display1")
	public ORSResponse dispaly1() {
		ORSResponse res = new ORSResponse();
		res.addMessage("invailid login or password");
		return res;
	}

	@GetMapping("display2")
	public ORSResponse dispaly2() {
		ORSResponse res = new ORSResponse();
		Map<String, String> errors = new HashMap<String, String>();
		errors.put("firstName", "firstName is required");
		errors.put("lastName", "lastName is required");
		errors.put("login", "login is required");
		errors.put("password", "password is required");

		res.addInputError(errors);

		return res;

	}

	@GetMapping("display3")
	public ORSResponse display3() {
		ORSResponse res = new ORSResponse();

		List list = new ArrayList<>();

		TestDTO dto = new TestDTO();

		dto.setFirstName("Ram");
		dto.setLastName("Sharma");
		dto.setLogin("ram@gmail.com");
		dto.setPassword("ram123");

		list.add(dto);

		TestDTO dto1 = new TestDTO();

		dto1.setFirstName("aman");
		dto1.setLastName("gupta");
		dto1.setLogin("aman@gmail.com");
		dto1.setPassword("aman123");

		list.add(dto1);

		res.addData(list);

		return res;
	}

	@GetMapping("display4")
	public ORSResponse display4() {
		ORSResponse res = new ORSResponse();

		List roleList = new ArrayList();

		roleList.add("admin");
		roleList.add("student");
		roleList.add("college");
		roleList.add("faculty");
		roleList.add("kiosk");

		res.addResult("roleList", roleList);

		return res;
	}

}
