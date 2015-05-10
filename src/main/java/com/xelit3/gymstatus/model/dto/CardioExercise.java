package com.xelit3.gymstatus.model.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CARDIO") 
public class CardioExercise extends Exercise{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public CardioExercise(){}

	public CardioExercise(String name){
		this.setExerciseName(name);	
		
	}
	
}