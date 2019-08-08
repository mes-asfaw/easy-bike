package com.me.easyBike.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StationStatus {
	private boolean isInstalled;
	private boolean isRenting;
	private int numBikesAvailable;
	private int numDocksAvailable;
	private long lastReported;
	private boolean isReturning;
	private String stationId;
	
	public boolean getIsInstalled() {
		return isInstalled;
	}
	public void setIsInstalled(boolean isInstalled) {
		this.isInstalled = isInstalled;
	}
	public boolean getIsRenting() {
		return isRenting;
	}
	public void setIsRenting(boolean isRenting) {
		this.isRenting = isRenting;
	}
	public int getNumBikesAvailable() {
		return numBikesAvailable;
	}
	public void setNumBikesAvailable(int numBikesAvailable) {
		this.numBikesAvailable = numBikesAvailable;
	}
	public int getNumDocksAvailable() {
		return numDocksAvailable;
	}
	public void setNumDocksAvailable(int numDocksAvailable) {
		this.numDocksAvailable = numDocksAvailable;
	}
	public long getLastReported() {
		return lastReported;
	}
	public void setLastReported(long lastReported) {
		this.lastReported = lastReported;
	}
	public boolean getIsReturning() {
		return isReturning;
	}
	public void setIsReturning(boolean isReturning) {
		this.isReturning = isReturning;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}	
	
}
