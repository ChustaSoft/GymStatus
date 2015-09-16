package com.xelit3.gymstatus.view.exercises;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus.CardioExerciseIntensity;

public class CardioExerciseStatusPanel extends JPanel implements ActionListener, ChangeListener, PropertyChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private enum PanelAction{SAVE, MODIFY};
	
	private Controller mainController = new Controller();
	
	private CardioExerciseStatus theExercise;
	
	private JTextField tfExerciseName;
	private JSpinner spIntensity;
	private JFormattedTextField tfTime;
	private JButton btnAction;

	private SpringLayout theLayout;
	
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
		
		JLabel lblTime = new JLabel("Time");
		theLayout.putConstraint(SpringLayout.NORTH, lblTime, 35, SpringLayout.NORTH, lblIntensity);
		theLayout.putConstraint(SpringLayout.WEST, lblTime, 0, SpringLayout.WEST, lblIntensity);
		theLayout.putConstraint(SpringLayout.EAST, lblTime, 0, SpringLayout.EAST, lblIntensity);
		add(lblTime);
		
		
		//Establecemos el control para el tiempo, y le establecemos un tiempo por defecto de 0 horas, 0 minutos, 0 segundos.
		Format timeFormatMask = new SimpleDateFormat("HH:mm:ss");
		Calendar tmpHelpCalendar = Calendar.getInstance();
		tmpHelpCalendar.set(Calendar.HOUR_OF_DAY, 0);
		tmpHelpCalendar.set(Calendar.MINUTE, 0);
		tmpHelpCalendar.set(Calendar.SECOND, 0);
		Date tmpInitTime = tmpHelpCalendar.getTime();
		
		this.tfTime = new JFormattedTextField(timeFormatMask);
		tfTime.setValue(tmpInitTime);
		tfTime.setHorizontalAlignment(JTextField.RIGHT);
//		tfTime.addPropertyChangeListener(this);
		theLayout.putConstraint(SpringLayout.VERTICAL_CENTER, tfTime, 35, SpringLayout.VERTICAL_CENTER, spIntensity);
		theLayout.putConstraint(SpringLayout.WEST, tfTime, 25, SpringLayout.EAST, lblTime);
		theLayout.putConstraint(SpringLayout.EAST, tfTime, 325, SpringLayout.WEST, lblTime);
		add(this.tfTime);		
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
		theLayout.putConstraint(SpringLayout.NORTH, btnAction, 225, SpringLayout.NORTH, this);
		
		btnAction.addActionListener(this);
		add(btnAction);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		CardioExerciseIntensity tmIntensity = (CardioExerciseIntensity) spIntensity.getValue();
		this.theExercise.setIntensity(tmIntensity);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//TODO Recoger el tiempo establecido y guardarlo dentro del ejercicio
		try{
			
			Date tmpTimeSelected = new Date(tfTime.getText());
			System.out.println();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
