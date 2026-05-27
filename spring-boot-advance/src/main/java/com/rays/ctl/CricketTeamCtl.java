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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.CricketTeamDTO;
import com.rays.form.CricketTeamForm;
import com.rays.service.CricketTeamService;

@RestController
@RequestMapping("cricket")
public class CricketTeamCtl extends BaseCtl {

	@Autowired
	public CricketTeamService service;

	@PostMapping("save")
	public ORSResponse add(@RequestBody @Validated CricketTeamForm form, BindingResult bindingresult) {

		ORSResponse res = new ORSResponse(true);

		res = validate(bindingresult);
		if (res.isSuccess() == false) {
			return res;
		}

		CricketTeamDTO dto = new CricketTeamDTO();
		dto.setPlayerName(form.getPlayerName());
		dto.setPlayerAddress(form.getPlayerAddress());
		dto.setPlayerType(form.getPlayerType());
		dto.setPlayerAge(form.getPlayerAge());
		long id = service.add(dto);

		if (id != 0 && id > 0) {
			res.addData(dto);
			res.addMessage("Player Added successfully");
			res.setSuccess(true);
		} else {
			res.addMessage("Error inn Team Add");
		}
		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody CricketTeamForm form) {

		ORSResponse res = new ORSResponse();
		CricketTeamDTO dto = new CricketTeamDTO();

		dto.setId(form.getId());
		dto.setPlayerName(form.getPlayerName());
		dto.setPlayerAddress(form.getPlayerAddress());
		dto.setPlayerType(form.getPlayerType());
		dto.setPlayerAge(form.getPlayerAge());
		service.update(dto);

		res.addData(dto);
		res.addMessage("Data Added successfully");
		res.setSuccess(true);
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
			res.addMessage("Error in Delete Data");
		}
		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse findByPk(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();

		if (id != 0 && id > 0) {
			CricketTeamDTO dto = service.findByPk(id);
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("Record Not Found");
		}
		return res;

	}

	@RequestMapping(value = "search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody CricketTeamForm form, @PathVariable(required = false) int pageNo) {

		int pageSize = 5;

		List<CricketTeamDTO> list = null;

		ORSResponse res = new ORSResponse();
		CricketTeamDTO dto = new CricketTeamDTO();
		dto.setId(form.getId());
		dto.setPlayerName(form.getPlayerName());

		list = service.search(dto, pageNo, pageSize);

		if (list != null) {
			res.setSuccess(true);
			res.addData(list);
		} else {
			res.addMessage("Record Not Found");
		}
		return res;

	}
}
