package com.xelit3.gymstatus.model.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * CardioExerciseStatus bean
 */
@Entity
@DiscriminatorValue(value = "CARDIO_STATUS") 
public class CardioExerciseStatus extends CardioExercise {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The Enum CardioExerciseIntensity.
	 */
	public enum CardioExerciseIntensity {
    	SOFT, MEDIUM, HIGH 
	}
	
	/** The intensity. */
	private CardioExerciseIntensity intensity;
	
	/** The time. */
	private long time;
	
	/**
	 * Instantiates a new cardio exercise status.
	 */
	public CardioExerciseStatus(){
		
	}
	
	/**
	 * Instantiates a new cardio exercise status.
	 *
	 * @param name the name
	 */
	public CardioExerciseStatus(String name){
		super(name);
	}
	
	/**
	 * Instantiates a new cardio exercise status.
	 *
	 * @param name the name
	 * @param muscle the muscle
	 * @param intensity the intensity
	 * @param time the time
	 */
	public CardioExerciseStatus(String name, Muscle muscle, CardioExerciseIntensity intensity, long time){
		this.setExerciseName(name);
		this.setIntensity(intensity);
		this.setTime(time);
	}
	
	/**
	 * Instantiates a new cardio exercise status.
	 *
	 * @param anExercise the an exercise
	 */
	public CardioExerciseStatus(CardioExercise anExercise) {
		this.setExerciseName(anExercise.getExerciseName());		
	}

	/**
	 * Gets the intensity.
	 *
	 * @return the intensity
	 */
	public CardioExerciseIntensity getIntensity() {
		return intensity;
	}

	/**
	 * Sets the intensity.
	 *
	 * @param intensity the new intensity
	 */
	public void setIntensity(CardioExerciseIntensity intensity) {
		this.intensity = intensity;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(long time) {
		this.time = time;
	}

}
