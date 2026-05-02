package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_divice")
public class DeviceDTO extends BaseDTO {

	@Column(name = "DIVICECODE", length = 50)
	private String deviceCode;
	@Column(name = "DIVICENAME", length = 50)
	private String deviceName;
	@Column(name = "USERNAME", length = 50)
	private String userName;
	@Column(name = "STATUS", length = 50)
	private String status;

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
