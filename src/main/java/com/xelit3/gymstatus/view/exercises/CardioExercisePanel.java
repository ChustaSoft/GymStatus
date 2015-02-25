package com.xelit3.gymstatus.view.exercises;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

import com.xelit3.gymstatus.model.dto.CardioExerciseStatus.CardioExerciseIntensity;
import com.xelit3.gymstatus.view.exercises.ExerciseCreationPanel.ExerciseCreationType;

public class CardioExercisePanel extends JPanel implements ActionListener, ChangeListener{
	
	private static final long serialVersionUID = 1L;
	
	private JTextField tfExerciseName;
	private JSpinner spIntensity;
	private JFormattedTextField tfTime;
	private JButton btnSave;
	private JRadioButton rbCreate, rbModify, rbDelete;
	
	public CardioExercisePanel(ExerciseCreationType ecType) {
		this.createComponents();
		
		switch(ecType){
		
			case CREATE_EXERCISE:
				tfTime.setEnabled(false);
				spIntensity.setEnabled(false);
				break;
				
			case SET_EXERCISE_STATUS:
				break;
		
		}
		
	}
	
	private void createComponents(){
		SpringLayout springLayout = new SpringLayout();
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
		
		JLabel lblExerciseName = new JLabel("Exercise name");
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
				
		JLabel lblIntensity = new JLabel("Intensity");
		springLayout.putConstraint(SpringLayout.NORTH, lblIntensity, 35, SpringLayout.NORTH, lblExerciseName);
		springLayout.putConstraint(SpringLayout.WEST, lblIntensity, 0, SpringLayout.WEST, lblExerciseName);		
		springLayout.putConstraint(SpringLayout.EAST, lblIntensity, 0, SpringLayout.EAST, lblExerciseName);
		add(lblIntensity);
		
		//TODO Averiguar si hay alguna manera de devolver el numero de valores dentro de un enum
		SpinnerListModel spIntensityModel = new SpinnerListModel(new String[] {
				CardioExerciseIntensity.HIGH.toString(), CardioExerciseIntensity.MEDIUM.toString(), CardioExerciseIntensity.SOFT.toString()}
		);
		this.spIntensity = new JSpinner(spIntensityModel);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, spIntensity, 35, SpringLayout.VERTICAL_CENTER, tfExerciseName);
		springLayout.putConstraint(SpringLayout.WEST, spIntensity, 25, SpringLayout.EAST, lblIntensity);
		springLayout.putConstraint(SpringLayout.EAST, spIntensity, 325, SpringLayout.WEST, lblIntensity);
		spIntensity.addChangeListener(this);
		add(spIntensity);
		
		JLabel lblTime = new JLabel("Time");
		springLayout.putConstraint(SpringLayout.NORTH, lblTime, 35, SpringLayout.NORTH, lblIntensity);
		springLayout.putConstraint(SpringLayout.WEST, lblTime, 0, SpringLayout.WEST, lblIntensity);
		springLayout.putConstraint(SpringLayout.EAST, lblTime, 0, SpringLayout.EAST, lblIntensity);
		add(lblTime);
		
		try {
			MaskFormatter mascara = new MaskFormatter("##.##");
			this.tfTime = new JFormattedTextField(mascara);
			tfTime.setHorizontalAlignment(JTextField.RIGHT);
			springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, tfTime, 35, SpringLayout.VERTICAL_CENTER, spIntensity);
			springLayout.putConstraint(SpringLayout.WEST, tfTime, 25, SpringLayout.EAST, lblTime);
			springLayout.putConstraint(SpringLayout.EAST, tfTime, 325, SpringLayout.WEST, lblTime);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		add(this.tfTime);
		
		this.btnSave = new JButton("Save exercise");
		springLayout.putConstraint(SpringLayout.EAST, btnSave, 0, SpringLayout.EAST, tfExerciseName);
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 225, SpringLayout.NORTH, this);
		add(btnSave);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
