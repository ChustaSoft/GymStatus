package com.xelit3.gymstatus.view.exercises;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.Exercise;

public class TableCardioExercise extends JTable implements TableModel {

	private static final long serialVersionUID = 1L;
	
	private String[] columnNames = {"TYPE", "NAME", "INTENSITY", "TIME"};
	private Object[][] exerciseListModel;
	
	
	public TableCardioExercise() {
		super();		
	}
	
	public TableCardioExercise(List<Exercise> exercisesList) {
		super();
		
		exerciseListModel = new Object[exercisesList.size()][columnNames.length];

		for (int i = 0; i < exercisesList.size(); i++) {
			CardioExerciseStatus exercise = (CardioExerciseStatus) exercisesList.get(i);
			
			exerciseListModel[i][0] = exercise.getClass().toString();
			exerciseListModel[i][1] = exercise.getExerciseName();
			exerciseListModel[i][2] = exercise.getIntensity().name();
			exerciseListModel[i][3] = exercise.getTime();
		}		
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}
