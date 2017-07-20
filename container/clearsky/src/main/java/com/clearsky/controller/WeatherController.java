package com.clearsky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clearsky.entities.Weather;
import com.clearsky.pojo.WeatherPojo;
import com.clearsky.pojo.WeatherReport;
import com.clearsky.service.WeatherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/weather")
@Api(tags = "weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(method = RequestMethod.GET, value = "cities")
	@ApiOperation(value = "lists out cities with past entry ", notes = "Get the list of cities that have ever reported their weather in the past")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<String> getCitiesWithPastEntries() {
		return weatherService.getCitiesWithPastEntries();
	}

	@RequestMapping(method = RequestMethod.GET, value = "cities/{city_name}")
	@ApiOperation(value = "Find a given city's latest weather ", notes = "Get the latest weather for a given city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather getLatestWatherOfACity(@PathVariable("city_name") String cityName) {
		return weatherService.getLatestWatherOfAcity(cityName);
	}

	@RequestMapping(method = RequestMethod.GET, value = "cities/{city_name}/{property}")
	@ApiOperation(value = "Find a given city's latest weather property", notes = "Get the latest weather property for a given city, e.g get the latest temperature for Chicago, get the latest humidity for Miami")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public String getLatestWatherPropertyOfACity(@PathVariable("city_name") String cityName,
			@PathVariable("property") String property) {
		return weatherService.getLatestWatherPropertyOfACity(cityName, property);
	}

	@RequestMapping(method = RequestMethod.GET, value = "cities/daily_average/{city_name}")
	@ApiOperation(value = "Find a given city's Daily Average weather", notes = "Get hourly averaged weather for a given city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<WeatherReport> getDailyAverageWeatherOfAGivenCity(@PathVariable("city_name") String cityName) {
		return weatherService.getDailyAverageWeatherOfAGivenCity(cityName);
	}

	@RequestMapping(method = RequestMethod.GET, value = "cities/hourly_average/{city_name}")
	@ApiOperation(value = "Find a given city's Hourly Average weather", notes = "Get daily averaged weather for a given city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<WeatherReport> getHourlyAverageWeatherOfAGivenCity(@PathVariable("city_name") String cityName) {
		return weatherService.getHourlyAverageWeatherOfAGivenCity(cityName);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Weather", notes = "Adds weather data for a city")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Weather addWeatherData(@RequestBody WeatherPojo weatherPojo) {
		System.out.println(weatherPojo.toString());
		return weatherService.create(weatherPojo);
	}

}