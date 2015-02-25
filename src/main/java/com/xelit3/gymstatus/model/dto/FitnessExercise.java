package com.xelit3.gymstatus.model.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "FITNESS") 
public class FitnessExercise extends Exercise {

	private static final long serialVersionUID = 1L;
		
	@ManyToOne
	private Muscle trainedMuscle;
		
	public FitnessExercise(){}
	
	public FitnessExercise(String name, Muscle muscle){
		this.setTrainedMuscle(muscle);
		this.setExerciseName(name);
	}
		
	public Muscle getTrainedMuscle() {
		return trainedMuscle;
	}
	
	public void setTrainedMuscle(Muscle trainedMuscle) {
		this.trainedMuscle = trainedMuscle;
	}	
	
}