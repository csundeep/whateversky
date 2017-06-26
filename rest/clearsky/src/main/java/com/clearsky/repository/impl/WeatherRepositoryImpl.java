package com.clearsky.repository.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.clearsky.data.WeatherPropertyType;
import com.clearsky.entities.Weather;
import com.clearsky.pojo.WeatherReport;
import com.clearsky.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Weather save(Weather weather) {
		entityManager.persist(weather);
		return weather;
	}

	@Override
	public List<String> getCitiesWithPastEntries() {
		TypedQuery<String> query = entityManager.createNamedQuery("Weather.findCitiesWithPastEntries", String.class);
		List<String> cities = query.getResultList();
		return cities;
	}

	@Override
	public Weather getLatestWatherOfAcity(String cityName) {
		TypedQuery<Weather> query = entityManager.createNamedQuery("Weather.findByCityName", Weather.class);
		query.setParameter("cityName", cityName);
		List<Weather> weathers = query.getResultList();
		if (weathers != null && weathers.size() > 0) {
			return weathers.get(0);
		}
		return null;
	}

	@Override
	public String getLatestWatherPropertyOfACity(String cityName, String property) {
		TypedQuery<Weather> query = entityManager.createNamedQuery("Weather.findByCityName", Weather.class);
		query.setParameter("cityName", cityName);
		List<Weather> weathers = query.getResultList();
		if (weathers != null && weathers.size() > 0) {
			if (property != null) {
				Weather weather = weathers.get(0);
				if (property.equalsIgnoreCase(WeatherPropertyType.temperature.toString()))
					return Double.toString(weather.getTemperature());
				else if (property.equalsIgnoreCase(WeatherPropertyType.humidity.toString()))
					return Double.toString(weather.getHumidity());
				else if (property.equalsIgnoreCase(WeatherPropertyType.pressure.toString()))
					return Double.toString(weather.getPressure());
				else if (property.equalsIgnoreCase(WeatherPropertyType.speed.toString()))
					return Double.toString(weather.getSpeed());
				else
					return Double.toString(weather.getDegree());
			}
		}
		return null;
	}

	@Override
	public List<WeatherReport> getDailyAverageWeatherOfAGivenCity(String cityName) {
		List<WeatherReport> weatherReports = new ArrayList<>();
		TypedQuery<Object[]> query = entityManager.createNamedQuery("Weather.findDailyAverageWeatherOfGivenCity",
				Object[].class);
		query.setParameter("cityName", cityName);
		List<Object[]> dbObjects = query.getResultList();
		for (Object[] object : dbObjects) {

			WeatherReport weatherReport = new WeatherReport();
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			try {
				weatherReport.setDate(df.parse(object[0].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			weatherReport.setAverageTemperature(Double.valueOf(object[1].toString()));
			weatherReport.setAverageHumidity(Double.valueOf(object[2].toString()));
			weatherReport.setAverageSpeed(Double.valueOf(object[3].toString()));
			weatherReport.setAverageDegree(Double.valueOf(object[4].toString()));
			weatherReport.setAveragePressure(Double.valueOf(object[5].toString()));
			weatherReports.add(weatherReport);
		}

		return weatherReports;
	}

	@Override
	public List<WeatherReport> getHourlyAverageWeatherOfAGivenCity(String cityName) {
		List<WeatherReport> weatherReports = new ArrayList<>();
		TypedQuery<Object[]> query = entityManager.createNamedQuery("Weather.findHourlyAverageWeatherOfGivenCity",
				Object[].class);
		query.setParameter("cityName", cityName);
		List<Object[]> dbObjects = query.getResultList();
		for (Object[] object : dbObjects) {
			WeatherReport weatherReport = new WeatherReport();
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			try {
				weatherReport.setDate(df.parse(object[0].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			weatherReport.setHour(Integer.valueOf(object[1].toString()));
			weatherReport.setAverageTemperature(Double.valueOf(object[2].toString()));
			weatherReport.setAverageHumidity(Double.valueOf(object[3].toString()));
			weatherReport.setAverageSpeed(Double.valueOf(object[4].toString()));
			weatherReport.setAverageDegree(Double.valueOf(object[5].toString()));
			weatherReport.setAveragePressure(Double.valueOf(object[6].toString()));
			weatherReports.add(weatherReport);
		}

		return weatherReports;
	}
}
