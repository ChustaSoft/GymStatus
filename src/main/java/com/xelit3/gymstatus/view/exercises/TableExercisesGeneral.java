package com.xelit3.gymstatus.view.exercises;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.view.routines.RoutineCreationPanel;

public class TableExercisesGeneral extends JPanel implements TableModel, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object[] theColumnNames = {"Exercise type", "Name"};
	
	private RoutineCreationPanel theParent;
	
	private JScrollPane theScrollPane;
	private JTable theTableExercises;
	
	private List<Exercise> theExercisesList = new ArrayList<Exercise>();
	private Object[][] theExercises;
	
	/**
	 * Create the panel.
	 */
	public TableExercisesGeneral(RoutineCreationPanel aParent) {
		theTableExercises = new JTable(this);
		theParent = aParent;
		theExercises = new Object[0][0];
		createComponents();
	}
		
	public TableExercisesGeneral(List<Exercise> aList) {
		theExercisesList = aList;
		createObjectHash();		
		
		theTableExercises = new JTable(theExercises, theColumnNames);
		createComponents();
	}

	private void createComponents() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		theTableExercises.setPreferredScrollableViewportSize(new Dimension(500, 70));         
		theTableExercises.setFillsViewportHeight(true); 
		theScrollPane = new JScrollPane(theTableExercises);	
		theTableExercises.addMouseListener(this);
		add(theScrollPane);
	}	

	private void createObjectHash() {
		theExercises = new Object[theExercisesList.size()][theColumnNames.length];
		for(Exercise cntE : theExercisesList){
			switch(cntE.getClass().getSimpleName()){
			
				case "FitnessExerciseStatus":
					theExercises[theExercisesList.indexOf(cntE)][0] = "FITNESS";
					break;
					
				case "CardioExerciseStatus":
					theExercises[theExercisesList.indexOf(cntE)][0] = "CARDIO";
					break;
			}
//			theExercises[theExercisesList.indexOf(cntE)][0] = cntE.getClass().getName();
			theExercises[theExercisesList.indexOf(cntE)][1] = cntE.getExerciseName();
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
	public void addTableModelListener(TableModelListener l) {}
	
	@Override
	public void removeTableModelListener(TableModelListener l) {}

	public void addNewRow(Object anExercise) {
		//Añadimos el ejercicio creado en el panel y devuelto mediante Observer-observable a la tabla, y refrescamos el panel
		Exercise tmpExercise = (Exercise) anExercise;
		
		if(!this.getExercisesList().contains(tmpExercise)){
			theExercisesList.add(tmpExercise);
			createObjectHash();
			theTableExercises = new JTable(theExercises, theColumnNames);
			this.updateUI();
		}		
	}

	/**
	 * @return the theExercisesList
	 */
	public List<Exercise> getExercisesList() {
		return theExercisesList;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = theTableExercises.rowAtPoint(e.getPoint());
//		int col = theTableExercises.columnAtPoint(e.getPoint());
		if(row >= 0 && row <= getExercisesList().size())
			theParent.openExerciseStatusCreationWindow(getExercisesList().get(row));        	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
