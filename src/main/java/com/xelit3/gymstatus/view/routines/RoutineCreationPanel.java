package com.xelit3.gymstatus.view.routines;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import com.xelit3.gymstatus.model.dto.Routine;
import com.xelit3.gymstatus.view.exercises.CardioExerciseStatusPanel;
import com.xelit3.gymstatus.view.exercises.FitnessExerciseStatusPanel;
import com.xelit3.gymstatus.view.exercises.TableExercisesGeneral;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

// TODO: Auto-generated Javadoc
/**
 * The Class RoutineCreationPanel.
 */
public class RoutineCreationPanel extends JPanel implements ActionListener, Observer{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The main controller. */
	private Controller mainController;
	
	/** The routine. */
	private Routine theRoutine = new Routine();
	
	/** The tf routine name. */
	private JTextField tfRoutineName;
	
	/** The jtable added exercises. */
	private TableExercisesGeneral jtableAddedExercises;
	
	/** The cb exercise type. */
	private JComboBox<String> cbExerciseType;
	
	/** The cb selected exercise. */
	private JComboBox<Exercise> cbSelectedExercise;	
	
	/** The btn create routine. */
	private JButton btnAddExercise, btnCreateRoutine, btnRemoveRoutine;
	
	/** The dp end date. */
	private JDatePickerImpl dpInitDate, dpEndDate;
	
	/** The exercises types. */
	private static String[] EXERCISES_TYPES = {"CARDIO", "FITNESS"};

	/** The frame status creation. */
	private JFrame frameStatusCreation;

	/**
	 * Create the panel.
	 */
	public RoutineCreationPanel() {		
		mainController = new Controller(this);
		
		createComponents(false);
	}

	public RoutineCreationPanel(Routine aRoutine, Controller aController) {		
		mainController = aController;
		
		theRoutine = aRoutine;
		
		createComponents(true);
		loadRoutineData(aRoutine);
	}

	/**
	 * Creates the components.
	 */
	private void createComponents(boolean isUpdating) {
		setLayout(null);
		
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
		dpInitDate = new JDatePickerImpl(datePanelInitDate);
		dpInitDate.setBounds(270, 53, 123, 20);
		this.add(dpInitDate);
		JDatePanelImpl datePanelEndDate = new JDatePanelImpl(modelEndDate);
		dpEndDate = new JDatePickerImpl(datePanelEndDate);
		dpEndDate.setBounds(470, 53, 129, 20);
		this.add(dpEndDate);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(67, 78, 84));
		separator.setBounds(48, 169, 530, 2);
		add(separator);
		jtableAddedExercises = new TableExercisesGeneral(this);
		jtableAddedExercises.setSize(569, 167);
		jtableAddedExercises.setLocation(30, 198);
		add(jtableAddedExercises);
		
		btnAddExercise = new JButton("Add to routine");
		btnAddExercise.setActionCommand("addExerciseToRoutine");
		btnAddExercise.setBounds(470, 119, 129, 20);
		btnAddExercise.addActionListener(this);
		add(btnAddExercise);
		
		if(isUpdating){
			btnCreateRoutine = new JButton("Modify routine");
			btnCreateRoutine.setActionCommand("updateRoutine");
		}
		else{
			btnCreateRoutine = new JButton("Create routine");
			btnCreateRoutine.setActionCommand("createRoutine");
		}
		btnCreateRoutine.setBounds(410, 380, 135, 23);
		btnCreateRoutine.addActionListener(this);
		add(btnCreateRoutine);
		
