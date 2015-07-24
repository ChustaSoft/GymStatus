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

public class ExerciseStatusManagementPanel extends JSplitPane implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel theTopPanel;
	private JPanel theExercisePanel;
	private Controller theController;
	private JLabel lblSelectExercise;
	private JComboBox<Exercise> cbSelectedExercise;
	private ButtonGroup bgSelectedExerciseType;
	private JRadioButton rbCardioSelected, rbFitnessSelected;
	
	public ExerciseStatusManagementPanel() {
		theController = new Controller();
		
		setOrientation(JSplitPane.VERTICAL_SPLIT);		
		
		createComponents();		
	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "SET_CARDIO":
				setCbSelectedExercise(CardioExercise.class);
				break;
				
			case "SET_FITNESS":
				setCbSelectedExercise(FitnessExercise.class);
				break;
				
			case "EXERCISE_CHANGED":
				if(rbCardioSelected.isSelected())			
					theExercisePanel = new CardioExerciseStatusPanel((CardioExercise)this.cbSelectedExercise.getSelectedItem());
				else if(rbFitnessSelected.isSelected())
					theExercisePanel = new FitnessExerciseStatusPanel((FitnessExercise)this.cbSelectedExercise.getSelectedItem());
				
				this.setRightComponent(theExercisePanel);				
				break;
			
		}		
	}
	
	private void setCbSelectedExercise(Class<?> aClass) {
		if(cbSelectedExercise!= null)
			theTopPanel.remove(cbSelectedExercise);
		
		List<Exercise> tmpExercises = theController.getExercises(aClass);
		cbSelectedExercise = new JComboBox<Exercise>(ConversorUtilitiy.obtainExercises(tmpExercises));
		cbSelectedExercise.setActionCommand("EXERCISE_CHANGED");		
		cbSelectedExercise.addActionListener(this);
		theTopPanel.add(cbSelectedExercise);
		
		theTopPanel.updateUI();
	}
	
	
}
