package com.clearsky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.clearsky.entities.Weather;

public interface WeatherRepository extends CrudRepository<Weather, Long> {

	@Query("SELECT distinct w.city FROM Weather w ORDER BY w.city")
	List<String> getDistinctCitiesOrderByCity();

	List<Weather> findByCityOrderByTimestampDesc(String city);

	@Query("SELECT cast(w.timestamp as date) as date," + "avg(w.temperature) as average_temperature,"
			+ "avg(w.humidity) as  average_humidity ," + "avg(w.speed) as  average_speed ,"
			+ "avg(w.degree) as  average_degree ," + "avg(w.pressure) as  average_pressure "
			+ "FROM Weather w where w.city= :cityName group by cast(w.timestamp as date)")
	List<Object[]> getDailyAverageWeatherOfAGivenCity(@Param(value = "cityName") String cityName);

	@Query("select cast(w.timestamp as date) as Day, hour(w.timestamp) as Hour, "
			+ "avg(w.temperature) as average_temperature," + "avg(w.humidity) as  average_humidity ,"
			+ "avg(w.speed) as  average_speed ," + "avg(w.degree) as  average_degree ,"
			+ "avg(w.pressure) as  average_pressure "
			+ "from Weather w where w.city= :cityName group by day( w.timestamp), hour( w.timestamp)")
	List<Object[]> getHourlyAverageWeatherOfAGivenCity(@Param(value = "cityName")  String cityName);

}
