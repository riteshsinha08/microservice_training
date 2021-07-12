package com.example.olxadvertise.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.olxadvertise.dto.AdvertiseDTO;
import com.example.olxadvertise.dto.AdvertiseResponseDTO;
import com.example.olxadvertise.dto.CategoriesResponseDTO;
import com.example.olxadvertise.dto.StatusResponseDTO;
import com.example.olxadvertise.exception.InvalidAuthorizationTokenException;
import com.example.olxadvertise.model.Advertise;
import com.example.olxadvertise.repository.AdvertiseRepository;
import com.example.olxadvertise.service.AdvertiseService;
import com.example.olxadvertise.service.UserServiceDelegate;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

	@Autowired
	private AdvertiseRepository advertiseRepository;
	
	@Autowired
	private UserServiceDelegate userServiceDelegate;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	private RestTemplate restTemplate;

	@PostConstruct
	public void setUp() {
		restTemplate = restTemplateBuilder.build();
	}

	@Override
	public AdvertiseDTO postNewAdvertise(Advertise advertise,String token) {
		if(!userServiceDelegate.isLoggedInUser(token)) {
			throw new InvalidAuthorizationTokenException(token);
		}
		List<Advertise> advertiseList = advertiseRepository.findAll();
		if (advertiseList.size() != 0) {
			advertise.setId(advertiseList.get(advertiseList.size() - 1).getId() + 1);
		} else {
			advertise.setId(1);
		}
		advertise.setCreatedDate(LocalDate.now());
		advertise.setModifiedDate(LocalDate.now());
		Advertise adveriseResponse = advertiseRepository.save(advertise);

		ResponseEntity<CategoriesResponseDTO> response = restTemplate
				.getForEntity("http://localhost:8000/advertise/category", CategoriesResponseDTO.class);
		String category = response.getBody().getCategories().stream().filter(p -> p.getId() == advertise.getId())
				.findFirst().get().getCategory();
		AdvertiseDTO dto = new AdvertiseDTO();
		dto.setCategory(category);
		dto.setDescription(adveriseResponse.getDescription());
		dto.setCreatedDate(adveriseResponse.getCreatedDate());
		dto.setId(adveriseResponse.getId());
		dto.setModifiedDate(adveriseResponse.getModifiedDate());
		dto.setPrice(adveriseResponse.getPrice());
		dto.setStatus("OPEN");
		dto.setTitle(adveriseResponse.getTitle());
		Map<?,?> map=userServiceDelegate.findByUserName(token);
		dto.setUsername((String)map.get("userName"));
		return dto;
	}

	@Override
	public AdvertiseDTO postNewAdvertise(int id, Advertise advertiseDTO,String token) {
		if(!userServiceDelegate.isLoggedInUser(token)) {
			throw new InvalidAuthorizationTokenException(token);
		}
		List<Advertise> advertiseList = advertiseRepository.findAll();
		Advertise advertise = advertiseList.stream().filter(p -> p.getId() == id).findFirst().get();
		advertise.setCategoryId(advertiseDTO.getCategoryId());
		advertise.setDescription(advertiseDTO.getDescription());
		advertise.setCreatedDate(LocalDate.now());
		advertise.setModifiedDate(LocalDate.now());
		advertise.setPrice(advertiseDTO.getPrice());
		advertise.setTitle(advertiseDTO.getTitle());
		advertiseRepository.save(advertise);
		ResponseEntity<StatusResponseDTO> response = restTemplate.getForEntity("http://localhost:8000/advertise/status",
				StatusResponseDTO.class);
		String status = response.getBody().getStatusList().stream().filter(p -> p.getId() == advertiseDTO.getStatusId())
				.findFirst().get().getStatus();
		ResponseEntity<CategoriesResponseDTO> categoryResponse = restTemplate
				.getForEntity("http://localhost:8000/advertise/category", CategoriesResponseDTO.class);

		String category = categoryResponse.getBody().getCategories().stream()
				.filter(p -> p.getId() == advertise.getId()).findFirst().get().getCategory();
		AdvertiseDTO dto = new AdvertiseDTO();
		dto.setStatus(status);
		dto.setCategory(category);
		dto.setDescription(advertiseDTO.getDescription());
		dto.setCreatedDate(LocalDate.now());
		dto.setModifiedDate(LocalDate.now());
		dto.setPrice(advertiseDTO.getPrice());
		dto.setTitle(advertiseDTO.getTitle());
		return dto;
	}

	@Override
	public AdvertiseResponseDTO getAllPostedAdvertise(String token) {
		List<Advertise> advertiseList = advertiseRepository.findAll();

		List<AdvertiseDTO> advertiseDTOs = advertiseList.parallelStream().map(p -> convertAdvertiseToAdvertiseDTO(p,token))
				.collect(Collectors.toList());
		AdvertiseResponseDTO advertiseResponseDTO = new AdvertiseResponseDTO();
		advertiseResponseDTO.setAdvertises(advertiseDTOs);
		return advertiseResponseDTO;
	}

	@Override
	public AdvertiseDTO getPostedAdvertiseById(int id,String token) {
		List<Advertise> advertiseList = advertiseRepository.findAll();
		Advertise advertise=advertiseList.stream().filter(p->p.getId()==id).findFirst().get();
		return convertAdvertiseToAdvertiseDTO(advertise,token);
	}

	@Override
	public Boolean deletePostedAdvertiseById(int id) {
		Advertise advertise=advertiseRepository.deleteAdvertiseById(id);
		if(advertise!=null)
		return true;
		else return false;
	}

	@Override
	public AdvertiseResponseDTO getFilterAdvertises() {
		Advertise advertise = new Advertise();
		// advertise.setId(1);
		advertise.setCreatedDate(LocalDate.now());
		advertise.setModifiedDate(LocalDate.now());
		// advertise.setStatus("OPEN");
		advertise.setTitle("laptop sale");
		advertise.setPrice(54000);
		advertise.setDescription("intel core 3");
		// advertise.setCategory("Electronic goods");
		List<Advertise> advertises = new ArrayList<>();
		AdvertiseResponseDTO advertiseResponseDTO = new AdvertiseResponseDTO();
		advertises.add(advertise);
		// advertiseResponseDTO.setAdvertises(advertises);
		return advertiseResponseDTO;
	}

	private AdvertiseDTO convertAdvertiseToAdvertiseDTO(Advertise advertise,String token) {

		ResponseEntity<CategoriesResponseDTO> categoryResponse = restTemplate
				.getForEntity("http://localhost:8000/advertise/category", CategoriesResponseDTO.class);

		String category = categoryResponse.getBody().getCategories().stream()
				.filter(p -> p.getId() == advertise.getCategoryId()).findFirst().get().getCategory();
		AdvertiseDTO dto = new AdvertiseDTO();
		dto.setCategory(category);
		dto.setStatus("OPEN");
		dto.setCreatedDate(advertise.getCreatedDate());
		dto.setDescription(advertise.getDescription());
		dto.setId(advertise.getId());
		dto.setModifiedDate(advertise.getModifiedDate());
		dto.setPrice(advertise.getPrice());
		dto.setTitle(advertise.getTitle());
		Map<?,?> map=userServiceDelegate.findByUserName(token);
		dto.setUsername((String)map.get("userName"));
		return dto;
	}

}
