package ca.sheridancollege.munevarm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.munevarm.beans.Car;
import ca.sheridancollege.munevarm.database.DatabaseAccess;

@Controller
public class HomeController {
	
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String getIndex(Model model) {
		
		model.addAttribute("CarList", da.getCars());
		return "index";
	}
	
	@GetMapping("/insert")
	public String getInsert(Model model) {
		model.addAttribute("Car", new Car());
		model.addAttribute("ManufacturerList", da.getManufacturers());
		return "insert";
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
		//if we want to add multiple, redirect to insert
//		model.addAttribute("Car", new Car());
//		model.addAttribute("ManufacturerList", da.getManufacturers());
		return "index";
	}
	
	@GetMapping("/update")
	public String getUpdate() {
		return "update";
	}
	
	@PostMapping("/update")
	public String postUpdate() {
		return "update";
	}
	
	@GetMapping("/delete")
	public String getDelete() {
		return "delete";
	}
	
	@PostMapping("/delete")
	public String postDelete() {
		return "delete";
	}
}
