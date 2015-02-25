package com.xelit3.gymstatus.view.routines;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.Color;

public class RoutineCreationPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfRoutineName;

	/**
	 * Create the panel.
	 */
	public RoutineCreationPanel() {
		setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(152, 87, 157, 20);
		add(comboBox);
		
		JLabel lblExerciseSelector = new JLabel("Select a exercise to add");
		lblExerciseSelector.setBounds(29, 87, 122, 20);
		add(lblExerciseSelector);
		
		JLabel lblRoutineName = new JLabel("Name for routine");
		lblRoutineName.setBounds(29, 46, 93, 17);
		add(lblRoutineName);
		
		tfRoutineName = new JTextField();
		tfRoutineName.setBounds(152, 44, 157, 20);
		add(tfRoutineName);
		tfRoutineName.setColumns(10);
		
		JLabel lblInitDate = new JLabel("Init date");
		lblInitDate.setBounds(330, 46, 48, 17);
		add(lblInitDate);
		
		JLabel lblEndDate = new JLabel("End date");
		lblEndDate.setBounds(330, 90, 48, 17);
		add(lblEndDate);
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl dpInitDate = new JDatePickerImpl(datePanel);
		dpInitDate.setBounds(388, 43, 110, 20);
		this.add(dpInitDate);
		JDatePickerImpl dpEndDate = new JDatePickerImpl(datePanel);
		dpEndDate.setBounds(389, 87, 110, 20);
		this.add(dpEndDate);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(67, 78, 84));
		separator.setBounds(29, 133, 469, 5);
		add(separator);
		

	}
}
