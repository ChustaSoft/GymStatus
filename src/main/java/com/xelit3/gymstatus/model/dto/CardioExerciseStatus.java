package com.xelit3.gymstatus.model.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CARDIO_STATUS") 
public class CardioExerciseStatus extends CardioExercise {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum CardioExerciseIntensity {
	    SOFT, MEDIUM, HIGH 
	}
	
	private CardioExerciseIntensity intensity;
	private long time;
	
	public CardioExerciseStatus(){
		
	}
	
	public CardioExerciseStatus(String name){
		super(name);
	}
	
	public CardioExerciseStatus(String name, Muscle muscle, CardioExerciseIntensity intensity, long time){
		this.setExerciseName(name);
		this.setIntensity(intensity);
		this.setTime(time);
	}
	
	public CardioExerciseStatus(CardioExercise anExercise) {
		this.setExerciseName(anExercise.getExerciseName());		
	}

	public CardioExerciseIntensity getIntensity() {
		return intensity;
	}

	public void setIntensity(CardioExerciseIntensity intensity) {
		this.intensity = intensity;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
