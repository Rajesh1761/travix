package com.travix.medusa.busyflights.repository;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.utility.Constants;

@Repository
public class CrazyAirRepository {
	
	final public static List<CrazyAirResponse> listOfCrazyAirResponse=new ArrayList<CrazyAirResponse>();
	static {
		CrazyAirResponse obj1 = new CrazyAirResponse(Constants.CRAZYAIRNAME,100.0,"E","GOI","BOM",getDateISODateTime(OffsetDateTime.now()),getDateISODateTime(OffsetDateTime.now().plusHours(4)));
		CrazyAirResponse obj2 = new CrazyAirResponse(Constants.CRAZYAIRNAME,500.0,"B","LHS","BOM",getDateISODateTime(OffsetDateTime.now().plusDays(1)),getDateISODateTime(OffsetDateTime.now().plusHours(4)));
		CrazyAirResponse obj3 = new CrazyAirResponse(Constants.CRAZYAIRNAME,200.0,"E","LHS","BOM",getDateISODateTime(OffsetDateTime.now().plusDays(2)),getDateISODateTime(OffsetDateTime.now().plusHours(4)));
		CrazyAirResponse obj4 = new CrazyAirResponse(Constants.CRAZYAIRNAME,550.0,"B","LHS","BOM",getDateISODateTime(OffsetDateTime.now().plusDays(3)),getDateISODateTime(OffsetDateTime.now().plusHours(4)));
		CrazyAirResponse obj5 = new CrazyAirResponse(Constants.CRAZYAIRNAME,300.0,"E","LHS","BOM",getDateISODateTime(OffsetDateTime.now().plusDays(4)),getDateISODateTime(OffsetDateTime.now().plusHours(4)));
		CrazyAirResponse obj6 = new CrazyAirResponse(Constants.CRAZYAIRNAME,600.0,"B","LHS","BOM","2019-01-07T23:34:49.201","2019-01-07T03:34:49.201");
		
		listOfCrazyAirResponse.add(obj1);
		listOfCrazyAirResponse.add(obj2);
		listOfCrazyAirResponse.add(obj3);
		listOfCrazyAirResponse.add(obj4);
		listOfCrazyAirResponse.add(obj5);
		listOfCrazyAirResponse.add(obj6);
		
	}

	public List<CrazyAirResponse> getCrazyAirlines(){
		return listOfCrazyAirResponse;
	}
	static String getDateISODateTime(OffsetDateTime outBound) {
		return outBound.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
}
