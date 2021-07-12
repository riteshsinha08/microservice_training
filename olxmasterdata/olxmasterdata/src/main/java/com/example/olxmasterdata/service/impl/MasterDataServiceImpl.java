package com.example.olxmasterdata.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.olxmasterdata.dto.CategoriesResponseDTO;
import com.example.olxmasterdata.dto.StatusResponseDTO;
import com.example.olxmasterdata.model.AdvertiseStatus;
import com.example.olxmasterdata.model.Category;
import com.example.olxmasterdata.repository.AdvertiseStatusRepository;
import com.example.olxmasterdata.repository.CategoryRepository;
import com.example.olxmasterdata.service.MasterDataService;

@Service
public class MasterDataServiceImpl implements MasterDataService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AdvertiseStatusRepository advertiseStatusRepository;

	@Override
	public CategoriesResponseDTO getAllCategories() {
		CategoriesResponseDTO dto = new CategoriesResponseDTO();
		List<Category> categories = categoryRepository.findAll();
		dto.setCategories(categories);
		return dto;
	}

	@Override
	public StatusResponseDTO getAllAdvertiseStatus() {
		StatusResponseDTO dto = new StatusResponseDTO();
		List<AdvertiseStatus> statusList = advertiseStatusRepository.findAll();
		dto.setStatusList(statusList);
		return dto;
	}

}
