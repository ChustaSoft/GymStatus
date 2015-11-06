package com.xelit3.gymstatus.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Serie embeddable bean
 */
@Embeddable
public class Serie implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The repetitions. */
	@Column(name="REPETITIONS")
	int repetitions;
	
	/** The weight. */
	@Column(name="WEIGHT")
	double weight;
	
	/** The with own weight. */
	@Column(name="INCLUDES_OWN_WEIGHT")
	private boolean withOwnWeight;
	
	/**
	 * Instantiates a new serie.
	 */
	public Serie(){}
	
	/**
	 * Instantiates a new serie.
	 *
	 * @param repetitions the repetitions
	 * @param weight the weight
	 * @param withOwnWeight the with own weight
	 */
	public Serie(int repetitions, double weight, boolean withOwnWeight){
		this.setRepetitions(repetitions);
		this.setWeight(weight);
		this.setWithOwnWeight(withOwnWeight);
	}

	/**
	 * Gets the repetitions.
	 *
	 * @return the repetitions
	 */
	public int getRepetitions() {
		return repetitions;
	}

	/**
	 * Sets the repetitions.
	 *
	 * @param repetitions the new repetitions
	 */
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Checks if is with own weight.
	 *
	 * @return true, if is with own weight
	 */
	public boolean isWithOwnWeight() {
		return withOwnWeight;
	}

	/**
	 * Sets the with own weight.
	 *
	 * @param withOwnWeight the new with own weight
	 */
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