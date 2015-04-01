package com.xelit3.gymstatus.control.utilities;

import java.util.List;
import java.util.Vector;

import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.Muscle;

public class ConversorUtilitiy {

	public static Vector<Muscle> obtainMuscles(List<Muscle> aList){
		Vector<Muscle> tmpVector = new Vector<Muscle>();
		
		for (Muscle muscle : aList) {
			tmpVector.add(muscle);
		}
		
		return tmpVector;
	}
	
	public static Vector<Exercise> obtainExercises(List<Exercise> aList){
		Vector<Exercise> tmpVector = new Vector<Exercise>();
		
		for (Exercise exercise : aList) {
			tmpVector.add(exercise);
		}
		
		return tmpVector;
	}
}
