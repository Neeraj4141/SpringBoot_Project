package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "INSURENCE")
public class InsurenceDTO extends BaseDTO {

	@Column(name = "costomer", length = 50)
	private String costomerName;
	@Column(name = "policyType", length = 50)
	private String policyType;
	@Column(name = "amount")
	private Long amount;
	@Column(name = "claimStatus", length = 50)
	private String claimStatus;

	public String getCostomerName() {
		return costomerName;
	}

	public void setCostomerName(String costomerName) {
		this.costomerName = costomerName;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

}
