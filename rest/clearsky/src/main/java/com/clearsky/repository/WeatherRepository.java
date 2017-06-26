package com.clearsky.repository;

import java.util.List;

import com.clearsky.entities.Weather;
import com.clearsky.pojo.WeatherReport;

public interface WeatherRepository {

	Weather save(Weather weather);

	List<String> getCitiesWithPastEntries();

	Weather getLatestWatherOfAcity(String cityName);

	String getLatestWatherPropertyOfACity(String cityName, String property);

	List<WeatherReport> getDailyAverageWeatherOfAGivenCity(String cityName);
	List<WeatherReport> getHourlyAverageWeatherOfAGivenCity(String cityName);
	

}
