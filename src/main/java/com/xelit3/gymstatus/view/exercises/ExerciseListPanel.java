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
import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExercise;

public class ExerciseListPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSplitPane mainSplitPane;
	private Controller mainController = new Controller();
	private JTable exercisesTable;
	
	public ExerciseListPanel() {
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

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()){
			case "btnListCardio":
				showExercises(CardioExercise.class);
				break;
				
			case "btnListFitness":
				showExercises(FitnessExercise.class);
				break;
		
		}		
	}
	
	public void showExercises(Class<?> exerciseClass){
		//TODO Sería adecuado realizar un Factory en este caso
		//TODO Refresco de la tabla al cambiar de ejercicio -> Recibe FitnessExercise
		List<Exercise> exercisesList = mainController.getExercises(exerciseClass);
		switch(exerciseClass.getSimpleName()){
			case "CardioExerciseStatus":
				exercisesTable = new TableCardioExercise(new CardioExerciseTableModel());
				break;
				
			case "FitnessExerciseStatus":
				exercisesTable = new TableFitnessExercise(new FitnessExerciseTableModel());
				break;
		}
		JScrollPane scrollPane = new JScrollPane(exercisesTable);
		
		mainSplitPane.setRightComponent(scrollPane);
		scrollPane.revalidate();
		scrollPane.updateUI();		
		mainSplitPane.revalidate();
		mainSplitPane.repaint();
		mainSplitPane.updateUI();
	}	

}
