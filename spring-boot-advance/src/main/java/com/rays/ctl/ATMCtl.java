package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.ATMDTO;
import com.rays.form.ATMForm;
import com.rays.service.ATMService;

@RestController
@RequestMapping("Atm")
public class ATMCtl extends BaseCtl {

	@Autowired
	ATMService service;

	@PostMapping("save")
	public ORSResponse add(@RequestBody @Valid ATMForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;
		}

		ATMDTO dto = new ATMDTO();

		dto.setBankName(form.getBankName());
		dto.setCashAvailable(form.getCashAvailable());
		dto.setLocation(form.getLocation());
		dto.setCode(form.getCode());

		long id = service.add(dto);

		if (id != 0 && id > 0) {
			res.addData(dto);
			res.addMessage("ATM Added Successfully !!");
			res.setSuccess(true);
		} else {
			res.addMessage("Error in add Atm !!");
		}
		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid ATMForm form, BindingResult bindingResult) {
		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;

		}
		ATMDTO dto = new ATMDTO();
		dto.setId(form.getId());
		dto.setBankName(form.getBankName());
		dto.setLocation(form.getLocation());
		dto.setCashAvailable(form.getCashAvailable());
		dto.setCode(form.getCode());

		service.update(dto);

		res.addData(dto);
		res.addMessage("Atm addded succeessfully !!");

		return res;

	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				service.delete(id);
				res.addMessage("Atm deleted Successfully!!");
				res.setSuccess(true);

			}

		} else {
			res.addMessage("Error in atm delete");
		}
		return res;

	}

	@GetMapping("get/{id}")

	public ORSResponse findbyid(@PathVariable(required = false) long id) {
		ORSResponse res = new ORSResponse();
		ATMDTO dto = service.findbyid(id);
		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);

		} else {
			res.addMessage("Record not Found of Atm!!");
		}
		return res;

	}

	@RequestMapping(value = "search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody ATMForm form, @PathVariable(required = false) int pageNo) {
		int pageSize = 5;

		ORSResponse res = new ORSResponse();
		ATMDTO dto = (ATMDTO) form.getDto();
		List<ATMDTO> list = service.search(dto, pageNo, pageSize);
		if (list != null) {
			res.addData(list);
			res.setSuccess(true);
		} else {
			res.addMessage("Record not found");
		}
		return res;
	}

}
