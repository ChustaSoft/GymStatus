package com.xelit3.gymstatus.view.exercises;

import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * Table for CardioExerciseStatus.
 */
public class TableCardioExerciseStatus extends JTable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/**
	 * Instantiates a new table cardio exercise status.
	 *
	 * @param aModel the a model
	 */
	public TableCardioExerciseStatus(TableModel aModel) {
		super(aModel);		
	}

}
