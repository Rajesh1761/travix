package com.travix.medusa.busyflights.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.repository.CrazyAirRepository;
import com.travix.medusa.busyflights.utility.CommonUtility;

@Service
public class CrazyAirService {

	@Autowired
	CrazyAirRepository crazyAirRepository;

	public List<CrazyAirResponse> getCrazyAirFlights(@RequestBody CrazyAirRequest crazyAirRequest) {
		final List<CrazyAirResponse> listOfCrazyAir = crazyAirRepository.getCrazyAirlines();
		List<CrazyAirResponse> finalResults = listOfCrazyAir.stream()
				.filter((p) -> crazyAirRequest.getOrigin().equals(p.getDepartureAirportCode())
						&& crazyAirRequest.getDestination().equals(p.getDestinationAirportCode())
						&& crazyAirRequest.getDepartureDate()
								.equals(CommonUtility.getDateFromISOInstant(p.getDepartureDate())) && 
								crazyAirRequest.getReturnDate()
										.equals(CommonUtility.getDateFromISOInstant(p.getArrivalDate())))
				.collect(Collectors.toList());
		return finalResults;
	}
}