		if(isUpdating){
			btnRemoveRoutine = new JButton("Remove routine");
			btnRemoveRoutine.setBounds(270, 380, 135, 23);
			btnRemoveRoutine.setActionCommand("removeRoutine");
			btnRemoveRoutine.addActionListener(this);
			add(btnRemoveRoutine);
		}
	}	
	
	private void loadRoutineData(Routine aRoutine) {
		this.tfRoutineName.setText(aRoutine.getRoutineName());
		this.dpInitDate.getModel().setDate(aRoutine.getInitDate().getYear(), aRoutine.getInitDate().getMonth(), aRoutine.getInitDate().getDate());
		this.dpInitDate.getModel().setSelected(true);
		this.dpEndDate.getModel().setDate(aRoutine.getFinishDate().getYear(), aRoutine.getFinishDate().getMonth(), aRoutine.getFinishDate().getDate());
		this.dpEndDate.getModel().setSelected(true);
		for(Exercise e : aRoutine.getExercises()){
			jtableAddedExercises.addNewRow(e);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
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
				setRoutineData();
				
				if(theRoutine.isValid())
					mainController.createRoutine(theRoutine);
				else
					JOptionPane.showMessageDialog(this, "Check out data first, some errors found");
				break;
				
			case "updateRoutine":
				setRoutineData();
				
				if(theRoutine.isValid())
					mainController.modifyRoutine(theRoutine);
				else
					JOptionPane.showMessageDialog(this, "Check out data first, some errors found");
				break;
				
			case "removeRoutine":
				int tmpConfirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?");
				if(tmpConfirm == 0)
					mainController.removeRoutine(theRoutine);
				break;
			
		}
		
	}

	private void setRoutineData() {
		Date tmpInitDate = (Date) dpInitDate.getModel().getValue();
		Date tmpEndDate = (Date) dpEndDate.getModel().getValue();
		
		//Creamos la rutina
		theRoutine.setRoutineName(this.tfRoutineName.getText());
		theRoutine.setInitDate(tmpInitDate);
		theRoutine.setFinishDate(tmpEndDate);
		theRoutine.setExercises(jtableAddedExercises.getExercisesList());
	}

	/**
	 * Open exercise status creation window.
	 *
	 * @param anExercise the an exercise
	 */
	public void openExerciseStatusCreationWindow(Object anExercise) {
		Class<?> aClass = anExercise.getClass();
		frameStatusCreation = new JFrame();
			
		//Hemos de pasarle el controller del panel padre a los hijos para que estos notifiquen adecuadamente a este sobre la insercion del ejercicio
		switch(aClass.getSimpleName()){
			case "FitnessExercise":
				frameStatusCreation.setContentPane(new FitnessExerciseStatusPanel((FitnessExercise) anExercise, mainController));				
				break;
				
			case "CardioExercise":
				frameStatusCreation.setContentPane(new CardioExerciseStatusPanel((CardioExercise) anExercise, mainController));		
				break;
				
			//TODO Adaptar los constructores ya creados de los estados de ejercicio para que carguen los datos del estado ya por defecto
			case "FitnessExerciseStatus":
				frameStatusCreation.setContentPane(new FitnessExerciseStatusPanel(theRoutine, (FitnessExerciseStatus) anExercise, mainController));	
				break;
				
			case "CardioExerciseStatus":
				frameStatusCreation.setContentPane(new CardioExerciseStatusPanel(theRoutine, (CardioExerciseStatus) anExercise, mainController));	
				break;
		}
		frameStatusCreation.setBounds(AppSettings.getInstance().getMainWindowPosX(), AppSettings.getInstance().getMainWindowPosY(), 640, 480);
		frameStatusCreation.setVisible(true);
	}

	/**
	 * Gets the class on demand.
	 *
	 * @param selectedItem the selected item
	 * @return the class on demand
	 */
	private Class<?> getClassOnDemand(String selectedItem) {
		if(selectedItem.equals(EXERCISES_TYPES[0]))
			return CardioExercise.class;
		else if(selectedItem.equals(EXERCISES_TYPES[1]))
			return FitnessExercise.class;
		else 
			return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable aController, Object anExercise) {
		frameStatusCreation.setVisible(false);
		jtableAddedExercises.addNewRow(anExercise);
	}
	
}
