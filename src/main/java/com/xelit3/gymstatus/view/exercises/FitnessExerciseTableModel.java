package com.xelit3.gymstatus.view.exercises;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExercise;
import com.xelit3.gymstatus.model.dto.FitnessExerciseStatus;

public class FitnessExerciseTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private List<Exercise> theExercises;
	private String[] columnNames = {"NAME", "MUSCLE", "MAX WEIGHT"};
	
	public FitnessExerciseTableModel(List<Exercise> aList){
		theExercises = aList;
	}

	@Override
	public int getColumnCount() {		
		return columnNames.length;
	}

	@Override
	public int getRowCount() {		
		return theExercises.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		FitnessExerciseStatus tmpExercise = ((FitnessExerciseStatus) theExercises.get(rowIndex));
		switch(columnNames[columnIndex]){			
			case "NAME":
				return tmpExercise.getExerciseName();
			case "MUSCLE":
				return tmpExercise.getTrainedMuscle();
			case "MAX WEIGHT":
				
				return tmpExercise.getSeries().toArray()[tmpExercise.getSeries().size() - 1].toString();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columnNames[arg0];
	}

}
