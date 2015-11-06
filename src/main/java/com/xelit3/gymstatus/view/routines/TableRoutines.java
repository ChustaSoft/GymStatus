package com.xelit3.gymstatus.view.routines;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.control.settings.AppSettings;
import com.xelit3.gymstatus.model.dto.Routine;

/**
 * Table for Routine
 */
public class TableRoutines extends JTable implements MouseListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The main controller. */
	private Controller mainController = new Controller();
	
	/** The table model. */
	private TableRoutinesModel tableModel = null;
	
	/** The frame for RoutineCreationPanel modal window */
	private JFrame frameStatusCreation = null;

	/**
	 * Instantiates a new table routines.
	 *
	 * @param aModel
	 *            the arg0
	 */
	public TableRoutines(TableModel aModel) {
		super(aModel);

		tableModel = (TableRoutinesModel) aModel;

		this.setBounds(25, 25, 600, 300);
		this.addMouseListener(this);

		List<Routine> tmpRoutines = mainController.getAllRoutines();

		for (Routine tmpR : tmpRoutines) {
			tableModel.addRow(tmpR);
		}

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = this.rowAtPoint(e.getPoint());
		if (row >= 0 && row <= tableModel.getRoutinesList().size())
			openRoutineCreationWindow(tableModel.getRoutinesList().get(row));

	}

	/**
	 * Open a JFrame with RoutineCreationPanel for modify or remove a routine
	 *
	 * @param routine the routine to manage
	 */
	private void openRoutineCreationWindow(Routine routine) {
		frameStatusCreation = new JFrame();
		frameStatusCreation.setContentPane(new RoutineCreationPanel(routine, mainController));
		frameStatusCreation.setBounds(AppSettings.getInstance().getMainWindowPosX(), AppSettings.getInstance().getMainWindowPosY(), 640, 480);
		frameStatusCreation.setVisible(true);
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

	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public Controller getController() {		
		return this.mainController;
	}
	
	/**
	 * Gets the frame modal.
	 *
	 * @return the frame modal
	 */
	public JFrame getFrameModal(){
		return frameStatusCreation;
	}

}
