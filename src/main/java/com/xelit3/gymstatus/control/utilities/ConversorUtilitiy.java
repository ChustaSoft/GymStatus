package com.xelit3.gymstatus.control.utilities;

import java.util.List;
import java.util.Vector;

import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.Muscle;

// TODO: Auto-generated Javadoc
/**
 * The Class ConversorUtilitiy.
 */
public class ConversorUtilitiy {

	/**
	 * Obtain muscles.
	 *
	 * @param aList the a list
	 * @return the vector
	 */
	public static Vector<Muscle> obtainMuscles(List<Muscle> aList){
		Vector<Muscle> tmpVector = new Vector<Muscle>();
		
		for (Muscle muscle : aList) {
			tmpVector.add(muscle);
		}
		
		return tmpVector;
	}
	
	/**
	 * Obtain exercises.
	 *
	 * @param aList the a list
	 * @return the vector
	 */
	public static Vector<Exercise> obtainExercises(List<Exercise> aList){
		Vector<Exercise> tmpVector = new Vector<Exercise>();
		
		for (Exercise exercise : aList) {
			tmpVector.add(exercise);
		}
		
		return tmpVector;
	}
}
