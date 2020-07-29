package ca.sheridancollege.munevarm.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseAccess {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void addCar(Long manufacturerId, String model, String color, int year, double price) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSER INTO cars(model, color, year, price) "
						+ "VALUES (:model, :color, :year, :price)";
		parameters.addValue("manufacturerId", manufacturerId);
		parameters.addValue("model", model);
		parameters.addValue("color", color);
		parameters.addValue("year", year);
		parameters.addValue("year", price);
		
		checkAndUpdate(query, parameters, "added");
	}
	
	public void updateCar(Long carId, String model, String color, int year, double price) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE cars SET "
				+ "model = :model, color = :color, year = :year, price = :price"
				+ "WHERE carId = :carId";
		parameters.addValue("carId", carId);
		parameters.addValue("model", model);
		parameters.addValue("color", color);
		parameters.addValue("year", year);
		parameters.addValue("year", price);
		
		checkAndUpdate(query, parameters, "updated");
	}
	
	public void deleteCar(Long carId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM cars WHERE carId = :carId";
		parameters.addValue("carId", carId);
		
		checkAndUpdate(query, parameters, "updated");
	}
	
	//To check if the record was added, updated or deleted.
	public void checkAndUpdate(String query, MapSqlParameterSource parameters, String action) {
		int rowsAffected = jdbc.update(query, parameters);
		if(rowsAffected > 0) {
			System.out.println("Course " + action + " succesfully");
		}
		
	}
}
