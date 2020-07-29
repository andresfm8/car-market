package ca.sheridancollege.munevarm.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.munevarm.beans.Car;
import ca.sheridancollege.munevarm.beans.Manufacturer;

@Repository
public class DatabaseAccess {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	public void addCar(Long manufacturerID, String carModel, int year, String color, double price) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO car (manufacturerID, carModel, year, color, price) "
						+ "VALUES (:manufacturerID, :carModel, :year, :color, :price)";
		parameters.addValue("manufacturerID", manufacturerID);
		parameters.addValue("carModel", carModel);
		parameters.addValue("year", year);
		parameters.addValue("color", color);
		parameters.addValue("price", price);
		
		checkAndUpdate(query, parameters, "added");
	}
	
	public void updateCar(Long carID, String carModel, double price) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE car SET carModel = :carModel, price = :price WHERE carID = :carID";
		parameters.addValue("carModel", carModel);
		parameters.addValue("price", price);
		
		checkAndUpdate(query, parameters, "updated");
	}
	
	public void deleteCar(Long carID) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM cars WHERE carID = :carID";
		parameters.addValue("carID", carID);
		
		checkAndUpdate(query, parameters, "updated");
	}
	//Find a car by id
	public List<Car> getCarById(Long carID) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM car WHERE carID = :carID";
		parameters.addValue("carID", carID);
		return jdbc.query(query, parameters, new BeanPropertyRowMapper<Car>(Car.class));
	}
	//Get all cars in the database
	public List<Car> getCars() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM car";
		return jdbc.query(query, parameters, new BeanPropertyRowMapper<Car>(Car.class));
	}
	//Get all manufacturers in the database
	public List<Manufacturer> getManufacturers() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM manufacturer";
		return jdbc.query(query, parameters, new BeanPropertyRowMapper<Manufacturer>(Manufacturer.class));
	}
	//To check if the record was added, updated or deleted.
	public void checkAndUpdate(String query, MapSqlParameterSource parameters, String action) {
		int rowsAffected = jdbc.update(query, parameters);
		if(rowsAffected > 0) {
			System.out.println("Car " + action + " succesfully");
		}
		
	}
}
