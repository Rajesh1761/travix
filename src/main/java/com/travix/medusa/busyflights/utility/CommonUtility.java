package com.travix.medusa.busyflights.utility;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

public class CommonUtility {
	
	static DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;
	public static LocalDateTime isoDateTimeFormatter(final String iso) {
		return LocalDateTime.parse(iso, isoDateTime);
	}
	
	static DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME ;
	public static LocalDateTime isoLocateDateTimeFormatter(final String iso) {
		return LocalDateTime.parse(iso, isoLocalDateTime);
	}
	
	static DateTimeFormatter isoInstant = DateTimeFormatter.ISO_INSTANT ;
	public static LocalDateTime isoInstantFormatter(final String iso) {
		return LocalDateTime.parse(iso, isoInstant);
	}
	
	public static <T> T getResponse(final String url, final Object request, final Class<T> responseType) {
		final Map<String, String> stringsOfMap = new HashMap<>();
		final RestTemplate restTemplate = new RestTemplate();
		final T postResponse = restTemplate.postForObject(url, request, responseType, stringsOfMap);
		return postResponse;
	}

	public static CrazyAirRequest getCrazyAirRequest(final BusyFlightsRequest busyFlightRequest) {
		return new CrazyAirRequest(busyFlightRequest.getOrigin(), busyFlightRequest.getDestination(),
				busyFlightRequest.getDepartureDate(), busyFlightRequest.getReturnDate(),
				busyFlightRequest.getNumberOfPassengers());
	}

	public static ToughJetRequest getToughJetRequest(final BusyFlightsRequest busyFlightRequest) {
		return new ToughJetRequest(busyFlightRequest.getOrigin(), busyFlightRequest.getDestination(),
				busyFlightRequest.getDepartureDate(), busyFlightRequest.getReturnDate(),
				busyFlightRequest.getNumberOfPassengers());
	}

	public static BusyFlightsResponse getBusyFlightResponse(final CrazyAirResponse crazyAirResponse) {
		BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
		busyFlightsResponse.setAirline(crazyAirResponse.getAirline());
		busyFlightsResponse.setSupplier(Constants.SUPPLIER[0]);
		busyFlightsResponse.setFare(new BigDecimal(crazyAirResponse.getPrice()));
		busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
		busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
		busyFlightsResponse.setDepartureDate(getDateFromISOInstant(crazyAirResponse.getDepartureDate()));
		busyFlightsResponse.setArrivalDate(getDateFromISOInstant(crazyAirResponse.getArrivalDate()));
		return busyFlightsResponse;
	}
	public static BusyFlightsResponse getBusyFlightResponse(final ToughJetResponse toughJetResponse) {
		BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
		busyFlightsResponse.setAirline(toughJetResponse.getCarrier());
		busyFlightsResponse.setSupplier(Constants.SUPPLIER[1]);
		busyFlightsResponse.setFare(new BigDecimal((toughJetResponse.getBasePrice()+toughJetResponse.getTax())-toughJetResponse.getDiscount()));
		busyFlightsResponse.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
		busyFlightsResponse.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
		busyFlightsResponse.setDepartureDate(getDateFromISOInstant(toughJetResponse.getOutboundDateTime()));
		busyFlightsResponse.setArrivalDate(getDateFromISOInstant(toughJetResponse.getInboundDateTime()));
		return busyFlightsResponse;
	}
	public static String getISOInstantToLocalDate(String date) {
		String dateInString = date;
		Instant instant = Instant.parse(dateInString);
		LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
		return result.toLocalDate().toString();
	}
	public static String getDateFromISOInstant(String date) {
		LocalDate localDate = LocalDate.parse(date.trim().substring(0,10));
		return localDate.toString();
	}
}
