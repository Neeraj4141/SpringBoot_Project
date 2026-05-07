package com.rays.ctl;

import java.io.OutputStream;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentService;
import com.rays.service.UserService;

@RestController
@RequestMapping("user")
public class UserCtl extends BaseCtl {

	@Autowired
	public UserService service;

	@Autowired
	public AttachmentService attservice;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;
		}

		UserDTO dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLogin(form.getLogin());
		dto.setPassword(form.getPassword());
		dto.setDob(form.getDob());
		dto.setRoleId(form.getRoleId());

		long id = service.add(dto);
		if (id != 0 && id > 0) {
			res.setSuccess(true);
			res.addMessage("User added successfully");
			res.addData(dto);
		} else {
			res.addMessage("error in user add");
		}
		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody UserForm form) {

		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLogin(form.getLogin());
		dto.setPassword(form.getPassword());
		dto.setDob(form.getDob());
		dto.setRoleId(form.getRoleId());

		service.update(dto);

		res.setSuccess(true);
		res.addData(dto);
		res.addMessage("User Updated Successfully");

		return res;
	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable(required = false) long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				service.delete(id);
				res.addMessage("User deleted successfully");
				res.setSuccess(true);
			}
		} else {
			res.addMessage("error in delete user");

		}
		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse findById(@PathVariable(required = false) long id) {

		ORSResponse res = new ORSResponse();
		UserDTO dto = service.findById(id);
		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);
		} else {
			res.addMessage("User not found by id");
		}
		return res;

	}

	@GetMapping("search/{pageNo}")
	public ORSResponse search(@PathVariable(required = false) int pageNo) {

		int pageSize = 5;

		List<UserDTO> list = null;

		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();

		list = service.search(dto, pageNo, pageSize);

		if (list != null) {
			res.addData(list);
			res.setSuccess(true);
		} else {
			res.addMessage("Record Note Found ");
		}
		return res;

	}

	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		AttachmentDTO attdto = new AttachmentDTO(file);
		attdto.setDescription("Profile Pic");
		attdto.setUserId(userId);

		UserDTO userdto = service.findById(userId);

		if (userdto.getImageId() != null && userdto.getImageId() > 0) {
			attdto.setId(userdto.getImageId());
		}
		long imageId = attservice.save(attdto);

		if (userdto.getImageId() == null) {
			userdto.setImageId(imageId);
			service.update(userdto);

		}

		ORSResponse res = new ORSResponse();
		res.addResult("imageId", imageId);
		res.addResult("userId", userId);
		res.setSuccess(true);

		return res;

	}
	
	@GetMapping("/profilePic/{userId}")
	public void downloadPic(@PathVariable Long userId, HttpServletResponse response) {
		try {

			UserDTO userDto = service.findById(userId);

			AttachmentDTO attachmentDTO = null;

			if (userDto != null) {
				attachmentDTO = attservice.findByPk(userDto.getImageId());
			}

			if (attachmentDTO != null) {

				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream(); // write byte code on response according to contenttype
				out.write(attachmentDTO.getDoc());
				out.close();

			} else {

				response.getWriter().write("ERROR: File not found");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
