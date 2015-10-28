package com.xelit3.gymstatus.view.exercises;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

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
	
//	private Vector<String> theColumnNames = new Vector<String>();
	private Object[] theColumnNames = {"Exercise type", "Name"};
	
	private JScrollPane theScrollPane;
	private JTable theTableExercises;
	
	private Object[][] theExercises;
	/**
	 * Create the panel.
	 */
	public TableExercisesGeneral(List<Exercise> aList) {
		createObjectHash(aList);
		
		setLayout(new GridLayout(1, 0, 0, 0));
		theTableExercises = new JTable(theExercises, theColumnNames);
		theTableExercises.setPreferredScrollableViewportSize(new Dimension(500, 70));         
		theTableExercises.setFillsViewportHeight(true); 
		theScrollPane = new JScrollPane(theTableExercises);
		theScrollPane.setSize(569, 167);
		theScrollPane.setLocation(30, 198);
		add(theScrollPane);
	}

	private void createObjectHash(List<Exercise> aList) {
		theExercises = new Object[aList.size()][theColumnNames.length];
		for(Exercise cntE : aList){
			theExercises[aList.indexOf(cntE)][0] = cntE.getClass().getName();
			theExercises[aList.indexOf(cntE)][1] = cntE.getExerciseName();
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public int getColumnCount() {
		return theColumnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return theColumnNames[columnIndex].toString();
	}

	@Override
	public int getRowCount() {
		return theExercises.length;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return theExercises[rowIndex][columnIndex];
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
