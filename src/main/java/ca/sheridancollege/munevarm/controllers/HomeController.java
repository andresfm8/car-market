package ca.sheridancollege.munevarm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.munevarm.beans.Car;
import ca.sheridancollege.munevarm.database.DatabaseAccess;

@Controller
public class HomeController {
	
	@Autowired
	@Lazy
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String getIndex(Model model) {
//		model.addAttribute("CarList", da.getCars());
		return "index";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
	
	@PostMapping("/register")
	public String postRegister(@RequestParam String username,
							   @RequestParam String password) {
		da.addUser(username, password);
		Long userID = da.findUserAccount(username).getUserID();
		da.addRole(userID, Long.valueOf(1));
		return "redirect:/secure";
	}
	
	@GetMapping("/secure")
	public String getSecure(Model model, Authentication authentication) {
		
		String username = authentication.getName();
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority ga: authentication.getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		model.addAttribute("CarList", da.getCars());
		model.addAttribute("username", username);
		return "secure/index";
	}
	
	@GetMapping("/secure/insert")
	public String getInsert(Model model) {
		
		model.addAttribute("Car", new Car());
		model.addAttribute("ManufacturerList", da.getManufacturers());
		return "secure/insert";
	}
	
	@PostMapping("/insert")
	public String postInsert(Model model,
							@RequestParam Long manufacturerID,
							@RequestParam String carModel,
							@RequestParam String color,
							@RequestParam int year,
							@RequestParam double price) {
		da.addCar(manufacturerID, carModel, year, color, price);
		model.addAttribute("CarList", da.getCars());
		return "index";
	}
	//Get form to update an specific car by ID
	@GetMapping("/update/{carID}")
	public String getUpdate(Model model, @PathVariable Long carID) {
		//Create an instance of a car and assign the car that is being updated
		Car car = da.getCarById(carID).get(0);
		model.addAttribute("Car", car);
		return "update";
	}
	
	@PostMapping("/update")
	public String postUpdate(Model model, 
			@ModelAttribute Car car) {
		
		da.updateCar(car);
		model.addAttribute("CarList", da.getCars());		
		return "index";
	}
	//Render delete form and get all cars
	@GetMapping("/delete")
	public String getDelete(Model model) {
		
		model.addAttribute("CarList", da.getCars());
		return "delete";
	}
	//Delete the car based on the user selection
	@PostMapping("/delete")
	public String postDelete(Model model,
			@ModelAttribute Car car) {
		da.deleteCar(car.getCarID());
		model.addAttribute("CarList", da.getCars());
		return "index";
	}
	
	@GetMapping("/permission-denied")
	public String getPermissionDenied() {
		return "error/permission-denied";
	}
}
