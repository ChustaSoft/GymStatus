package com.xelit3.gymstatus.view.exercises;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FitnessExerciseTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private List<Object[]> exercises = new ArrayList<Object[]>();
	private String[] columnNames = {"TYPE", "NAME", "MUSCLE"};
	
	public FitnessExerciseTableModel() {
		super();
	}

	@Override
	public int getColumnCount() {		
		return columnNames.length;
	}

	@Override
	public int getRowCount() {		
		return exercises.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return exercises.get(rowIndex)[columnIndex];
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columnNames[arg0];
	}

}
