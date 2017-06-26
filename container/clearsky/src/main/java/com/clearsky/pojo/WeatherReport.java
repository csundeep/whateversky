package com.clearsky.pojo;

import java.util.Date;

public class WeatherReport {
	private double averageHumidity;
	private double averagePressure;
	private double averageTemperature;
	private double averageSpeed;
	private double averageDegree;
	private Date date;
	private int hour;

	public double getAverageHumidity() {
		return averageHumidity;
	}

	public void setAverageHumidity(double averageHumidity) {
		this.averageHumidity = averageHumidity;
	}

	public double getAveragePressure() {
		return averagePressure;
	}

	public void setAveragePressure(double averagePressure) {
		this.averagePressure = averagePressure;
	}

	public double getAverageTemperature() {
		return averageTemperature;
	}

	public void setAverageTemperature(double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}

	public double getAverageSpeed() {
		return averageSpeed;
	}

	public void setAverageSpeed(double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public double getAverageDegree() {
		return averageDegree;
	}

	public void setAverageDegree(double averageDegree) {
		this.averageDegree = averageDegree;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

}