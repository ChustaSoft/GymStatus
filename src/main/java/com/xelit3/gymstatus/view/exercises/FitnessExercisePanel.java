package com.xelit3.gymstatus.view.exercises;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

import com.xelit3.gymstatus.view.exercises.ExerciseCreationPanel.ExerciseCreationType;

public class FitnessExercisePanel extends JPanel implements ChangeListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblExerciseName, lblTrainedMuscle, lbRepetitions, lblSeriesRepetitions;
	private SpringLayout springLayout;
	private JTextField tfExerciseName;
	private JSpinner spTrainedMuscle, spNumberRepetitions;
	private List<JFormattedTextField> tfListRepetitions = new ArrayList<JFormattedTextField>();
	private JRadioButton rbCreate, rbModify, rbDelete;
	private JButton btnSave;

	public FitnessExercisePanel(ExerciseCreationType exType) {
		this.createComponents();
		
		switch(exType){
		
			case CREATE_EXERCISE:
				spTrainedMuscle.setEnabled(false);
				spNumberRepetitions.setEnabled(false);
				break;
				
			case SET_EXERCISE_STATUS:
				break;
			
		}
	}
	
	private void createComponents(){
		//TODO Problemas con los JFormattedTextField, también con eliminación dinámica
		springLayout = new SpringLayout();
		setLayout(springLayout);
				
		ButtonGroup bgCrud = new ButtonGroup();
			
		rbCreate = new JRadioButton("Create");
		rbCreate.setActionCommand("setCreateForm");
		rbCreate.addActionListener(this);
		rbCreate.setSelected(true);
		springLayout.putConstraint(SpringLayout.NORTH, rbCreate, 25, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, rbCreate, 75, SpringLayout.WEST, this);
		add(rbCreate);
		
		rbModify = new JRadioButton("Modify");
		rbModify.setActionCommand("setModifyForm");
		rbModify.addActionListener(this);
		springLayout.putConstraint(SpringLayout.NORTH, rbModify, 0, SpringLayout.NORTH, rbCreate);
		springLayout.putConstraint(SpringLayout.WEST, rbModify, 10, SpringLayout.EAST, rbCreate);
		add(rbModify);
		
		rbDelete = new JRadioButton("Delete");
		rbDelete.setActionCommand("setDeleteForm");
		rbDelete.addActionListener(this);
		springLayout.putConstraint(SpringLayout.NORTH, rbDelete, 0, SpringLayout.NORTH, rbModify);
		springLayout.putConstraint(SpringLayout.WEST, rbDelete, 10, SpringLayout.EAST, rbModify);
		add(rbDelete);
		
		bgCrud.add(rbCreate);
		bgCrud.add(rbModify);
		bgCrud.add(rbDelete);
		
		this.lblExerciseName = new JLabel("Exercise name");
		springLayout.putConstraint(SpringLayout.NORTH, lblExerciseName, 75, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblExerciseName, 75, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblExerciseName, 200, SpringLayout.WEST, this);
		add(lblExerciseName);
		
		this.tfExerciseName = new JTextField();
		tfExerciseName.setHorizontalAlignment(JTextField.RIGHT);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, tfExerciseName, 0, SpringLayout.VERTICAL_CENTER, lblExerciseName);
		springLayout.putConstraint(SpringLayout.WEST, tfExerciseName, 25, SpringLayout.EAST, lblExerciseName);
		springLayout.putConstraint(SpringLayout.EAST, tfExerciseName, 325, SpringLayout.WEST, lblExerciseName);
		tfExerciseName.setColumns(10);
		add(tfExerciseName);
		
		this.lblTrainedMuscle = new JLabel("Trained muscle");
		springLayout.putConstraint(SpringLayout.NORTH, lblTrainedMuscle, 35, SpringLayout.NORTH, lblExerciseName);
		springLayout.putConstraint(SpringLayout.WEST, lblTrainedMuscle, 0, SpringLayout.WEST, lblExerciseName);
		springLayout.putConstraint(SpringLayout.EAST, lblTrainedMuscle, 0, SpringLayout.EAST, lblExerciseName);
		add(lblTrainedMuscle);
		
		//TODO Simulated first
		SpinnerModel spTrainedMuscleModel = new SpinnerListModel(new String[] {
			"Musculo1", "Musculo2"}
		);
				
		this.spTrainedMuscle = new JSpinner();
		spTrainedMuscle.setModel(spTrainedMuscleModel);
		springLayout.putConstraint(SpringLayout.WEST, spTrainedMuscle, 0, SpringLayout.WEST, tfExerciseName);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, spTrainedMuscle, 0, SpringLayout.VERTICAL_CENTER, lblTrainedMuscle);
		springLayout.putConstraint(SpringLayout.EAST, spTrainedMuscle, 0, SpringLayout.EAST, tfExerciseName);
		add(spTrainedMuscle);
		
		this.lbRepetitions = new JLabel("Repetitions");
		springLayout.putConstraint(SpringLayout.NORTH, lbRepetitions, 35, SpringLayout.NORTH, lblTrainedMuscle);
		springLayout.putConstraint(SpringLayout.WEST, lbRepetitions, 0, SpringLayout.WEST, lblTrainedMuscle);
		springLayout.putConstraint(SpringLayout.EAST, lbRepetitions, 0, SpringLayout.EAST, lblTrainedMuscle);
		add(lbRepetitions);
		
		this.btnSave = new JButton("Save exercise");
		springLayout.putConstraint(SpringLayout.EAST, btnSave, 0, SpringLayout.EAST, tfExerciseName);
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 225, SpringLayout.NORTH, this);
		add(btnSave);
		
		//Definimos un modelo que obligará a elegir entre uno y 5 ejercicios 
		SpinnerModel spNumberRepetitionsModel = new SpinnerNumberModel(1, 1, 5, 1);		
		this.spNumberRepetitions = new JSpinner(spNumberRepetitionsModel);
		springLayout.putConstraint(SpringLayout.WEST, spNumberRepetitions, 0, SpringLayout.WEST, tfExerciseName);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, spNumberRepetitions, 0, SpringLayout.VERTICAL_CENTER, lbRepetitions);
		springLayout.putConstraint(SpringLayout.EAST, spNumberRepetitions, 0, SpringLayout.EAST, tfExerciseName);
		spNumberRepetitions.addChangeListener(this);
		add(spNumberRepetitions);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		SpinnerNumberModel sm = (SpinnerNumberModel) spNumberRepetitions.getModel();
		this.lblSeriesRepetitions = new JLabel("Series");
		springLayout.putConstraint(SpringLayout.NORTH, lbRepetitions, 35, SpringLayout.NORTH, lblTrainedMuscle);
		springLayout.putConstraint(SpringLayout.WEST, lbRepetitions, 0, SpringLayout.WEST, lblTrainedMuscle);
		springLayout.putConstraint(SpringLayout.EAST, lbRepetitions, 0, SpringLayout.EAST, lblTrainedMuscle);
		
