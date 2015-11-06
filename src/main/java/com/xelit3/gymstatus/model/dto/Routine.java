package com.xelit3.gymstatus.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Routine bean.
 */
@Entity
@Table(name="ROUTINES")
public class Routine implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id @GeneratedValue
	@Column(name="ROUTINE_ID")
	private int id;
	
	/** The routine name. */
	@Column(name="NAME", nullable=false, unique=true)
	private String routineName;
	
	/** The init date. */
	@Column(name="INIT_DATE", nullable=false)
	private Date initDate;
	
	/** The finish date. */
	@Column(name="FINISH_DATE", nullable=false)
	private Date finishDate;
	
	/** The exercises included. */
	@ManyToMany (cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Collection<Exercise> exercisesIncluded = new ArrayList<Exercise>();
	
	/**
	 * Instantiates a new routine.
	 */
	public Routine(){}
	
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
	 * Gets the routine name.
	 *
	 * @return the routine name
	 */
	public String getRoutineName() {
		return routineName;
	}
	
	/**
	 * Sets the routine name.
	 *
	 * @param routineName the new routine name
	 */
	public void setRoutineName(String routineName) {
		this.routineName = routineName;
	}
	
	/**
	 * Gets the exercises.
	 *
	 * @return the exercises
	 */
	public Collection<Exercise> getExercises() {
		return exercisesIncluded;
	}
	
	/**
	 * Sets the exercises.
	 *
	 * @param exercises the new exercises
	 */
	public void setExercises(Collection<Exercise> exercises) {
		this.exercisesIncluded = exercises;
	}
	
	/**
	 * Gets the inits the date.
	 *
	 * @return the inits the date
	 */
	public Date getInitDate() {
		return initDate;
	}
	
	/**
	 * Sets the inits the date.
	 *
	 * @param initDate the new inits the date
	 */
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	
	/**
	 * Gets the finish date.
	 *
	 * @return the finish date
	 */
	public Date getFinishDate() {
		return finishDate;
	}
	
	/**
	 * Sets the finish date.
	 *
	 * @param finishDate the new finish date
	 */
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid(){
		boolean tmpValid = true;
		try{
			if("".equals(getRoutineName()))
				tmpValid = false;
			if(initDate.compareTo(finishDate) > 0)
				tmpValid = false;
			if(getExercises().size() < 1)
				tmpValid = false;
			
		}
		catch(Exception e){
			tmpValid = false;
		}
		
		return tmpValid;
	}

}
