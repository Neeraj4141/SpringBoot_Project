package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseForm;

public class CricketTeamForm extends BaseForm {

	@NotEmpty(message = "PlayerName id required")
	private String playerName;
	@NotEmpty(message = "playerAddress is required")
	private String playerAddress;
	@NotEmpty(message = "playerType is required")
	private String playerType;
	@NotNull(message = "playerAge id required")
	private long playerAge;

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

	public long getPlayerAge() {
		return playerAge;
	}

	public void setPlayerAge(long playerAge) {
		this.playerAge = playerAge;
	}

}
