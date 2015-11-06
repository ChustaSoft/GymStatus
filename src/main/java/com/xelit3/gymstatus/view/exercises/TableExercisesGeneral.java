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

/**
 * Table general for Exercises.
 * Implemented with TableModel
 */
public class TableExercisesGeneral extends JPanel implements TableModel, MouseListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The column names. */
	private Object[] theColumnNames = {"Exercise type", "Name"};
	
	/** The parent. */
	private RoutineCreationPanel theParent;
	
	/** The scroll pane. */
	private JScrollPane theScrollPane;
	
	/** The table exercises. */
	private JTable theTableExercises;
	
	/** The exercises list. */
	private List<Exercise> theExercisesList = new ArrayList<Exercise>();
	
	/** The exercises. */
	private Object[][] theExercises;
	
	/**
	 * Create the panel.
	 *
	 * @param aParent the a parent
	 */
	public TableExercisesGeneral(RoutineCreationPanel aParent) {
		theTableExercises = new JTable(this);
		theParent = aParent;
		theExercises = new Object[0][0];
		createComponents();
	}
		
	/**
	 * Instantiates a new table exercises general.
	 *
	 * @param aList the a list
	 */
	public TableExercisesGeneral(List<Exercise> aList) {
		theExercisesList = aList;
		createObjectHash();		
		
		theTableExercises = new JTable(theExercises, theColumnNames);
		createComponents();
	}

	/**
	 * Creates the components.
	 */
	private void createComponents() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		theTableExercises.setPreferredScrollableViewportSize(new Dimension(500, 70));         
		theTableExercises.setFillsViewportHeight(true); 
		theScrollPane = new JScrollPane(theTableExercises);	
		theTableExercises.addMouseListener(this);
		add(theScrollPane);
	}	

	/**
	 * Creates the object hash.
	 */
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

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return theColumnNames.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return theColumnNames[columnIndex].toString();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return theExercises.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return theExercises[rowIndex][columnIndex];
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex < 2)
			return false;             
		else
			return true;  
	}	
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#addTableModelListener(javax.swing.event.TableModelListener)
	 */
	@Override
	public void addTableModelListener(TableModelListener l) {}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#removeTableModelListener(javax.swing.event.TableModelListener)
	 */
	@Override
	public void removeTableModelListener(TableModelListener l) {}

	/**
	 * Adds the new row.
	 *
	 * @param anExercise the an exercise
	 */
	public void addNewRow(Object anExercise) {
		//Añadimos el ejercicio creado en el panel y devuelto mediante Observer-observable a la tabla, y refrescamos el panel
		Exercise tmpExercise = (Exercise) anExercise;
		
		if(!this.getExercisesList().contains(tmpExercise)){
			theExercisesList.add(tmpExercise);
			refreshTable();
		}		
	}

	/**
	 * Refresh the table data and view
	 */
	private void refreshTable() {
		createObjectHash();
		theTableExercises = new JTable(theExercises, theColumnNames);
		this.updateUI();
	}
	
	/**
	 * Delete a row for that table
	 *
	 * @param anExercise the an exercise
	 */
	public void deleteRow(Object anExercise) {
		Exercise tmpExercise = (Exercise) anExercise;
		
		if(this.getExercisesList().contains(tmpExercise)){
			theExercisesList.remove(tmpExercise);
			refreshTable();
		}	
	}

	/**
	 * Gets the exercises list.
	 *
	 * @return the theExercisesList
	 */
	public List<Exercise> getExercisesList() {
		return theExercisesList;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = theTableExercises.rowAtPoint(e.getPoint());
//		int col = theTableExercises.columnAtPoint(e.getPoint());
		if(row >= 0 && row <= getExercisesList().size())
			theParent.openExerciseStatusCreationWindow(getExercisesList().get(row));        	
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
