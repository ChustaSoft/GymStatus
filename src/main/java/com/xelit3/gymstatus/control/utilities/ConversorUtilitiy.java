package com.xelit3.gymstatus.control.utilities;

import java.util.List;
import java.util.Vector;

import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.Muscle;

/**
 * The Class ConversorUtilitiy.
 * This class has static methods used in the entire application
 */
public class ConversorUtilitiy {

	/**
	 * Conversor from a Muscle list to a Muscle Vector, useful for JComboBox
	 *
	 * @param aList the a list of Muscle
	 * @return the vector of Muscle
	 */
	public static Vector<Muscle> obtainMuscles(List<Muscle> aList){
		Vector<Muscle> tmpVector = new Vector<Muscle>();
		
		for (Muscle muscle : aList) {
			tmpVector.add(muscle);
		}
		
		return tmpVector;
	}
	
	/**
	 * Conversor from a Exercise list to a Muscle Vector, useful for JComboBox
	 *
	 * @param aList the a list of Muscle
	 * @return the vector of Muscle
	 */
	public static Vector<Exercise> obtainExercises(List<Exercise> aList){
		Vector<Exercise> tmpVector = new Vector<Exercise>();
		
		for (Exercise exercise : aList) {
			tmpVector.add(exercise);
		}
		
		return tmpVector;
	}
}
