package com.xelit3.gymstatus.view.exercises;

import javax.swing.JTabbedPane;

public class ExerciseCreationPanel extends JTabbedPane {
	
	public enum ExerciseCreationType{
		CREATE_EXERCISE, SET_EXERCISE_STATUS
	}

	private static final long serialVersionUID = 1L;
	private FitnessExercisePanel fitnessExercisePanel;
	private CardioExercisePanel cardioExercisePanel;
	
	/**
	 * Create the panel.
	 */
	public ExerciseCreationPanel(ExerciseCreationType ecType) {
		setTabPlacement(JTabbedPane.BOTTOM);
		switch(ecType){
		
			case CREATE_EXERCISE:
				fitnessExercisePanel = new FitnessExercisePanel(ExerciseCreationType.CREATE_EXERCISE);
				cardioExercisePanel = new CardioExercisePanel(ExerciseCreationType.CREATE_EXERCISE);
				break;
				
			case SET_EXERCISE_STATUS:
				fitnessExercisePanel = new FitnessExercisePanel(ExerciseCreationType.SET_EXERCISE_STATUS);
				cardioExercisePanel = new CardioExercisePanel(ExerciseCreationType.SET_EXERCISE_STATUS);
				break;
		}
		
		addTab("Fitness exercise", null, fitnessExercisePanel, null);
		addTab("Cardio exercise", null, cardioExercisePanel, null);	

	}	

}
