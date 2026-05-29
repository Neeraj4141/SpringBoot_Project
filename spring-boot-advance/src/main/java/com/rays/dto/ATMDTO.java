package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_atm")
public class ATMDTO extends BaseDTO {

	@Column(name = "bankName", length = 50)
	public String bankName;
	@Column(name = "location", length = 50)
	public String location;
	@Column(name = "cashAvailable")
	public Long cashAvailable;
	@Column(name = "code")
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
