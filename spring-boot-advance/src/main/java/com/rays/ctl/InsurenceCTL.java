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
import com.rays.dto.InsurenceDTO;
import com.rays.form.InsurenceForm;
import com.rays.service.InsurenceService;

@RestController
@RequestMapping(value = "insurence")
public class InsurenceCTL extends BaseCtl {

	@Autowired
	InsurenceService service;

	@PostMapping(value = "save")
	public ORSResponse add(@RequestBody @Valid InsurenceForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;
		}
		InsurenceDTO dto = (InsurenceDTO) form.getDto();
		long id = service.add(dto);

		if (id != 0 && id > 0) {
			res.addData(dto);
			res.addMessage("Insurence Addded Successfully!!");
			res.setSuccess(true);
		} else {
			res.addMessage("Error in Insurence Add !!");
		}
		return res;

	}

	@PostMapping(value = "update")
	public ORSResponse update(@RequestBody @Valid InsurenceForm form, BindingResult bindingresult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingresult);
		if (res.isSuccess() == false) {
			return res;

		}

		InsurenceDTO dto = (InsurenceDTO) form.getDto();
		service.update(dto);

		res.addData(dto);
		res.setSuccess(true);
		res.addMessage("insurence Updated Successfully !! ");

		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {
		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				service.delete(id);
				res.addMessage("Insurence deleted Successfully");
				res.setSuccess(true);
			}
		} else {
			res.addMessage("error in delete insurence");

		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse findbyid(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();
		if (id != 0 && id > 0) {
			InsurenceDTO dto = service.findbyid(id);
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("Record not found !!");
		}
		return res;
	}

}