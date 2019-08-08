package com.me.easyBike.model;

public class StationStatusInfo {
	private String name; 
	private int numBikesAvailable;
	private int numDocksAvailable;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
