package com.xelit3.gymstatus.view.routines;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class RoutineCreationPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfRoutineName;
	private JTable jtableAddedExercises;
	
	private static String[] EXERCISES_TYPES = {"CARDIO", "FITNESS"};

	/**
	 * Create the panel.
	 */
	public RoutineCreationPanel() {
		setLayout(null);
		
		JComboBox cbExerciseType = new JComboBox(EXERCISES_TYPES);
		cbExerciseType.setBounds(30, 119, 100, 20);
		cbExerciseType.setActionCommand("changeExerciseType");
		cbExerciseType.addActionListener(this);
		add(cbExerciseType);
		
		JComboBox cbSelectedExercise = new JComboBox();
		cbSelectedExercise.setBounds(169, 119, 180, 20);
		add(cbSelectedExercise);
		
		JLabel lblExerciseSelector = new JLabel("Select a exercise to add");
		lblExerciseSelector.setBounds(30, 88, 157, 20);
		add(lblExerciseSelector);
		
		JLabel lblRoutineName = new JLabel("Name for routine");
		lblRoutineName.setBounds(30, 25, 100, 17);
		add(lblRoutineName);
		
		tfRoutineName = new JTextField();
		tfRoutineName.setBounds(30, 53, 157, 20);
		add(tfRoutineName);
		tfRoutineName.setColumns(10);
		
		JLabel lblInitDate = new JLabel("Init date");
		lblInitDate.setBounds(224, 25, 48, 17);
		add(lblInitDate);
		
		JLabel lblEndDate = new JLabel("End date");
		lblEndDate.setBounds(383, 25, 129, 17);
		add(lblEndDate);
		
		UtilDateModel modelInitDate = new UtilDateModel(), modelEndDate = new UtilDateModel();
		JDatePanelImpl datePanelInitDate = new JDatePanelImpl(modelInitDate);
		JDatePickerImpl dpInitDate = new JDatePickerImpl(datePanelInitDate);
		dpInitDate.setBounds(226, 53, 123, 20);
		this.add(dpInitDate);
		JDatePanelImpl datePanelEndDate = new JDatePanelImpl(modelEndDate);
		JDatePickerImpl dpEndDate = new JDatePickerImpl(datePanelEndDate);
		dpEndDate.setBounds(383, 53, 129, 20);
		this.add(dpEndDate);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(67, 78, 84));
		separator.setBounds(29, 169, 469, 5);
		add(separator);
		
		jtableAddedExercises = new JTable();
		
		jtableAddedExercises.setModel(new DefaultTableModel(
			new Object[][]{},
			new String[] {
				"Exercise type", "Exercise name", "Trained muscle", "Intensity", "Time"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane(jtableAddedExercises);
		scrollPane.setSize(410, 167);
		scrollPane.setLocation(58, 198);
		add(scrollPane);
		
		JButton btnAddExercise = new JButton("Add to routine");
		btnAddExercise.setActionCommand("addExerciseToRoutine");
		btnAddExercise.setBounds(383, 118, 129, 20);
		btnAddExercise.addActionListener(this);
		add(btnAddExercise);	
		
		JButton btnCreateRoutine = new JButton("Create routine");
		btnCreateRoutine.setActionCommand("createRoutine");
		btnCreateRoutine.setBounds(395, 376, 117, 23);
		btnCreateRoutine.addActionListener(this);
		add(btnCreateRoutine);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()){
		
		case "changeExerciseType":
			//TODO Cargar en el segundo combobox los ejercicios dependiendo de lo clickado.
			System.out.println("Change type - Not implemented");
			break;
			
		case "addExerciseToRoutine":
			System.out.println("Adding exercises - Not implemented yet");
			break;
			
		case "createRoutine":
			System.out.println("Create routine - Not implemented yet");
			break;
			
		}
		
	}
}
