package com.xelit3.gymstatus.view.exercises;

import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 * The Class TableFitnessExerciseStatus.
 */
public class TableFitnessExerciseStatus extends JTable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new table fitness exercise status.
	 *
	 * @param aModel the a model
	 */
	public TableFitnessExerciseStatus(TableModel aModel) {
		super(aModel);	
		this.setBounds(25, 25, 600, 300);
	}	

}
