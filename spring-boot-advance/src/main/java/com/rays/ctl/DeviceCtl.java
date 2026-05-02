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
import com.rays.dto.DeviceDTO;
import com.rays.form.DeviceForm;
import com.rays.service.DeviceService;

@RestController
@RequestMapping("device")
public class DeviceCtl {

	@Autowired
	public DeviceService service;

	@PostMapping("save")
	public ORSResponse add(@RequestBody DeviceForm form) {

		ORSResponse res = new ORSResponse();
		DeviceDTO dto = new DeviceDTO();

		dto.setDeviceName(form.getDeviceName());
		dto.setDeviceCode(form.getDeviceCode());
		dto.setUserName(form.getUserName());
		dto.setStatus(form.getStatus());

		long id = service.add(dto);

		if (id != 0 && id > 0) {
			res.addData(dto);
			res.addMessage("Device added successfully");
			res.setSuccess(true);

		} else {
			res.addMessage("error in add device");
		}
		return res;
	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody DeviceForm form) {

		ORSResponse res = new ORSResponse();
		DeviceDTO dto = new DeviceDTO();
		service.update(dto);
		dto.setId(form.getId());
		dto.setDeviceCode(form.getDeviceCode());
		dto.setDeviceName(form.getDeviceName());
		dto.setUserName(form.getUserName());
		dto.setStatus(form.getStatus());

		res.addData(dto);
		res.addMessage("Device added successfully");
		res.setSuccess(true);

		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {

			for (long id : ids) {
				service.delete(id);
				res.addMessage("device deleted successfully");
				res.setSuccess(true);
			}

		} else {
			res.addMessage("error in device delete");
		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse findByPk(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();

		DeviceDTO dto = service.findByPk(id);
		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("Device not found");
		}
		return res;
	}

	@GetMapping("search/{pageNo}")
	public ORSResponse search(@PathVariable(required = false) int pageNo) {

		int pageSize = 5;

		ORSResponse res = new ORSResponse();
		DeviceDTO dto = new DeviceDTO();
		List<DeviceDTO> list = service.search(dto, pageNo, pageSize);
		if (list != null) {
			res.setSuccess(true);
			res.addData(list);
		} else {
			res.addMessage("record not found of device");
		}
		return res;

	}
}
