package com.example.olxadvertise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.olxadvertise.dto.AdvertiseDTO;
import com.example.olxadvertise.dto.AdvertiseResponseDTO;
import com.example.olxadvertise.model.Advertise;
import com.example.olxadvertise.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller class for OLX Advertise application
 * 
 * @author ritesh
 *
 */
@CrossOrigin("*")
@RestController
@RefreshScope
public class AdvertiseController {

	/**
	 * Reference to {@link AdvertiseService}}
	 */
	@Autowired
	private AdvertiseService advertiseService;

	/**
	 * This API use to create advertise.
	 * 
	 * @param advertise
	 *            the request DTO
	 * @return the response {@link Advertise}}
	 */
	@ApiOperation(value = "API to post advertise")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful advertise creation", response = Advertise.class) })
	@PostMapping(value = "/advertise", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AdvertiseDTO> postNewAdvertise(@RequestBody Advertise advertise,
			@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(advertiseService.postNewAdvertise(advertise, token), HttpStatus.CREATED);
	}

	/**
	 * This API use to update posted advertise
	 * 
	 * @param id
	 *            the advertise id
	 * @return the response {@link Advertise}}
	 */
	@ApiOperation(value = "API to Update advertise")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful advertise updation", response = Advertise.class) })
	@PutMapping(value = "/advertise/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AdvertiseDTO> postNewAdvertise(@PathVariable("id") int id, @RequestBody Advertise advertise,
			@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(advertiseService.postNewAdvertise(id, advertise,token), HttpStatus.OK);
	}

	/**
	 * @return
	 */
	@ApiOperation(value = "API to get all posted advertise")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval of advertise", response = AdvertiseResponseDTO.class) })
	@GetMapping(value = "/user/advertise", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AdvertiseResponseDTO> getAllPostedAdvertise(@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(advertiseService.getAllPostedAdvertise(token), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "API to get advertise by id")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval of advertise ", response = Advertise.class) })
	@GetMapping(value = "/user/advertise/{postId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AdvertiseDTO> getPostedAdvertiseById(@PathVariable("postId") int id,
			@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(advertiseService.getPostedAdvertiseById(id,token), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/user/advertise/{postId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Boolean> deletePostedAdvertiseById(@PathVariable("postId") int id) {
		return new ResponseEntity<>(advertiseService.deletePostedAdvertiseById(id), HttpStatus.OK);
	}

	/**
	 * @return
	 */
	@ApiOperation(value = "API to get filter  advertise")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval of advertise", response = AdvertiseResponseDTO.class) })
	@GetMapping(value = "/advertise/search/filtercriteria", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<AdvertiseResponseDTO> getFilterAdvertises() {
		return new ResponseEntity<>(advertiseService.getFilterAdvertises(), HttpStatus.OK);
	}

}
