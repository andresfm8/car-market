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
	public String getIndex() {
		return "index";
	}
	
	@GetMapping("/insert")
	public String getInsert(Model model) {
		
		model.addAttribute("Car", new Car());
		return "insert";
	}
	
	@PostMapping("/insert")
	public String postInsert(Model model,
							@RequestParam Long manufacturerId,
							@RequestParam String carModel,
							@RequestParam String color,
							@RequestParam int year,
							@RequestParam double price) {
		da.addCar(manufacturerId, carModel, color, year, price);
		return "insert";
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
