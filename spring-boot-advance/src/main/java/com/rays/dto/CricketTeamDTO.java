package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "CRICKET_TEAM")
public class CricketTeamDTO extends BaseDTO {

	@Column(name = "PLAYERNAME", length = 50)
	private String playerName;

	@Column(name = "PLAYERADDRESS", length = 50)
	private String playerAddress;

	@Column(name = "PLAYERTYPE", length = 50)
	private String playerType;

	@Column(name = "PLAYERAGE")
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

}
