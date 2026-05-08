package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseForm;

public class StudentForm extends BaseForm {

	@NotEmpty(message = "name is required")
	private String name;
	@NotEmpty(message = "school is required")
	private String school;
	@NotEmpty(message = "address is required")
	private String address;
	@NotNull(message = "fees is required")
	private Long fees;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getFees() {
		return fees;
	}

	public void setFees(Long fees) {
		this.fees = fees;
	}

}
