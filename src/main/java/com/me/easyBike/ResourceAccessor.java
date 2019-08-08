package com.me.easyBike;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.easyBike.model.StationInformation;
import com.me.easyBike.model.StationStatus;

public class ResourceAccessor {
	
	public String getAvailableResources(String URI) throws IOException{
		String output ="";

		URL url = new URL(URI);
		HttpURLConnection connnection = (HttpURLConnection) url.openConnection();
		connnection.setRequestMethod("GET");
		connnection.setRequestProperty("Accept", "application/json");

		if (connnection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP Error code : "
					+ connnection.getResponseCode());
		}
		
		InputStreamReader inputStreamReader = new InputStreamReader(connnection.getInputStream());
		Scanner scanner = new Scanner(inputStreamReader); 

		while (scanner.hasNext()) {
			output += scanner.next();
		}
		
		inputStreamReader.close();
		scanner.close();
		connnection.disconnect();
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(output);
		JsonNode data = rootNode.get("data");	
		
		if (data == null)		
			throw new NullPointerException();
		
		JsonNode stations = data.get("stations");
		if (stations == null)		
			throw new NullPointerException();		
		
		return stations.toString();
	}
	
	public List<StationInformation> getStationInfo(String STATION_API_URI) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();

		String stations = getAvailableResources(STATION_API_URI);

		if(stations == null)
			throw new NullPointerException();
		
		@SuppressWarnings("unchecked")
		List<StationInformation> stationInfoList =  (List<StationInformation>)objectMapper.readValue(stations,
				new TypeReference<List<StationInformation>>(){});
		
		return stationInfoList;
	}

	public List<StationStatus> getStationStatus(String AVAILABILERESOURCES_API_URI) throws IOException{

		ObjectMapper objectMapper = new ObjectMapper();
		String stations = getAvailableResources(AVAILABILERESOURCES_API_URI);

		if(stations == null)
			throw new NullPointerException();
		
		@SuppressWarnings("unchecked")
		List<StationStatus> stationstatusList =  (List<StationStatus>)objectMapper.readValue(stations.toString(), 
				new TypeReference<List<StationStatus>>(){});
		
		return stationstatusList;
	}
}
