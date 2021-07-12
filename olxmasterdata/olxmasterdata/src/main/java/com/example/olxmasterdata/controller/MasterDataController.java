package com.example.olxmasterdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.olxmasterdata.dto.CategoriesResponseDTO;
import com.example.olxmasterdata.dto.StatusResponseDTO;
import com.example.olxmasterdata.service.MasterDataService;

@RestController
@CrossOrigin
@RefreshScope
public class MasterDataController {

	@Autowired
	private MasterDataService masterDataService;

	@GetMapping(value = "/advertise/category", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CategoriesResponseDTO> getAllCategories() {
		return new ResponseEntity<>(masterDataService.getAllCategories(), HttpStatus.OK);
	}

	@GetMapping(value = "/advertise/status", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<StatusResponseDTO> getAllAdvertiseStatus() {
		return new ResponseEntity<>(masterDataService.getAllAdvertiseStatus(), HttpStatus.OK);

	}
}
