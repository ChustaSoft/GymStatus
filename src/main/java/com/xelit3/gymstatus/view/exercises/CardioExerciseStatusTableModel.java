package com.xelit3.gymstatus.view.exercises;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.Exercise;

// TODO: Auto-generated Javadoc
/**
 * The Class CardioExerciseStatusTableModel.
 */
public class CardioExerciseStatusTableModel extends AbstractTableModel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The exercises. */
	private List<Exercise> theExercises;
	
	/** The column names. */
	private String[] columnNames = {"NAME", "INTENSITY", "TIME"};
	
	/**
	 * Instantiates a new cardio exercise status table model.
	 *
	 * @param aList the a list
	 */
	public CardioExerciseStatusTableModel(List<Exercise> aList){
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
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int arg0) {
		return columnNames[arg0];
	}

}
