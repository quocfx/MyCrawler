package myobject;

import java.util.Map;

public class Seller {
	
	private String name;
	private int rate;
	private String location;
	private Map<String, Integer> timeOnLazada;
	
	public String getName() {
		return name;
	}
	public int getRate() {
		return rate;
	}
	public String getLocation() {
		return location;
	}
	public Map<String, Integer> getTimeOnLazada() {
		return timeOnLazada;
	}

}
