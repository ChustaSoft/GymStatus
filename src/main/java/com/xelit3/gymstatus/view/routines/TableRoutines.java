package com.xelit3.gymstatus.view.routines;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.control.settings.AppSettings;
import com.xelit3.gymstatus.model.dto.FitnessExercise;
import com.xelit3.gymstatus.model.dto.Routine;
import com.xelit3.gymstatus.view.exercises.FitnessExerciseStatusPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class TableRoutines.
 */
public class TableRoutines extends JTable implements MouseListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Controller mainController = new Controller();
	private TableRoutinesModel tableModel = null;

	/**
	 * Instantiates a new table routines.
	 *
	 * @param arg0
	 *            the arg0
	 */
	public TableRoutines(TableModel arg0) {
		super(arg0);

		tableModel = (TableRoutinesModel) arg0;

		this.setBounds(25, 25, 600, 300);
		this.addMouseListener(this);

		List<Routine> tmpRoutines = mainController.getAllRoutines();

		for (Routine tmpR : tmpRoutines) {
			tableModel.addRow(tmpR);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = this.rowAtPoint(e.getPoint());
		if (row >= 0 && row <= tableModel.getRoutinesList().size())
			openRoutineCreationWindow(tableModel.getRoutinesList().get(row));

	}

	private void openRoutineCreationWindow(Routine routine) {
		JFrame frameStatusCreation = new JFrame();
		frameStatusCreation.setContentPane(new RoutineCreationPanel(routine, mainController));
		frameStatusCreation.setBounds(AppSettings.getInstance().getMainWindowPosX(), AppSettings.getInstance().getMainWindowPosY(), 640, 480);
		frameStatusCreation.setVisible(true);
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
