package com.xelit3.gymstatus.view.exercises;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExercise;

public class ExerciseListPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSplitPane mainSplitPane;
	private Controller controller = new Controller();
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
		
		showExercises(CardioExercise.class);
						
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
		
//		exercisesTable.setFillsViewportHeight(true);
//		exercisesTable.updateUI();
//		exercisesTable.repaint();
//		this.invalidate();
	}
	
	public void showExercises(Class<?> exerciseClass){
		//TODO Es la manera mas correcta de implementar polimorfismo? Casteando en la clase Tabla correspondiente?
		//TODO Switch cases de clases
		//TODO Refresco de las tablas
		List<Exercise> exercisesList = controller.getExercises(exerciseClass);
		switch(exerciseClass.getSimpleName()){
			case "CardioExercise":
				exercisesTable = new TableCardioExercise(exercisesList);
				break;
				
			case "FitnessExercise":
				exercisesTable = new TableFitnessExercise(exercisesList);
				break;
		}
		
		mainSplitPane.setRightComponent(exercisesTable);
		exercisesTable.updateUI();
		this.invalidate();
	}	

}
