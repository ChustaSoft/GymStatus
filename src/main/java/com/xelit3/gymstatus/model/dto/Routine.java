package com.xelit3.gymstatus.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ROUTINES")
public class Routine implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name="ROUTINE_ID")
	private int id;
	
	@Column(name="NAME", nullable=false)
	private String routineName;
	
	@Column(name="INIT_DATE", nullable=false)
	private Date initDate;
	
	@Column(name="FINISH_DATE", nullable=false)
	private Date finishDate;
	
	@ManyToMany (cascade=CascadeType.PERSIST)
	private Collection<Exercise> exercisesIncluded = new ArrayList<Exercise>();
	
	public Routine(){}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRoutineName() {
		return routineName;
	}
	
	public void setRoutineName(String routineName) {
		this.routineName = routineName;
	}
	
	public Collection<Exercise> getExercises() {
		return exercisesIncluded;
	}
	
	public void setExercises(Collection<Exercise> exercises) {
		this.exercisesIncluded = exercises;
	}
	
	public Date getInitDate() {
		return initDate;
	}
	
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	
	public Date getFinishDate() {
		return finishDate;
	}
	
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
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
