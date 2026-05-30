package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.InsurenceDTO;

public class InsurenceForm extends BaseForm {

	@NotEmpty(message = "costomerName Is Required")
	private String costomerName;
	@NotEmpty(message = "policyType Is Required")
	private String policyType;
	@NotNull(message = "amount Id Required")
	private Long amount;
	@NotEmpty(message = "claimStatus Is Required")
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

    @Override
    public BaseDTO getDto() {

        InsurenceDTO dto = new InsurenceDTO();

        dto.setId(id); // agar BaseForm me id hai
        dto.setCostomerName(costomerName);
        dto.setPolicyType(policyType);
        dto.setAmount(amount);
        dto.setClaimStatus(claimStatus);

        return dto;
    }

}
