package com.xelit3.gymstatus.model.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class FitnessExercise.
 */
@Entity
@DiscriminatorValue(value = "FITNESS") 
public class FitnessExercise extends Exercise {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The trained muscle. */
	@ManyToOne
	private Muscle trainedMuscle;
		
	/**
	 * Instantiates a new fitness exercise.
	 */
	public FitnessExercise(){}
	
	/**
	 * Instantiates a new fitness exercise.
	 *
	 * @param name the name
	 * @param muscle the muscle
	 */
	public FitnessExercise(String name, Muscle muscle){
		this.setTrainedMuscle(muscle);
		this.setExerciseName(name);
	}
		
	/**
	 * Gets the trained muscle.
	 *
	 * @return the trained muscle
	 */
	public Muscle getTrainedMuscle() {
		return trainedMuscle;
	}
	
	/**
	 * Sets the trained muscle.
	 *
	 * @param trainedMuscle the new trained muscle
	 */
	public void setTrainedMuscle(Muscle trainedMuscle) {
		this.trainedMuscle = trainedMuscle;
	}	
	
}