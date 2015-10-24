package com.xelit3.gymstatus.view.exercises;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.JSpinner.DateEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus.CardioExerciseIntensity;

public class CardioExerciseStatusPanel extends JPanel implements ActionListener, ChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private enum PanelAction{SAVE, MODIFY};
	
	private Controller mainController = new Controller();
	
	private CardioExerciseStatus theExercise;

	private SpringLayout theLayout;
	
	private JTextField tfExerciseName;
	private JSpinner spIntensity, spTime;
	private JButton btnAction;
				
	public CardioExerciseStatusPanel(CardioExercise anExercise) {
		this.theExercise = new CardioExerciseStatus(anExercise);
		this.createComponents();	
		this.setBtnAction(PanelAction.SAVE);
	}
	
	public CardioExerciseStatusPanel(CardioExerciseStatus anExercise){
		this.theExercise = anExercise;
		this.createComponents();
		this.setBtnAction(PanelAction.MODIFY);
	}
	
	private void createComponents(){
		theLayout = new SpringLayout();
		setLayout(theLayout);
	
		JLabel lblExerciseName = new JLabel("Exercise name");
		theLayout.putConstraint(SpringLayout.NORTH, lblExerciseName, 75, SpringLayout.NORTH, this);
		theLayout.putConstraint(SpringLayout.WEST, lblExerciseName, 75, SpringLayout.WEST, this);
		theLayout.putConstraint(SpringLayout.EAST, lblExerciseName, 200, SpringLayout.WEST, this);
		add(lblExerciseName);
		
		tfExerciseName = new JTextField(this.theExercise.getExerciseName());
		tfExerciseName.setEditable(false);
		tfExerciseName.setHorizontalAlignment(JTextField.RIGHT);
		theLayout.putConstraint(SpringLayout.VERTICAL_CENTER, tfExerciseName, 0, SpringLayout.VERTICAL_CENTER, lblExerciseName);
		theLayout.putConstraint(SpringLayout.WEST, tfExerciseName, 25, SpringLayout.EAST, lblExerciseName);
		theLayout.putConstraint(SpringLayout.EAST, tfExerciseName, 325, SpringLayout.WEST, lblExerciseName);
		tfExerciseName.setColumns(10);
		add(tfExerciseName);
				
		JLabel lblIntensity = new JLabel("Intensity");
		theLayout.putConstraint(SpringLayout.NORTH, lblIntensity, 35, SpringLayout.NORTH, lblExerciseName);
		theLayout.putConstraint(SpringLayout.WEST, lblIntensity, 0, SpringLayout.WEST, lblExerciseName);		
		theLayout.putConstraint(SpringLayout.EAST, lblIntensity, 0, SpringLayout.EAST, lblExerciseName);
		add(lblIntensity);
		
		//Establecemos el spinner con los objectos que hay en la enum de intensidad de la clase CardioExercise
		SpinnerListModel spIntensityModel = new SpinnerListModel(new Object[] {
				CardioExerciseIntensity.HIGH, CardioExerciseIntensity.MEDIUM, CardioExerciseIntensity.SOFT}
		);
		
		this.spIntensity = new JSpinner(spIntensityModel);
		theLayout.putConstraint(SpringLayout.VERTICAL_CENTER, spIntensity, 35, SpringLayout.VERTICAL_CENTER, tfExerciseName);
		theLayout.putConstraint(SpringLayout.WEST, spIntensity, 25, SpringLayout.EAST, lblIntensity);
		theLayout.putConstraint(SpringLayout.EAST, spIntensity, 325, SpringLayout.WEST, lblIntensity);
		spIntensity.addChangeListener(this);
		add(spIntensity);
		
		JLabel lblTime = new JLabel("Time (min)");
		theLayout.putConstraint(SpringLayout.NORTH, lblTime, 35, SpringLayout.NORTH, lblIntensity);
		theLayout.putConstraint(SpringLayout.WEST, lblTime, 0, SpringLayout.WEST, lblIntensity);
		theLayout.putConstraint(SpringLayout.EAST, lblTime, 0, SpringLayout.EAST, lblIntensity);
		add(lblTime);

		//Establecemos el Spinner para los minutos
		SpinnerNumberModel snm = new SpinnerNumberModel(1, 1, 250, 1);
		spTime = new JSpinner(snm);
		theLayout.putConstraint(SpringLayout.VERTICAL_CENTER, spTime, 35, SpringLayout.VERTICAL_CENTER, spIntensity);
		theLayout.putConstraint(SpringLayout.WEST, spTime, 25, SpringLayout.EAST, lblTime);
		theLayout.putConstraint(SpringLayout.EAST, spTime, 325, SpringLayout.WEST, lblTime);
		add(this.spTime);	
	}
	
	private void setBtnAction(PanelAction anAction) {
		switch(anAction){
			case SAVE:
				this.btnAction = new JButton("Save status");
				btnAction.setActionCommand(anAction.toString());
				break;
				
			case MODIFY:
				this.btnAction = new JButton("Modify status");
				btnAction.setActionCommand(anAction.toString());
				break;
		}
		
		theLayout.putConstraint(SpringLayout.EAST, btnAction, 0, SpringLayout.EAST, tfExerciseName);
		theLayout.putConstraint(SpringLayout.NORTH, btnAction, 250, SpringLayout.NORTH, this);
		
		btnAction.addActionListener(this);
		add(btnAction);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		
			case "SAVE":
				saveExerciseStatus();
				break;
				
			case "MODIFY":
				modifyExerciseStatus();
				break;
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		CardioExerciseIntensity tmIntensity = (CardioExerciseIntensity) spIntensity.getValue();
		this.theExercise.setIntensity(tmIntensity);
	}
	
	private void saveExerciseStatus() {
		int tmpMinutes = (int) this.spTime.getModel().getValue();
		long tmpSeconds = (long) (tmpMinutes * 60 * 1000);
		
		this.theExercise.setTime(tmpSeconds);
		
		stateChanged(null);
		
		this.mainController.saveExercise(theExercise);
	}
	
	private void modifyExerciseStatus() {
		// TODO Modificar ejercicio, desde el panel de Rutinas
	}
}
