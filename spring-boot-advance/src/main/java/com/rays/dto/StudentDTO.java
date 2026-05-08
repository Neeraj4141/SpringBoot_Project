package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "STUDENT")
public class StudentDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "SCHOOL", length = 50)
	private String school;

	@Column(name = "ADDRESS", length = 50)
	private String address;

	@Column(name = "FEES")
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
