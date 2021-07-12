package com.example.olxmasterdata.dto;

import java.util.List;

import com.example.olxmasterdata.model.AdvertiseStatus;

public class StatusResponseDTO {

	private List<AdvertiseStatus> statusList;

	public List<AdvertiseStatus> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<AdvertiseStatus> statusList) {
		this.statusList = statusList;
	}
}
