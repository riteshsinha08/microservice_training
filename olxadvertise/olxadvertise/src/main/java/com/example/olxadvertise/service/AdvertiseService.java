package com.example.olxadvertise.service;

import com.example.olxadvertise.dto.AdvertiseDTO;
import com.example.olxadvertise.dto.AdvertiseResponseDTO;
import com.example.olxadvertise.model.Advertise;

public interface AdvertiseService {

	public AdvertiseDTO postNewAdvertise(Advertise advertise,String token);
	public AdvertiseDTO postNewAdvertise(int id,Advertise advertise,String token);
	public AdvertiseResponseDTO getAllPostedAdvertise(String token);
	public AdvertiseDTO getPostedAdvertiseById(int id,String token);
	public Boolean deletePostedAdvertiseById(int id);
	public AdvertiseResponseDTO getFilterAdvertises();
}
