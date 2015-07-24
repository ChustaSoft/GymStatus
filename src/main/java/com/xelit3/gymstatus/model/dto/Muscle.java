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

@Entity
@Table(name="MUSCLES")
public class Muscle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name="MUSCLE_ID")
	private int id;
	@Basic
	private String muscleName;
	
	@OneToMany
	@JoinTable(name="MUSCLE_EXERCISE", joinColumns=@JoinColumn(name="MUSCLE_ID"),
		inverseJoinColumns=@JoinColumn(name="EXERCISE_ID")
	)
	private Collection<Exercise> exercisesList = new ArrayList<Exercise>();
	
	public Muscle(){}
	
	public Muscle(String name){
		this.setMusclename(name);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMusclename() {
		return muscleName;
	}
	public void setMusclename(String name) {
		this.muscleName = name;
	}

	public Collection<Exercise> getExercisesList() {
		return exercisesList;
	}

	public void setExercisesList(Collection<Exercise> exercisesList) {
		this.exercisesList = exercisesList;
	}

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
