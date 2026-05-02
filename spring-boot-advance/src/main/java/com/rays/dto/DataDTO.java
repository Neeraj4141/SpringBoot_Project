package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "st_data")
public class DataDTO extends BaseDTO {

	@Column(name = "DATACODE", length = 50)
	private String dataCode;
	@Column(name = "DATATYPE", length = 50)
	private String dataType;
	@Column(name = "DATANAME", length = 50)
	private String dataName;
	@Column(name = "DATASTATUS", length = 50)
	private String dataStatus;

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

}
