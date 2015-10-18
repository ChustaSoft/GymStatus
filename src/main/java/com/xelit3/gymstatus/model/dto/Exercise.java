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

@Entity
@Table(name="EXERCISES")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	name="EXERCISE_TYPE",
	discriminatorType=DiscriminatorType.STRING
)
public abstract class Exercise implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name="EXERCISE_ID")
	private int id;
	@Column(name="EXERCISE_NAME")
	private String exerciseName;
	
	@ManyToMany (mappedBy="exercisesIncluded")
	private Collection<Routine> routinesIncluding = new ArrayList<Routine>();
	
	public Exercise(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExerciseName() {
		return exerciseName;
	}
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	public Collection<Routine> getRoutinesIncluding() {
		return routinesIncluding;
	}
	public void setRoutinesIncluding(Collection<Routine> routinesIncluding) {
		this.routinesIncluding = routinesIncluding;
	}

	@Override
	public String toString() {
		return this.getExerciseName();
	}
		
}
