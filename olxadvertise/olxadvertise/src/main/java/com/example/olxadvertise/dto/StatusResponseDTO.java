package com.example.olxadvertise.dto;

import java.util.List;

import com.example.olxadvertise.model.AdvertiseStatus;


public class StatusResponseDTO {

	private List<AdvertiseStatus> statusList;

	public List<AdvertiseStatus> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<AdvertiseStatus> statusList) {
		this.statusList = statusList;
	}
}
