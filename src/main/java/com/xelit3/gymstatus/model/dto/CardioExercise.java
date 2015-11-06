package com.xelit3.gymstatus.model.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * CardioExercise bean
 */
@Entity
@DiscriminatorValue(value = "CARDIO") 
public class CardioExercise extends Exercise{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/**
	 * Instantiates a new cardio exercise.
	 */
	public CardioExercise(){}

	/**
	 * Instantiates a new cardio exercise.
	 *
	 * @param name the name
	 */
	public CardioExercise(String name){
		this.setExerciseName(name);	
		
	}
	
}