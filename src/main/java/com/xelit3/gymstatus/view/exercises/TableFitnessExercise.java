package com.xelit3.gymstatus.view.exercises;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableFitnessExercise extends JTable {

	private static final long serialVersionUID = 1L;
	
	public TableFitnessExercise(TableModel aModel) {
		super(aModel);	
		this.setBounds(25, 25, 600, 300);
	}	

}
