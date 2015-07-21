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

public class ExerciseStatusPanel extends JSplitPane implements ActionListener{
	
	private JPanel theExercisePanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSelectExercise;
	private JComboBox<Exercise> cbSelectedExercise;
	private Controller theController;
	private JPanel theTopPanel;
	
	private final static String CARDIO_MANAGEMENT = "mngCardioExercise", FITNESS_MANAGEMENT = "mngFitnessExercise";

	

	/**
	 * Create the panel.
	 */
	public ExerciseStatusPanel() {
		theController = new Controller();
		
		setOrientation(JSplitPane.VERTICAL_SPLIT);		
		
		theTopPanel = new JPanel();
		setLeftComponent(theTopPanel);
		theTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblSelectExercise = new JLabel("Select exercise");
		theTopPanel.add(lblSelectExercise);
		
		ButtonGroup bgSelectedExerciseType = new ButtonGroup();
		
		JRadioButton rbCardioSelected = new JRadioButton("CARDIO");
		rbCardioSelected.setSelected(true);
		rbCardioSelected.setActionCommand(CARDIO_MANAGEMENT);
		rbCardioSelected.addActionListener(this);
		theTopPanel.add(rbCardioSelected);
		
		JRadioButton rbFitnessSelected = new JRadioButton("FITNESS");
		rbFitnessSelected.setActionCommand(FITNESS_MANAGEMENT);
		rbFitnessSelected.addActionListener(this);
		theTopPanel.add(rbFitnessSelected);
		
		bgSelectedExerciseType.add(rbCardioSelected);
		bgSelectedExerciseType.add(rbFitnessSelected);
		
		this.setRightComponent(null);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()){
		
		case CARDIO_MANAGEMENT:
			setCbSelectedExercise(CardioExercise.class);
			theExercisePanel = new CardioExerciseStatusPanel();
			break;
			
		case FITNESS_MANAGEMENT:
			setCbSelectedExercise(FitnessExercise.class);
			theExercisePanel = new FitnessExerciseStatusPanel();
			break;
		}
		
		setRightComponent(theExercisePanel);		
	}

	/**
	 * @param 
	 */
	private void setCbSelectedExercise(Class<?> aClass) {
		if(cbSelectedExercise!= null)
			theTopPanel.remove(cbSelectedExercise);
		
		List<Exercise> tmpExercises = theController.getExercises(aClass);
		cbSelectedExercise = new JComboBox<Exercise>(ConversorUtilitiy.obtainExercises(tmpExercises));
		theTopPanel.add(cbSelectedExercise);
		
		theTopPanel.invalidate();
	}
	
	
}
