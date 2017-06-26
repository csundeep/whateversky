package com.clearsky.pojo;

import java.util.Date;

public class WeatherPojo {
	private Long id;
	private String city;
	private String description;
	private double humidity;
	private double pressure;
	private double temperature;
	private Wind wind;
	private Date timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "WeatherPojo [id=" + id + ", city=" + city + ", description=" + description + ", humidity=" + humidity
				+ ", pressure=" + pressure + ", temperature=" + temperature + ", wind=" + wind + ", timestamp="
				+ timestamp + "]";
	}

}
