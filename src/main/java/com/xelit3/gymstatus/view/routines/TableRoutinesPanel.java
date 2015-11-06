package com.xelit3.gymstatus.view.routines;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;

import com.xelit3.gymstatus.control.events.EventAction;
import com.xelit3.gymstatus.control.events.EventAction.Action;

/**
 * Panel for Routine list
 */
public class TableRoutinesPanel extends JScrollPane implements Observer {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The model. */
	private TableRoutinesModel model;
	
	/** The table routines. */
	private TableRoutines tableRoutines;

	/**
	 * @param view
	 */
	public TableRoutinesPanel(Component view) {
		super(view);
		tableRoutines = (TableRoutines) view;
		model = (TableRoutinesModel) tableRoutines.getModel();
		tableRoutines.getController().addObserver(this);;
	}

	/**
	 * @return the model
	 */
	public TableRoutinesModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(TableRoutinesModel model) {
		this.model = model;
	}

	/**
	 * @return the tableRoutines
	 */
	public TableRoutines getTableRoutines() {
		return tableRoutines;
	}

	/**
	 * @param tableRoutines the tableRoutines to set
	 */
	public void setTableRoutines(TableRoutines tableRoutines) {
		this.tableRoutines = tableRoutines;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		EventAction tmpEA = (EventAction) arg;
		
		if(tmpEA.getAction() == Action.DELETE){
			if(getModel().getRoutinesList().contains(tmpEA.getTarget())){
				tableRoutines.getFrameModal().setVisible(false);
				getModel().getRoutinesList().remove(tmpEA.getTarget());
				getModel().fireTableDataChanged();
			}
		}
	}

}
