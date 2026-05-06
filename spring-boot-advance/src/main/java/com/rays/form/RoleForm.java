package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseForm;

public class RoleForm extends BaseForm {

	@NotEmpty(message = "name is required")
	private String name;
	@NotEmpty(message = "description is required")
	private String discription;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

}
