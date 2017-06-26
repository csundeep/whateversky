package com.clearsky.service;

import java.util.List;

import com.clearsky.entities.Weather;
import com.clearsky.pojo.WeatherPojo;
import com.clearsky.pojo.WeatherReport;

public interface WeatherService {

	Weather create(WeatherPojo weatherPojo);

	List<String> getCitiesWithPastEntries();

	Weather getLatestWatherOfAcity(String cityName);

	String getLatestWatherPropertyOfACity(String cityName, String property);

	List<WeatherReport> getDailyAverageWeatherOfAGivenCity(String cityName);

	List<WeatherReport> getHourlyAverageWeatherOfAGivenCity(String cityName);

}
