package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseForm;

public class PasswordForm extends BaseForm {

	@NotEmpty(message = "Passwordcode is Required")
	private String passwordCode;
	@NotEmpty(message = "UserName ids Required")
	private String userName;
	@NotEmpty(message = "PasswordValue ids Required")
	private String passwordValue;
	@NotEmpty(message = "Status ids Required")
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
