package com.xelit3.gymstatus.view.exercises;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.Exercise;

public class CardioExerciseStatusTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private List<Exercise> theExercises;
	private String[] columnNames = {"NAME", "INTENSITY", "TIME"};
	
	public CardioExerciseStatusTableModel(List<Exercise> aList){
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
		switch(columnNames[columnIndex]){			
			
			case "NAME":
				return theExercises.get(rowIndex).getExerciseName();
			
			case "INTENSITY":
				return ((CardioExerciseStatus) theExercises.get(rowIndex)).getIntensity().name();
			
			case "TIME":
				return ((CardioExerciseStatus) theExercises.get(rowIndex)).getTime();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columnNames[arg0];
	}

}
