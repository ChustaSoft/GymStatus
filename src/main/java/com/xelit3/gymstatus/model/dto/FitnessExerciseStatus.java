package com.xelit3.gymstatus.model.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
@DiscriminatorValue(value = "FITNESS_STATUS") 
public class FitnessExerciseStatus extends FitnessExercise {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable (name="FITNESS_EXERCISE_SERIES",
		joinColumns = @JoinColumn(name="EXERCISE_ID")
	)
	private Collection<Serie> series = new ArrayList<Serie>();
	
	public FitnessExerciseStatus(){
		
	}
	
	public FitnessExerciseStatus(String name, Muscle muscle) {
		super(name, muscle);
	}

	public Collection<Serie> getSeries() {
		return series;
	}

	public void setSeries(Collection<Serie> series) {
		this.series = series;
	}

}
