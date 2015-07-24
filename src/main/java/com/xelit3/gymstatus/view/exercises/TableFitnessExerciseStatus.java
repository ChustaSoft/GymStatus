package com.xelit3.gymstatus.view.exercises;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableFitnessExerciseStatus extends JTable {

	private static final long serialVersionUID = 1L;
	
	public TableFitnessExerciseStatus(TableModel aModel) {
		super(aModel);	
		this.setBounds(25, 25, 600, 300);
	}	

}
