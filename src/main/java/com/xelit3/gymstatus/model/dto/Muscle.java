package com.xelit3.gymstatus.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Muscle bean
 */
@Entity
@Table(name="MUSCLES")
public class Muscle implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id @GeneratedValue
	@Column(name="MUSCLE_ID")
	private int id;
	
	/** The muscle name. */
	@Basic
	private String muscleName;
	
	/** The exercises list. */
	@OneToMany
	@JoinTable(name="MUSCLE_EXERCISE", joinColumns=@JoinColumn(name="MUSCLE_ID"),
		inverseJoinColumns=@JoinColumn(name="EXERCISE_ID")
	)
	private Collection<Exercise> exercisesList = new ArrayList<Exercise>();
	
	/**
	 * Instantiates a new muscle.
	 */
	public Muscle(){}
	
	/**
	 * Instantiates a new muscle.
	 *
	 * @param name the name
	 */
	public Muscle(String name){
		this.setMusclename(name);
	}
	
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
	 * Gets the musclename.
	 *
	 * @return the musclename
	 */
	public String getMusclename() {
		return muscleName;
	}
	
	/**
	 * Sets the musclename.
	 *
	 * @param name the new musclename
	 */
	public void setMusclename(String name) {
		this.muscleName = name;
	}

	/**
	 * Gets the exercises list.
	 *
	 * @return the exercises list
	 */
	public Collection<Exercise> getExercisesList() {
		return exercisesList;
	}

	/**
	 * Sets the exercises list.
	 *
	 * @param exercisesList the new exercises list
	 */
	public void setExercisesList(Collection<Exercise> exercisesList) {
		this.exercisesList = exercisesList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getMusclename();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Muscle other = (Muscle) obj;
		if (id != other.id)
			return false;
		return true;
	}	
	
}
