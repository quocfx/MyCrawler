package model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seller {	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private float rate;
	
	@JsonProperty
	private String location;
	
	@JsonProperty
	private int size;
	
	@JsonProperty("time_on_lazada")
	private Map<String, Integer> timeOnLazada;
	
	@JsonProperty("shipped_on_time")
	private Map<String, Integer> shippedOnTime;
	
	@JsonProperty
	private Map<String, String> category;
	
	/**
	 * Gets the name
	 * <p>
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 * <p>
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the rate
	 * <p>
	 * @return Returns the rate.
	 */
	public float getRate() {
		return rate;
	}
	/**
	 * Sets the rate.
	 * <p>
	 * @param rate The rate to set.
	 */
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	/**
	 * Gets the location.
	 * <p>
	 * @return Returns the location.
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * Sets the location.
	 * <p>
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Gets the size.
	 * <p>
	 * @return Returns the size.
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Sets the size.
	 * <p>
	 * @param size The size to set.
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Gets the time on lazada.
	 * <p>
	 * @return Returns the time on lazada.
	 */
	public Map<String, Integer> getTimeOnLazada() {
		return timeOnLazada;
	}
	/**
	 * Sets the timeOnLazada.
	 * <p>
	 * @param timeOnLazada The timeOnLazada to set.
	 */
	public void setTimeOnLazada(Map<String, Integer> timeOnLazada) {
		this.timeOnLazada = timeOnLazada;
	}
	
	/**
	 * Sets the shippedOnTime.
	 * <p>
	 * @param shippedOnTime The shippedOnTime to set.
	 */
	public void setShippedOnTime(Map<String, Integer> shippedOnTime) {
		this.shippedOnTime = shippedOnTime;
	}
	
	/**
	 * Gets the shippedOnTime.
	 * <p>
	 * @return Returns the shippedOnTime.
	 */
	public Map<String, Integer> getShippedOnTime() {
		return shippedOnTime;
	}
	
	/**
	 * Gets the category.
	 * <p>
	 * @return Returns the category.
	 */
	public Map<String, String> getCategory() {
		return category;
	}
	/**
	 * Sets the category.
	 * <p>
	 * @param category The category to set.
	 */
	public void setCategory(Map<String, String> category) {
		this.category = category;
	}
}
