package com.xelit3.gymstatus.view.exercises;

import javax.swing.JTabbedPane;

/**
 * Panel to manage Exercise's subclasses
 */
public class ExerciseManagementPanel extends JTabbedPane {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The fitness exercise panel. */
	private FitnessExercisePanel fitnessExercisePanel;
	
	/** The cardio exercise panel. */
	private CardioExercisePanel cardioExercisePanel;
	
	/**
	 * Create the panel.
	 */
	public ExerciseManagementPanel() {
		setTabPlacement(JTabbedPane.BOTTOM);
		
		fitnessExercisePanel = new FitnessExercisePanel();
		cardioExercisePanel = new CardioExercisePanel();
		
		addTab("Fitness exercise", null, fitnessExercisePanel, null);
		addTab("Cardio exercise", null, cardioExercisePanel, null);	

	}	

}
