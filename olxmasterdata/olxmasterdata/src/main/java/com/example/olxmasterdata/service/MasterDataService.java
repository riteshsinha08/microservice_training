package com.example.olxmasterdata.service;

import com.example.olxmasterdata.dto.CategoriesResponseDTO;
import com.example.olxmasterdata.dto.StatusResponseDTO;

public interface MasterDataService {

	public CategoriesResponseDTO getAllCategories();
	public StatusResponseDTO getAllAdvertiseStatus();
}
