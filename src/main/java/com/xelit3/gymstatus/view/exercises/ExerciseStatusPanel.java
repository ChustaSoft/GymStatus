package com.xelit3.gymstatus.view.exercises;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;

public class ExerciseStatusPanel extends JSplitPane implements ActionListener{
	
	private JPanel theExercisePanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSelectExercise;
	
	private final static String CARDIO_MANAGEMENT = "mngCardioExercise", FITNESS_MANAGEMENT = "mngFitnessExercise";

	/**
	 * Create the panel.
	 */
	public ExerciseStatusPanel() {
		setOrientation(JSplitPane.VERTICAL_SPLIT);		
		
		JPanel tmpTopPanel = new JPanel();
		setLeftComponent(tmpTopPanel);
		tmpTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblSelectExercise = new JLabel("Select exercise");
		tmpTopPanel.add(lblSelectExercise);
		
		ButtonGroup bgSelectedExerciseType = new ButtonGroup();
		
		JRadioButton rbCardioSelected = new JRadioButton("CARDIO");
		rbCardioSelected.setSelected(true);
		rbCardioSelected.setActionCommand(CARDIO_MANAGEMENT);
		rbCardioSelected.addActionListener(this);
		tmpTopPanel.add(rbCardioSelected);
		
		JRadioButton rbFitnessSelected = new JRadioButton("FITNESS");
		rbFitnessSelected.setActionCommand(FITNESS_MANAGEMENT);
		rbFitnessSelected.addActionListener(this);
		tmpTopPanel.add(rbFitnessSelected);
		
		bgSelectedExerciseType.add(rbCardioSelected);
		bgSelectedExerciseType.add(rbFitnessSelected);
		
		JComboBox cbSelectedExercise = new JComboBox();
		tmpTopPanel.add(cbSelectedExercise);
		
		this.setRightComponent(null);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		
		case CARDIO_MANAGEMENT:
			theExercisePanel = new CardioExerciseStatusPanel();
			break;
			
		case FITNESS_MANAGEMENT:
			theExercisePanel = new FitnessExerciseStatusPanel();
			break;
		}
		
		setRightComponent(theExercisePanel);		
	}
}
