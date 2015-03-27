package com.xelit3.gymstatus.view.exercises;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ExerciseStatusPanel extends JSplitPane {
	
	private CardioExerciseStatusPanel theCardioPanel;
	private FitnessExerciseStatusPanel theFitnessPanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSelectExercise;

	/**
	 * Create the panel.
	 */
	public ExerciseStatusPanel() {
		setOrientation(JSplitPane.VERTICAL_SPLIT);		
		
		JPanel panel = new JPanel();
		setLeftComponent(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblSelectExercise = new JLabel("Select exercise");
		panel.add(lblSelectExercise);
		
		JComboBox cbSelectedExercise = new JComboBox();
		panel.add(cbSelectedExercise);
		
		JTabbedPane tpExerciseStatus = new JTabbedPane(JTabbedPane.TOP);
		theCardioPanel = new CardioExerciseStatusPanel();
		theFitnessPanel = new FitnessExerciseStatusPanel();
		tpExerciseStatus.addTab("Cardio Exercise Status", theCardioPanel);
		tpExerciseStatus.addTab("Fitness exercise status", theFitnessPanel);
		
		setRightComponent(tpExerciseStatus);		

	}
}