//		//Eliminamos los TextFields primero de la interfaz, para volver a colcarlos dependiendo de la actualización
//		for(JFormattedTextField tf : tfListRepetitions){
//			this.remove(tf);
//		}
		tfListRepetitions = new ArrayList<JFormattedTextField>();
		int xPosWest = 25, yPosEast = 50;
		
		for(int i=0; i<sm.getNumber().intValue(); i++){
			JFormattedTextField tmpField = null;
			try {
				MaskFormatter mascara = new MaskFormatter("##.##");
				tmpField = new JFormattedTextField(mascara);
//				tmpField.setSize(new Dimension(tmpField.getHeight(), tmpField.getWidth() + 10));
				tmpField.invalidate();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}			 
			
			tfListRepetitions.add(tmpField);
			springLayout.putConstraint(SpringLayout.NORTH, tmpField, 0, SpringLayout.NORTH, lbRepetitions);
			springLayout.putConstraint(SpringLayout.WEST, tmpField, xPosWest, SpringLayout.EAST, lblSeriesRepetitions);
			springLayout.putConstraint(SpringLayout.EAST, tmpField, yPosEast, SpringLayout.WEST, lblSeriesRepetitions);
			add(tmpField);
			
			xPosWest += 70;
			yPosEast += 70;
		}
		
		//TODO Problemas con el resfresco del panel padre, funcionaba siendo unico con .updateUI();
		//Diferencias entre los metodos invalidate(), revalidate(), updateUI(), repaint() y pack() 
		this.updateUI();
		this.invalidate();
		ExerciseCreationPanel parent = (ExerciseCreationPanel) this.getParent();
		parent.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Realizar una enum para determinar si vamos a arreglar un ejercicio o el estado de un ejercicio, swtich case pertinenete primero antes del CRUD
		switch(e.getActionCommand()){
			case "setCreateForm":
				System.out.println("create");
				break;
				
			case "setModifyForm":
				System.out.println("modify");
				break;
				
			case "setDeleteForm":
				System.out.println("delete");
				break;
	}
		
	}
}
