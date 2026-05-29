package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseForm;

public class ATMForm extends BaseForm {

	@NotEmpty(message = "bankName is  required")
	public String bankName;
	@NotEmpty(message = "location is required")
	public String location;
	@NotNull(message = "cashAvailable is required")
	public Long cashAvailable;
	@NotNull(message = "code  is required")
	public Long code;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getCashAvailable() {
		return cashAvailable;
	}

	public void setCashAvailable(Long cashAvailable) {
		this.cashAvailable = cashAvailable;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

}
