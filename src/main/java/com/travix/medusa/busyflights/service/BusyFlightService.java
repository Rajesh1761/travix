package com.travix.medusa.busyflights.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.utility.CommonUtility;
import com.travix.medusa.busyflights.utility.Constants;

@Service
public class BusyFlightService {

	@JsonProperty("CrazyAirResponse")
	CrazyAirResponse[] crazyAirResponse = null;
	@JsonProperty("ToughJetResponse")
	ToughJetResponse[] toughJetResponse = null;

	public List<BusyFlightsResponse> getFlightDetails(BusyFlightsRequest busyFlightsRequest)
			throws ValidationException {
		return search(busyFlightsRequest);
	}

	public List<BusyFlightsResponse> search(BusyFlightsRequest request) throws ValidationException {
		
		final CrazyAirRequest crazyAirRequest = CommonUtility.getCrazyAirRequest(request);
		final ToughJetRequest toughJetRequest = CommonUtility.getToughJetRequest(request);
		crazyAirResponse = CommonUtility.getResponse(Constants.CRAZYAPI, crazyAirRequest, CrazyAirResponse[].class);
		toughJetResponse = CommonUtility.getResponse(Constants.TOUGHJETAPI, toughJetRequest, ToughJetResponse[].class);
		final List<BusyFlightsResponse> listOfBusyFlightResponse = Arrays.stream(crazyAirResponse)
				.map(CommonUtility::getBusyFlightResponse).collect(Collectors.toList());
		final List<BusyFlightsResponse> listOfToughJetFlightResponse = Arrays.stream(toughJetResponse)
				.map(CommonUtility::getBusyFlightResponse).collect(Collectors.toList());
		listOfBusyFlightResponse.addAll(listOfToughJetFlightResponse);
		List<BusyFlightsResponse> finalResults = listOfBusyFlightResponse.stream()
				.filter((p) -> request.getOrigin().equals(p.getDepartureAirportCode()) && request.getDestination().equals(p.getDestinationAirportCode())).collect(Collectors.toList());
		if(finalResults !=null && !finalResults.isEmpty()) {
			finalResults.sort(new Comparator<BusyFlightsResponse>() {
				@Override
				public int compare(BusyFlightsResponse o1, BusyFlightsResponse o2) {
					return o1.getFare().intValue() - o2.getFare().intValue();
				}
				
			});
			return finalResults;	
		}else {
			throw new ValidationException("Flights are not available for provided input");
		}
		
	}

}
