package myobject;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SellerDetail {
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private int rate;
	
	@JsonProperty
	private String location;
	
	@JsonProperty
	private String size;
	
	@JsonProperty("time_on_lazada")
	private Map<String, Integer> timeOnLazada;
	
	@JsonProperty("shipped_on_time")
	private Map<String, Integer> shippedOnTime;
	
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
	
	/**
	 * Gets the size.
	 * <p>
	 * @return Returns the size.
	 */
	public String getSize() {
		return size;
	}
	
	/**
	 * Gets the shippedOnTime.
	 * <p>
	 * @return Returns the shippedOnTime.
	 */
	public Map<String, Integer> getShippedOnTime() {
		return shippedOnTime;
	}

}
