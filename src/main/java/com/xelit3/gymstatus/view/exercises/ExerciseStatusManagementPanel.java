package com.xelit3.gymstatus.view.exercises;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.control.utilities.ConversorUtilitiy;
import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExercise;

// TODO: Auto-generated Javadoc
/**
 * The Class ExerciseStatusManagementPanel.
 */
public class ExerciseStatusManagementPanel extends JSplitPane implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The top panel. */
	private JPanel theTopPanel;
	
	/** The exercise panel. */
	private JPanel theExercisePanel;
	
	/** The main controller. */
	private Controller mainController;
	
	/** The lbl select exercise. */
	private JLabel lblSelectExercise;
	
	/** The cb selected exercise. */
	private JComboBox<Exercise> cbSelectedExercise;
	
	/** The bg selected exercise type. */
	private ButtonGroup bgSelectedExerciseType;
	
	/** The rb fitness selected. */
	private JRadioButton rbCardioSelected, rbFitnessSelected;
	
	/**
	 * Instantiates a new exercise status management panel.
	 */
	public ExerciseStatusManagementPanel() {
		mainController = new Controller();
		
		setOrientation(JSplitPane.VERTICAL_SPLIT);		
		
		createComponents();		
	}

	/**
	 * Creates the components.
	 */
	private void createComponents() {
		theTopPanel = new JPanel();
		setLeftComponent(theTopPanel);
		theTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblSelectExercise = new JLabel("Select exercise");
		theTopPanel.add(lblSelectExercise);
		
		bgSelectedExerciseType = new ButtonGroup();
		
		rbCardioSelected = new JRadioButton("CARDIO");
		rbCardioSelected.setActionCommand("SET_CARDIO");
		rbCardioSelected.addActionListener(this);
		
		theTopPanel.add(rbCardioSelected);
		
		rbFitnessSelected = new JRadioButton("FITNESS");
		rbFitnessSelected.setActionCommand("SET_FITNESS");
		rbFitnessSelected.addActionListener(this);
		theTopPanel.add(rbFitnessSelected);
		
		bgSelectedExerciseType.add(rbCardioSelected);
		bgSelectedExerciseType.add(rbFitnessSelected);
		
		this.setRightComponent(null);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			
			case "SET_CARDIO":
				this.setRightComponent(null);
				setCbSelectedExercise(CardioExercise.class);
				break;
				
			case "SET_FITNESS":
				this.setRightComponent(null);
				setCbSelectedExercise(FitnessExercise.class);
				break;
				
			case "EXERCISE_CHANGED":
				if(rbCardioSelected.isSelected())			
					theExercisePanel = new CardioExerciseStatusPanel((CardioExercise)this.cbSelectedExercise.getSelectedItem(), mainController);
				else if(rbFitnessSelected.isSelected())
					theExercisePanel = new FitnessExerciseStatusPanel((FitnessExercise)this.cbSelectedExercise.getSelectedItem(), mainController);
				
				this.setRightComponent(theExercisePanel);				
				break;
			
		}		
	}
	
	/**
	 * Sets the cb selected exercise.
	 *
	 * @param aClass the new cb selected exercise
	 */
	private void setCbSelectedExercise(Class<?> aClass) {
		if(cbSelectedExercise!= null)
			theTopPanel.remove(cbSelectedExercise);
		
		List<Exercise> tmpExercises = mainController.getExercises(aClass);
		cbSelectedExercise = new JComboBox<Exercise>(ConversorUtilitiy.obtainExercises(tmpExercises));
		cbSelectedExercise.setActionCommand("EXERCISE_CHANGED");		
		cbSelectedExercise.addActionListener(this);
		theTopPanel.add(cbSelectedExercise);
		
		theTopPanel.updateUI();
	}
	
	
}
