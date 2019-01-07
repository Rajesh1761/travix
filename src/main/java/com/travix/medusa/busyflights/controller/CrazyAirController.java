package com.travix.medusa.busyflights.controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.CrazyAirService;

@RestController
public class CrazyAirController {
	
	/*
	 * {   "origin": "LHS", "destination": "BOM", "departureDate": "2019-01-07","returnDate": "2019-01-07", "passengerCount": 4}
	 */
	@Autowired
	CrazyAirService crazyAirService;
	@PostMapping("/search/crazyAir")
	@ResponseBody
	public List<CrazyAirResponse> getCrazyAirFlights(@RequestBody CrazyAirRequest crazyAirRequest) throws ValidationException {
		if(crazyAirRequest.getOrigin().length()==3 && crazyAirRequest.getDestination().length()==3) {
			return crazyAirService.getCrazyAirFlights(crazyAirRequest);
		}else {
			throw new ValidationException("Length of Origin/Destination must be 3 in length ");
		}
		
	}
}
