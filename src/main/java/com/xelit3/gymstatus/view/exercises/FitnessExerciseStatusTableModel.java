package com.xelit3.gymstatus.view.exercises;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExercise;
import com.xelit3.gymstatus.model.dto.FitnessExerciseStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class FitnessExerciseStatusTableModel.
 */
public class FitnessExerciseStatusTableModel extends AbstractTableModel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The exercises. */
	private List<Exercise> theExercises;
	
	/** The column names. */
	private String[] columnNames = {"NAME", "MUSCLE", "MAX WEIGHT"};
	
	/**
	 * Instantiates a new fitness exercise status table model.
	 *
	 * @param aList the a list
	 */
	public FitnessExerciseStatusTableModel(List<Exercise> aList){
		theExercises = aList;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {		
		return columnNames.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {		
		return theExercises.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
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
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int arg0) {
		return columnNames[arg0];
	}

}
