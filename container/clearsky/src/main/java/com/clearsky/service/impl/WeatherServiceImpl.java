package com.clearsky.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clearsky.data.WeatherPropertyType;
import com.clearsky.entities.Weather;
import com.clearsky.exception.WeatherPropertyNotFoundException;
import com.clearsky.exception.WeatherValidationException;
import com.clearsky.pojo.WeatherPojo;
import com.clearsky.pojo.WeatherReport;
import com.clearsky.repository.WeatherRepository;
import com.clearsky.service.WeatherService;
import com.clearsky.validator.WeatherValidator;

@Service
public class WeatherServiceImpl implements WeatherService {
	@Autowired
	private WeatherRepository weatherRepository;

	@Transactional
	public Weather create(WeatherPojo weatherPojo) {

		String message = WeatherValidator.validateWeather(weatherPojo);
		if (message != null)
			throw new WeatherValidationException(message);
		Weather weather = new Weather();
		weather.setCity(weatherPojo.getCity());
		weather.setHumidity(weatherPojo.getHumidity());
		weather.setPressure(weatherPojo.getPressure());
		weather.setTemperature(weatherPojo.getTemperature());
		weather.setTimestamp(weatherPojo.getTimestamp());
		weather.setDescription(weatherPojo.getDescription());
		if (weatherPojo.getWind() != null) {
			weather.setDegree(weatherPojo.getWind().getDegree());
			weather.setSpeed(weatherPojo.getWind().getSpeed());
		}
		return weatherRepository.save(weather);

	}

	public List<String> getCitiesWithPastEntries() {
		return weatherRepository.getDistinctCitiesOrderByCity();
	}

	@Override
	public Weather getLatestWatherOfAcity(String cityName) {
		List<Weather> weathers = weatherRepository.findByCityOrderByTimestampDesc(cityName);
		if (weathers != null && weathers.size() > 0) {
			return weathers.get(0);
		}
		return null;
	}

	@Override
	public String getLatestWatherPropertyOfACity(String cityName, String property) {
		boolean isPropertyPresent = false;
		for (WeatherPropertyType w : WeatherPropertyType.values()) {
			if (property.equalsIgnoreCase(w.name().toString())) {
				isPropertyPresent = true;
			}
		}
		if (!isPropertyPresent)
			throw new WeatherPropertyNotFoundException("property " + property + " not found");
		List<Weather> weathers = weatherRepository.findByCityOrderByTimestampDesc(cityName);
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

		List<Object[]> dbObjects = weatherRepository.getDailyAverageWeatherOfAGivenCity(cityName);
		;
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
		List<Object[]> dbObjects = weatherRepository.getHourlyAverageWeatherOfAGivenCity(cityName);
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
