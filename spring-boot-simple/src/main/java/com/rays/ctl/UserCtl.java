package com.rays.ctl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;

@RestController
@RequestMapping(value="User")
public class UserCtl {
	
	@PostMapping("/save")
	public ORSResponse save() {
		
		ORSResponse res = new ORSResponse();
		
		res.addMessage("Hello User");
		res.setSuccess(true);
		
		return res;
	}

}
