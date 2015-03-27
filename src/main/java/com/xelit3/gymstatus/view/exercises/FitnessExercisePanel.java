package com.xelit3.gymstatus.view.exercises;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.model.dto.FitnessExercise;
import com.xelit3.gymstatus.model.dto.Muscle;

public class FitnessExercisePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblExerciseName, lblTrainedMuscle;
	private SpringLayout springLayout;
	private JTextField tfExerciseName;
	private JComboBox cbTrainedMuscle;
	private JRadioButton rbCreate, rbModify, rbDelete;
	private JButton btnSave;
	
	private Controller mainController = new Controller();

	private List<Muscle> musclesList;

	public FitnessExercisePanel() {
		this.createComponents();		
	}
	
	private void createComponents(){
		//TODO Problemas con los JFormattedTextField, tambi�n con eliminaci�n din�mica
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
		
		setUpMuscles();
		
		this.btnSave = new JButton("Save exercise");
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 23, SpringLayout.SOUTH, cbTrainedMuscle);
		springLayout.putConstraint(SpringLayout.EAST, btnSave, 0, SpringLayout.EAST, tfExerciseName);
		btnSave.setActionCommand("createExercise");
		btnSave.addActionListener(this);
		add(btnSave);	
		
	}

	private void setUpMuscles() {
		musclesList = mainController.getMuscles();
		
		String [] tmpMusclesStr = new String[musclesList.size()];
		
		for(int i= 0; i< musclesList.size(); i++){
			tmpMusclesStr[i] = musclesList.get(i).getMusclename();
		}
		
		this.cbTrainedMuscle = new JComboBox(tmpMusclesStr);
		springLayout.putConstraint(SpringLayout.WEST, cbTrainedMuscle, 0, SpringLayout.WEST, tfExerciseName);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cbTrainedMuscle, 0, SpringLayout.VERTICAL_CENTER, lblTrainedMuscle);
		springLayout.putConstraint(SpringLayout.EAST, cbTrainedMuscle, 0, SpringLayout.EAST, tfExerciseName);
		add(cbTrainedMuscle);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Realizar una enum para determinar si vamos a arreglar un ejercicio o el estado de un ejercicio, swtich case pertinenete primero antes del CRUD
		switch(e.getActionCommand()){
			case "setCreateForm":
				btnSave.setActionCommand("createExercise");
				break;
				
			case "setModifyForm":
				btnSave.setActionCommand("modifyExercise");
				break;
				
			case "setDeleteForm":
				btnSave.setActionCommand("removeExercise");
				break;
				
			case "createExercise":
				saveExercise();
				break;
				
			case "modifyExercise":
				System.out.println("updating");
				break;
				
			case "removeExercise":
				System.out.println("removing");
				break;
		}
		
	}
	
	private void saveExercise(){
		FitnessExercise tmpFitnessExercise = new FitnessExercise();
		tmpFitnessExercise.setExerciseName(tfExerciseName.getText());
		tmpFitnessExercise.setTrainedMuscle(musclesList.get(cbTrainedMuscle.getSelectedIndex()));
		
		this.mainController.saveExercise(tmpFitnessExercise);
	}
	
	private void modifyExercise(){
		
	}
	
	private void removeExercise(){
		
	}
}
