package com.travix.medusa.busyflights.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.repository.ToughJetRepository;
import com.travix.medusa.busyflights.utility.CommonUtility;

@Service
public class ToughJetService {

	@Autowired
	ToughJetRepository toughJetRepository;
	
	public List<ToughJetResponse> getToughJetFlights(@RequestBody ToughJetRequest toughJetRequest) {
	    final List<ToughJetResponse> listOfToughJetAir = toughJetRepository.getToughJetAirlines();
		List<ToughJetResponse> finalResults = listOfToughJetAir.stream()
				.filter((p) -> toughJetRequest.getFrom().equals(p.getDepartureAirportName())
						&& toughJetRequest.getTo().equals(p.getArrivalAirportName())
						&& toughJetRequest.getOutboundDate()
								.equals(CommonUtility.getDateFromISOInstant(p.getOutboundDateTime())) && 
										toughJetRequest.getInboundDate()
										.equals(CommonUtility.getDateFromISOInstant(p.getInboundDateTime())))
				.collect(Collectors.toList());
		return finalResults;
	}
}
