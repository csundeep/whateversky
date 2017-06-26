package com.clearsky.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table
@Entity
@NamedQueries({
		@NamedQuery(name = "Weather.findCitiesWithPastEntries", query = "SELECT distinct w.city FROM Weather w ORDER BY w.city"),
		@NamedQuery(name = "Weather.findDailyAverageWeatherOfGivenCity", query = "SELECT cast(w.timestamp as date) as date,"
				+ "avg(w.temperature) as average_temperature," + "avg(w.humidity) as  average_humidity ,"
				+ "avg(w.speed) as  average_speed ," + "avg(w.degree) as  average_degree ,"
				+ "avg(w.pressure) as  average_pressure "
				+ "FROM Weather w where w.city= :cityName group by cast(w.timestamp as date)"),
		@NamedQuery(name = "Weather.findHourlyAverageWeatherOfGivenCity", query = "select cast(w.timestamp as date) as Day, hour(w.timestamp) as Hour, "
				+ "avg(w.temperature) as average_temperature," + "avg(w.humidity) as  average_humidity ,"
				+ "avg(w.speed) as  average_speed ," + "avg(w.degree) as  average_degree ,"
				+ "avg(w.pressure) as  average_pressure "
				+ "from Weather w where w.city= :cityName group by day( w.timestamp), hour( w.timestamp)"),

		@NamedQuery(name = "Weather.findByCityName", query = "SELECT w FROM Weather w where w.city=:cityName ORDER BY w.timestamp desc") })
public class Weather {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String city;
	@Column
	private String description;
	@Column
	private double humidity;
	@Column
	private double pressure;
	@Column
	private double temperature;
	@Column
	private double speed;
	@Column
	private double degree;
	@Column
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

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDegree() {
		return degree;
	}

	public void setDegree(double degree) {
		this.degree = degree;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
