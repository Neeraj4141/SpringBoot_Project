package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.CricketTeamDTO;

public class CricketTeamForm extends BaseForm {

	@NotEmpty(message = "PlayerName is required")
	private String playerName;
	@NotEmpty(message = "playerAddress is required")
	private String playerAddress;
	@NotEmpty(message = "playerType is required")
	private String playerType;
	@NotNull(message = "playerAge is required")
	private Long playerAge;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerAddress() {
		return playerAddress;
	}

	public void setPlayerAddress(String playerAddress) {
		this.playerAddress = playerAddress;
	}

	public String getPlayerType() {
		return playerType;
	}

	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
	public Long getPlayerAge() {
		return playerAge;
	}

	public void setPlayerAge(Long playerAge) {
		this.playerAge = playerAge;
	}

	@Override
	public BaseDTO getDto() {
		CricketTeamDTO dto = new CricketTeamDTO();
		dto.setPlayerName(playerName);
		dto.setPlayerAddress(playerAddress);
		dto.setPlayerType(playerType);
		dto.setPlayerAge(playerAge);
		return dto;
	}

}
