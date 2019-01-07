package com.travix.medusa.busyflights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.ToughJetService;

@RestController
public class ToughJetFlightController {
	
	/*
	 * { "from": "LHS", "to": "BOM", "outboundDate": "2019-01-07", "inboundDate": "2019-01-07", "numberOfAdults": 4 }
	 */
	@Autowired
	ToughJetService toughJestService;
	@PostMapping("/search/toughJet")
	@ResponseBody
	public List<ToughJetResponse> getToughJetFlights(@RequestBody ToughJetRequest toughJetFlightsRequest) {
		return toughJestService.getToughJetFlights(toughJetFlightsRequest);
	}
}
