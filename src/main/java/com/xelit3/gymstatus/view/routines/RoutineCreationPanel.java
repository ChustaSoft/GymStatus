package com.xelit3.gymstatus.view.routines;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.control.settings.AppSettings;
import com.xelit3.gymstatus.control.utilities.ConversorUtilitiy;
import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExercise;
import com.xelit3.gymstatus.model.dto.FitnessExerciseStatus;
import com.xelit3.gymstatus.view.exercises.CardioExerciseStatusPanel;
import com.xelit3.gymstatus.view.exercises.FitnessExerciseStatusPanel;
import com.xelit3.gymstatus.view.exercises.TableExercisesGeneral;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class RoutineCreationPanel extends JPanel implements ActionListener, Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller mainController;
	
	private JTextField tfRoutineName;
	private TableExercisesGeneral jtableAddedExercises;
	private JComboBox<String> cbExerciseType;
	private JComboBox<Exercise> cbSelectedExercise;	
	private JButton btnAddExercise, btnCreateRoutine;
	
	private static String[] EXERCISES_TYPES = {"CARDIO", "FITNESS"};

	/**
	 * Create the panel.
	 */
	public RoutineCreationPanel() {
		setLayout(null);
		
		mainController = new Controller(this);
		
		createComponents();		
	}

	private void createComponents() {
		cbExerciseType = new JComboBox<String>(EXERCISES_TYPES);
		cbExerciseType.setBounds(30, 119, 123, 17);
		cbExerciseType.setActionCommand("changeExerciseType");
		cbExerciseType.addActionListener(this);
		add(cbExerciseType);
		
		cbSelectedExercise = new JComboBox<Exercise>();
		cbSelectedExercise.setEditable(false);
		cbSelectedExercise.setBounds(248, 119, 145, 17);
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
		lblInitDate.setBounds(270, 25, 48, 17);
		add(lblInitDate);
		
		JLabel lblEndDate = new JLabel("End date");
		lblEndDate.setBounds(470, 25, 129, 17);
		add(lblEndDate);
		
		UtilDateModel modelInitDate = new UtilDateModel(), modelEndDate = new UtilDateModel();
		JDatePanelImpl datePanelInitDate = new JDatePanelImpl(modelInitDate);
		JDatePickerImpl dpInitDate = new JDatePickerImpl(datePanelInitDate);
		dpInitDate.setBounds(270, 53, 123, 20);
		this.add(dpInitDate);
		JDatePanelImpl datePanelEndDate = new JDatePanelImpl(modelEndDate);
		JDatePickerImpl dpEndDate = new JDatePickerImpl(datePanelEndDate);
		dpEndDate.setBounds(470, 53, 129, 20);
		this.add(dpEndDate);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(67, 78, 84));
		separator.setBounds(48, 169, 530, 2);
		add(separator);
		jtableAddedExercises = new TableExercisesGeneral();
		jtableAddedExercises.setSize(569, 167);
		jtableAddedExercises.setLocation(30, 198);
		add(jtableAddedExercises);
		
		btnAddExercise = new JButton("Add to routine");
		btnAddExercise.setActionCommand("addExerciseToRoutine");
		btnAddExercise.setBounds(470, 119, 129, 20);
		btnAddExercise.addActionListener(this);
		add(btnAddExercise);	
		
		btnCreateRoutine = new JButton("Create routine");
		btnCreateRoutine.setActionCommand("createRoutine");
		btnCreateRoutine.setBounds(395, 376, 117, 23);
		btnCreateRoutine.addActionListener(this);
		add(btnCreateRoutine);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()){
		
		case "changeExerciseType":
			//Así cambiamos dinámicamente los ejercicios del JComboBox
			Class<?> aClass = getClassOnDemand((String) cbExerciseType.getSelectedItem());
			List<Exercise> tmpExercises = new ArrayList<Exercise>();
			tmpExercises = mainController.getExercises(aClass);
			DefaultComboBoxModel<Exercise> tmpModel = new DefaultComboBoxModel<Exercise>(ConversorUtilitiy.obtainExercises(tmpExercises));
			this.cbSelectedExercise.setModel(tmpModel);
			break;
			
		case "addExerciseToRoutine":
			if(this.cbSelectedExercise.getSelectedItem() != null){
				openExerciseStatusCreationWindow(this.cbSelectedExercise.getSelectedItem());
			}
			
			break;
			
		case "createRoutine":
			System.out.println("Create routine - Not implemented yet");
			break;
			
		}
		
	}

	private void openExerciseStatusCreationWindow(Object anExercise) {
		Class<?> aClass = anExercise.getClass();
		JFrame tmpFrame = new JFrame();
				
		switch(aClass.getSimpleName()){
			case "FitnessExercise":
				tmpFrame.setContentPane(new FitnessExerciseStatusPanel((FitnessExercise) anExercise));				
				break;
				
			case "CardioExercise":
				tmpFrame.setContentPane(new CardioExerciseStatusPanel((CardioExercise) anExercise));		
				break;
		}
		tmpFrame.setBounds(AppSettings.getInstance().getMainWindowPosX(), AppSettings.getInstance().getMainWindowPosY(), 640, 480);
//		tmpFrame.setBounds(this.getParent().getParent().getX(), this.getParent().getParent().getY(), 640, 480);
		tmpFrame.setVisible(true);
	}

	private Class<?> getClassOnDemand(String selectedItem) {
		if(selectedItem.equals(EXERCISES_TYPES[0]))
			return CardioExercise.class;
		else if(selectedItem.equals(EXERCISES_TYPES[1]))
			return FitnessExercise.class;
		else 
			return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof FitnessExerciseStatus){
			//TODO
		}
		else if(arg instanceof CardioExerciseStatus){
			//TODO
		}
	}
	
}
