package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "PASSWORD")
public class PasswordDTO extends BaseDTO {

	@Column(name = "PassowrdCode", length = 50)
	private String passwordCode;
	@Column(name = "UserName", length = 50)
	private String userName;
	@Column(name = "PasswordValue", length = 50)
	private String passwordValue;
	@Column(name = "Status", length = 50)
	private String status;

	public String getPasswordCode() {
		return passwordCode;
	}

	public void setPasswordCode(String passwordCode) {
		this.passwordCode = passwordCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordValue() {
		return passwordValue;
	}

	public void setPasswordValue(String passwordValue) {
		this.passwordValue = passwordValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}