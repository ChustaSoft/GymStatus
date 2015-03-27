package com.xelit3.gymstatus.view.exercises;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CardioExercisePanel extends JPanel implements ActionListener, ChangeListener{
	
	private static final long serialVersionUID = 1L;
	
	private JTextField tfExerciseName;
	private JButton btnSave;
	private JRadioButton rbCreate, rbModify, rbDelete;
	
	public CardioExercisePanel() {
		this.createComponents();		
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
		
		this.btnSave = new JButton("Save exercise");
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 26, SpringLayout.SOUTH, tfExerciseName);
		springLayout.putConstraint(SpringLayout.EAST, btnSave, 0, SpringLayout.EAST, tfExerciseName);
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
