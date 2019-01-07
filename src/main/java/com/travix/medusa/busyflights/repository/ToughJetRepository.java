package com.travix.medusa.busyflights.repository;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;

@Repository
public class ToughJetRepository {

	final public static List<ToughJetResponse> listOfToughJetAirResponse=new ArrayList<ToughJetResponse>();
	static {
		ToughJetResponse obj1 = new ToughJetResponse(100.0,12.0,10.0,"LHS","BOM",getDateISODateTime(OffsetDateTime.now()),getDateISODateTime(OffsetDateTime.now().plusHours(4)));
		ToughJetResponse obj2 = new ToughJetResponse(500.0,12.0,10.0,"LHS","BOM",getDateISODateTime(OffsetDateTime.now().plusDays(1)),getDateISODateTime(OffsetDateTime.now().plusHours(4)));
		ToughJetResponse obj3 = new ToughJetResponse(200.0,12.0,10.0,"LHS","BOM",getDateISODateTime(OffsetDateTime.now().plusDays(2)),getDateISODateTime(OffsetDateTime.now().plusHours(4)));
		ToughJetResponse obj4 = new ToughJetResponse(550.0,12.0,10.0,"LHS","BOM",getDateISODateTime(OffsetDateTime.now().plusDays(3)),getDateISODateTime(OffsetDateTime.now().plusHours(4)));
		ToughJetResponse obj5 = new ToughJetResponse(300.0,12.0,10.0,"LHS","BOM",getDateISODateTime(OffsetDateTime.now().plusDays(4)),getDateISODateTime(OffsetDateTime.now().plusHours(4)));
		ToughJetResponse obj6 = new ToughJetResponse(600.0,12.0,10.0,"LHS","BOM","2019-01-07T21:54:46.775Z","2019-01-07T17:54:46.775Z");
		
		listOfToughJetAirResponse.add(obj1);
		listOfToughJetAirResponse.add(obj2);
		listOfToughJetAirResponse.add(obj3);
		listOfToughJetAirResponse.add(obj4);
		listOfToughJetAirResponse.add(obj5);
		listOfToughJetAirResponse.add(obj6);
		
	}

	public List<ToughJetResponse> getToughJetAirlines(){
		return listOfToughJetAirResponse;
	}
	static String getDateISODateTime(OffsetDateTime outBound) {
		return outBound.format(DateTimeFormatter.ISO_INSTANT);
	}
}
