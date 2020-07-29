package ca.sheridancollege.munevarm.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable{
	
	private Long carId;
	private String carModel;
	private String color;
	private int year;
	private double price;
}
