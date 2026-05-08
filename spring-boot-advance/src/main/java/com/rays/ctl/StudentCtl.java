package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.StudentDTO;
import com.rays.form.StudentForm;
import com.rays.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentCtl extends BaseCtl {

	@Autowired
	StudentService service;

	@PostMapping("save")
	public ORSResponse add(@RequestBody @Validated StudentForm form, BindingResult bindingresult) {

		ORSResponse res = new ORSResponse();
		res = validate(bindingresult);
		if (res.isSuccess() == false) {
			return res;
		}
		StudentDTO dto = new StudentDTO();
		dto.setName(form.getName());
		dto.setSchool(form.getSchool());
		dto.setAddress(form.getAddress());
		dto.setFees(form.getFees());

		long id = service.add(dto);

		if (id != 0 && id > 0) {
			res.setSuccess(true);
			res.addData(dto);
			res.addMessage("Student added Successfully");

		} else {
			res.addMessage("exception in add Student");
		}
		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody @Validated StudentForm form, BindingResult bindingresult) {

		ORSResponse res = new ORSResponse();
		res = validate(bindingresult);
		if (res.isSuccess() == false) {
			return res;
		}

		StudentDTO dto = new StudentDTO();

		dto.setId(form.getId());
		dto.setName(form.getName());
		dto.setSchool(form.getSchool());
		dto.setAddress(form.getAddress());
		dto.setFees(form.getFees());

		service.update(dto);
		res.setSuccess(true);
		res.addData(dto);
		res.addMessage("Data Updated SuccessFully");

		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				service.delete(id);
				res.addMessage("Student deleted successfully");
				res.setSuccess(true);
			}

		} else {
			res.addMessage("Record No found For delete");
		}
		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse findByPk(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();
		StudentDTO dto = service.findByPk(id);

		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage(" Record Not Found");
		}
		return res;
	}

	@GetMapping("search/{pageNo}")
	public ORSResponse serach(@PathVariable(required = false) int pageNo) {

		int pageSize = 5;
		ORSResponse res = new ORSResponse();
		StudentDTO dto = new StudentDTO();
		List<StudentDTO> list = service.search(dto, pageNo, pageSize);

		if (list != null) {
			res.addData(list);
			res.setSuccess(true);
		} else {
			res.addMessage("Record Not Found");
		}
		return res;

	}

}
