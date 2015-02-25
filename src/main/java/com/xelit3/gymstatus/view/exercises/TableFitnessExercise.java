package com.xelit3.gymstatus.view.exercises;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExercise;

public class TableFitnessExercise extends JTable implements TableModel {

	private static final long serialVersionUID = 1L;
	
	private String[] columnNames = {"TYPE", "NAME", "MUSCLE"};
	private Object[][] exerciseListModel;

	
	
	public TableFitnessExercise() {
		super();		
	}
	
	public TableFitnessExercise(List<Exercise> exercisesList) {
		super();
		
		exerciseListModel = new Object[exercisesList.size()][columnNames.length];

		for (int i = 0; i < exercisesList.size(); i++) {
			FitnessExercise exercise = (FitnessExercise) exercisesList.get(i);
			
			exerciseListModel[i][0] = exercise.getClass().toString();
			exerciseListModel[i][1] = exercise.getExerciseName();
			exerciseListModel[i][2] = exercise.getTrainedMuscle().getMusclename();
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
