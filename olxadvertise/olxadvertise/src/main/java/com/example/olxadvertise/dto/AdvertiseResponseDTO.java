package com.example.olxadvertise.dto;

import java.util.List;

import com.example.olxadvertise.model.Advertise;

public class AdvertiseResponseDTO {

	private List<AdvertiseDTO> advertises;

	public List<AdvertiseDTO> getAdvertises() {
		return advertises;
	}

	public void setAdvertises(List<AdvertiseDTO> advertises) {
		this.advertises = advertises;
	}
}
