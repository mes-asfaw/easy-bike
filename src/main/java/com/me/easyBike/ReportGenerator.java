package com.me.easyBike;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.me.easyBike.model.StationInformation;
import com.me.easyBike.model.StationStatus;
import com.me.easyBike.model.StationStatusInfo;

public class ReportGenerator {
	private static final String STATION_API_URI = "https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json";
	private static final String AVAILABILERESOURCES_API_URI = "https://gbfs.urbansharing.com/oslobysykkel.no/station_status.json";

	private static ResourceAccessor accessor = new ResourceAccessor();

	private Map<String, String> stationIdNameMap = new HashMap<String, String>();
	private List<StationStatus> stationstatusList = null;
	private List<StationInformation> stationInfoList = null;
	private List<StationStatusInfo> stationStatusInfo = new ArrayList<StationStatusInfo>();

	public void generate() {		

		try {
			stationInfoList = accessor.getStationInfo(STATION_API_URI);
			
			createStationIdNameMap();

			stationstatusList = accessor.getStationStatus(AVAILABILERESOURCES_API_URI);

			extractStationInfo();
			
			print();
		}catch(UnknownHostException e){
			System.out.println("remote server is unknown");
		}catch (NullPointerException e){
			System.out.println("remote server returning invalid values");
		}catch(RuntimeException e){
			System.out.println("request to the remote server is not successful");
		}catch(JsonProcessingException e){
			System.out.println("unable to process the response from the remote server");
		}catch (IOException e) {
			System.out.println("remote server has internal error");			
		}catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void createStationIdNameMap(){
		if (stationInfoList == null)		
			throw new NullPointerException();

		for(StationInformation stationInfo : stationInfoList){
			stationIdNameMap.put(stationInfo.getStationId(), stationInfo.getName());
		}
	}

	public void extractStationInfo(){
		if (stationstatusList == null || stationIdNameMap == null)		
			throw new NullPointerException();
		
		StationStatusInfo station;

		for(int i=0; i< stationstatusList.size();i++){

			for(Map.Entry<String, String> entry : stationIdNameMap.entrySet()){

				if(entry.getKey().equals(stationstatusList.get(i).getStationId())){
					station = new StationStatusInfo();
					station.setName(entry.getValue());
					station.setNumBikesAvailable(stationstatusList.get(i).getNumBikesAvailable());
					station.setNumDocksAvailable(stationstatusList.get(i).getNumDocksAvailable());

					stationStatusInfo.add(station);
					stationIdNameMap.remove(entry);
					break;
				}
			}
		}
	}
	
	public void print() throws JsonProcessingException{
		if(stationStatusInfo == null)
			throw new NullPointerException();

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

		String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stationStatusInfo);
		
		System.out.println(result);
	}
}
