package com.xelit3.gymstatus.view.exercises;

import javax.swing.JTabbedPane;

public class ExerciseManagementPanel extends JTabbedPane {
	
	private static final long serialVersionUID = 1L;
	private FitnessExercisePanel fitnessExercisePanel;
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
