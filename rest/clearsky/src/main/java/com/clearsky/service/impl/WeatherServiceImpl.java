package com.clearsky.service.impl;

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

		return weatherRepository.getCitiesWithPastEntries();
	}

	@Override
	public Weather getLatestWatherOfAcity(String cityName) {
		return weatherRepository.getLatestWatherOfAcity(cityName);
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
		return weatherRepository.getLatestWatherPropertyOfACity(cityName, property);
	}

	@Override
	public List<WeatherReport> getDailyAverageWeatherOfAGivenCity(String cityName) {
		return weatherRepository.getDailyAverageWeatherOfAGivenCity(cityName);
	}

	@Override
	public List<WeatherReport> getHourlyAverageWeatherOfAGivenCity(String cityName) {
		return weatherRepository.getHourlyAverageWeatherOfAGivenCity(cityName);

	}

}
