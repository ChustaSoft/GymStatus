package com.xelit3.gymstatus.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Serie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="REPETITIONS")
	int repetitions;
	@Column(name="WEIGHT")
	double weight;
	@Column(name="INCLUDES_OWN_WEIGHT")
	private boolean withOwnWeight;
	
	public Serie(){}
	
	public Serie(int repetitions, double weight, boolean withOwnWeight){
		this.setRepetitions(repetitions);
		this.setWeight(weight);
		this.setWithOwnWeight(withOwnWeight);
	}

	public int getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isWithOwnWeight() {
		return withOwnWeight;
	}

	public void setWithOwnWeight(boolean withOwnWeight) {
		this.withOwnWeight = withOwnWeight;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[REP=" + repetitions + ", WEIGHT=" + weight + ", OWN?=" + withOwnWeight + "]";
	}
	
}