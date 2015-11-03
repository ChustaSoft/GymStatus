package com.xelit3.gymstatus.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Exercise.
 */
@Entity
@Table(name="EXERCISES")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	name="EXERCISE_TYPE",
	discriminatorType=DiscriminatorType.STRING
)
public abstract class Exercise implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id @GeneratedValue
	@Column(name="EXERCISE_ID")
	private int id;
	
	/** The exercise name. */
	@Column(name="EXERCISE_NAME")
	private String exerciseName;
	
	/** The routines including. */
	@ManyToMany (mappedBy="exercisesIncluded")
	private Collection<Routine> routinesIncluding = new ArrayList<Routine>();
	
	/**
	 * Instantiates a new exercise.
	 */
	public Exercise(){}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the exercise name.
	 *
	 * @return the exercise name
	 */
	public String getExerciseName() {
		return exerciseName;
	}
	
	/**
	 * Sets the exercise name.
	 *
	 * @param exerciseName the new exercise name
	 */
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	
	/**
	 * Gets the routines including.
	 *
	 * @return the routines including
	 */
	public Collection<Routine> getRoutinesIncluding() {
		return routinesIncluding;
	}
	
	/**
	 * Sets the routines including.
	 *
	 * @param routinesIncluding the new routines including
	 */
	public void setRoutinesIncluding(Collection<Routine> routinesIncluding) {
		this.routinesIncluding = routinesIncluding;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getExerciseName();
	}
		
}
