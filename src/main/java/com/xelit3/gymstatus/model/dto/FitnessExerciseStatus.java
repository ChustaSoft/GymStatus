package com.xelit3.gymstatus.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

/**
 * FitnessExerciseStatus bean
 */
@Entity
@DiscriminatorValue(value = "FITNESS_STATUS") 
public class FitnessExerciseStatus extends FitnessExercise {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The series. */
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable (name="FITNESS_EXERCISE_SERIES",
		joinColumns = @JoinColumn(name="EXERCISE_ID")
	)
	private List<Serie> series = new ArrayList<Serie>();
	
	/**
	 * Instantiates a new fitness exercise status.
	 */
	public FitnessExerciseStatus(){
		
	}
	
	/**
	 * Instantiates a new fitness exercise status.
	 *
	 * @param anExercise the an exercise
	 */
	public FitnessExerciseStatus(FitnessExercise anExercise){
		this.setExerciseName(anExercise.getExerciseName());
		this.setTrainedMuscle(anExercise.getTrainedMuscle());
	}
	
	/**
	 * Instantiates a new fitness exercise status.
	 *
	 * @param name the name
	 * @param muscle the muscle
	 */
	public FitnessExerciseStatus(String name, Muscle muscle) {
		super(name, muscle);
	}

	/**
	 * Gets the series.
	 *
	 * @return the series
	 */
	public List<Serie> getSeries() {
		return series;
	}

	/**
	 * Sets the series.
	 *
	 * @param series the new series
	 */
	public void setSeries(List<Serie> series) {
		this.series = series;
	}
	
}
