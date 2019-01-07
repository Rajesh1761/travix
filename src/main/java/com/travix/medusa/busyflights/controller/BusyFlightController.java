package com.travix.medusa.busyflights.controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightService;

@RestController
public class BusyFlightController {
	/*
	 * 
	 *{ "origin": "LHS", "destination": "BOM", "departureDate": "2019-01-07", "returnDate": "2019-01-07", "numberOfPassengers": 4 }
	 */
	@Value("${maximumNumberOfPassanger}")
	private String maximumNumberOfPassanger;
	
	@Value("${minimumNumberOfPassanger}")
	private String minimumNumberOfPassanger;
	@Autowired
	BusyFlightService busyFlightService;
	@GetMapping("/search")
	public List<BusyFlightsResponse> getFlightDetails(@RequestBody BusyFlightsRequest busyFlightsRequest) throws ValidationException {
		if(busyFlightsRequest!=null) {
			if(busyFlightsRequest.getNumberOfPassengers() <= Integer.parseInt(maximumNumberOfPassanger) && busyFlightsRequest.getNumberOfPassengers() >= Integer.parseInt(minimumNumberOfPassanger)) {
				if(busyFlightsRequest.getOrigin().length()==3 && busyFlightsRequest.getDestination().length()==3) {
					return busyFlightService.getFlightDetails(busyFlightsRequest);
				}else {
					throw new ValidationException("Length of Origin/Destination must be 3 in length ");
				}
					
			}else {
				throw new ValidationException("Minimum "+Integer.parseInt(minimumNumberOfPassanger)+" and maximum "+ Integer.parseInt(maximumNumberOfPassanger) +" passanger allowed");
			}	
		}else {
			throw new ValidationException("Request body cannot be null");
		}
		
		
	}
}
