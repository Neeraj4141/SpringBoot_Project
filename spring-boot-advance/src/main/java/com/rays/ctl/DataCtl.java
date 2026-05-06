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
import com.rays.dto.DataDTO;
import com.rays.dto.RoleDTO;
import com.rays.form.DataForm;
import com.rays.service.DataService;

@RestController
@RequestMapping("data")
public class DataCtl {

	@Autowired
	public DataService service;

	@PostMapping("save")
	public ORSResponse add(@RequestBody DataForm form) {

		ORSResponse res = new ORSResponse();
		DataDTO dto = new DataDTO();

		dto.setDataCode(form.getDataCode());
		dto.setDataName(form.getDataName());
		dto.setDataType(form.getDataType());
		dto.setDataStatus(form.getDataStatus());

		long id = service.add(dto);

		if (id != 0 && id > 0) {
			res.addData(dto);
			res.addMessage("data Adeddd successfully");
			res.setSuccess(true);
		} else {
			res.addMessage("error in data add");
		}
		return res;
	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody DataForm form) {

		ORSResponse res = new ORSResponse();
		DataDTO dto = new DataDTO();

		dto.setId(form.getId());
		dto.setDataCode(form.getDataCode());
		dto.setDataName(form.getDataName());
		dto.setDataType(form.getDataType());
		dto.setDataStatus(form.getDataStatus());

		service.update(dto);
		res.addData(dto);
		res.setSuccess(true);
		res.addMessage(" data updated successfully");
		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();
		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				service.delete(id);
				res.addMessage("Data Deleted Successfully");
				res.setSuccess(true);
			}
		} else {
			res.addMessage("error in data delete");
		}
		return res;
	}

	@GetMapping("Get/{id}")
	public ORSResponse get(@PathVariable(required = false) long id) {
		ORSResponse res = new ORSResponse();
		DataDTO dto = service.findByPk(id);
		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("Role not found im DataBase");
		}
		return res;
	}

	@GetMapping("search/{pageNo}")
	public ORSResponse search(@PathVariable(required = false) int pageNo) {

		int pageSize = 5;

		List<DataDTO> list = null;
		ORSResponse res = new ORSResponse();
		DataDTO dto = new DataDTO();

		list = service.search(dto, pageNo, pageSize);
		if (list != null) {
			res.addData(list);
			res.setSuccess(true);

		} else {
			res.addMessage("Record not found");
		}
		return res;

	}

}
