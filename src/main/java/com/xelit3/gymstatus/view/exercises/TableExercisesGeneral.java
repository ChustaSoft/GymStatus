package com.xelit3.gymstatus.view.exercises;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.xelit3.gymstatus.model.dto.Exercise;

public class TableExercisesGeneral extends JPanel implements TableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<String> theColumnNames = new Vector<String>();
	
	private JScrollPane theScrollPane;
	private JTable theTableExercises;
	
	private Vector<Exercise> theExercises = new Vector<Exercise>();	
	/**
	 * Create the panel.
	 */
	public TableExercisesGeneral() {
		theColumnNames.addElement("Exercise type");
		theColumnNames.addElement("Name");
		theTableExercises = new JTable(theExercises, theColumnNames);
		theScrollPane = new JScrollPane(theTableExercises);
		theScrollPane.setSize(569, 167);
		theScrollPane.setLocation(30, 198);
		add(theScrollPane);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public int getColumnCount() {
		return theColumnNames.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return theColumnNames.get(columnIndex);
	}

	@Override
	public int getRowCount() {
		return theExercises.size();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
			case 0:
				return theExercises.get(rowIndex).getClass().toString();
				
			case 1:
				return theExercises.get(rowIndex).getExerciseName();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex < 2)
			return false;             
		else
			return true;  
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
