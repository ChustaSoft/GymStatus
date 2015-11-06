package com.xelit3.gymstatus.view.exercises;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExerciseStatus;

/**
 * Panel for ExerciseStatus
 */
public class ExerciseStatusListPanel extends JPanel implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The main split pane. */
	private JSplitPane mainSplitPane;
	
	/** The main controller. */
	private Controller mainController = new Controller();
	
	/** The exercises table. */
	private JTable exercisesTable;
	
	/**
	 * Instantiates a new exercise status list panel.
	 */
	public ExerciseStatusListPanel() {
		super(new BorderLayout());
		mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		mainSplitPane.setEnabled(false);
				
		JSplitPane buttonsExerciseTypePanel = new JSplitPane();
		buttonsExerciseTypePanel.setResizeWeight(0.5);
		
		JButton btn_listCardioEx = new JButton("Cardio Exercises");
		btn_listCardioEx.setActionCommand("btnListCardio");
		btn_listCardioEx.addActionListener(this);
		buttonsExerciseTypePanel.setLeftComponent(btn_listCardioEx);
		JButton btn_listFitnessEx = new JButton("Fitness Exercises");
		btn_listFitnessEx.setActionCommand("btnListFitness");
		btn_listFitnessEx.addActionListener(this);
		buttonsExerciseTypePanel.setRightComponent(btn_listFitnessEx);
		buttonsExerciseTypePanel.setEnabled(false);
		
		mainSplitPane.setLeftComponent(buttonsExerciseTypePanel);
		
		showExercises(CardioExerciseStatus.class);
						
		add(mainSplitPane, BorderLayout.CENTER);
       
	}	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()){
			case "btnListCardio":
				showExercises(CardioExerciseStatus.class);
				break;
				
			case "btnListFitness":
				showExercises(FitnessExerciseStatus.class);
				break;	
		
		}		
	}
	
	/**
	 * Show exercises.
	 *
	 * @param exerciseClass the exercise class
	 */
	public void showExercises(Class<?> exerciseClass){
		List<Exercise> exercisesList = mainController.getExercises(exerciseClass);
		//We show a different table depending of the class we recive, expecting for a CardioExerciseStatus or FitnessExerciseStatus
		switch(exerciseClass.getSimpleName()){
			case "CardioExerciseStatus":
				exercisesTable = new TableCardioExerciseStatus(new CardioExerciseStatusTableModel(exercisesList));
				break;
				
			case "FitnessExerciseStatus":				
				exercisesTable = new TableFitnessExerciseStatus(new FitnessExerciseStatusTableModel(exercisesList));
				break;
		}
		JScrollPane scrollPane = new JScrollPane(exercisesTable);
		exercisesTable.setFillsViewportHeight(true);
		
		mainSplitPane.setRightComponent(scrollPane);
	}	

}
