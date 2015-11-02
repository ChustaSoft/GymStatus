package com.xelit3.gymstatus.view.exercises;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.control.utilities.ConversorUtilitiy;
import com.xelit3.gymstatus.model.dto.FitnessExercise;
import com.xelit3.gymstatus.model.dto.FitnessExerciseStatus;
import com.xelit3.gymstatus.model.dto.Muscle;
import com.xelit3.gymstatus.model.dto.Serie;

public class FitnessExerciseStatusPanel extends JPanel implements ChangeListener, ActionListener {
		
	private static final long serialVersionUID = 1L;
	
	private enum PanelAction{SAVE, MODIFY};
	
	private static int MAX_SERIES = 5;
	
	private Controller mainController;
	
	private FitnessExerciseStatus theFitnessExercise;
	private List<Muscle> theMusclesList;
	
	private SpringLayout theLayout;
	private JLabel lblExerciseName, lblTrainedMuscle, lbSeriesNumber, lblSerieRepetitions, lblSerieMaxWeight;
	private JTextField tfExerciseName;
	private JComboBox<Muscle> cbTrainedMuscle;
	private JSpinner spNumberSeries;
	private List<JFormattedTextField> theListTfNumberRepetitions = new ArrayList<JFormattedTextField>();
	private List<JFormattedTextField> theListTfMaxWeight = new ArrayList<JFormattedTextField>();
	private JButton btnAction;

	private boolean theErrors = false;
	
	public FitnessExerciseStatusPanel(FitnessExercise anExercise, Controller aController) {
		this.theFitnessExercise = new FitnessExerciseStatus(anExercise);
		this.mainController = aController;
		this.createComponents();
		setBtnAction(PanelAction.SAVE);
	}
	
	public FitnessExerciseStatusPanel(FitnessExerciseStatus anExerciseStatus, Controller aController){
		this.theFitnessExercise = anExerciseStatus;
		this.mainController = aController;
		this.createComponents();
		setBtnAction(PanelAction.MODIFY);
	}
	
	private void createComponents(){
		theLayout = new SpringLayout();
		setLayout(theLayout);
		
		this.lblExerciseName = new JLabel("Exercise name");
		theLayout.putConstraint(SpringLayout.NORTH, lblExerciseName, 75, SpringLayout.NORTH, this);
		theLayout.putConstraint(SpringLayout.WEST, lblExerciseName, 75, SpringLayout.WEST, this);
		theLayout.putConstraint(SpringLayout.EAST, lblExerciseName, 200, SpringLayout.WEST, this);
		add(lblExerciseName);
		
		this.tfExerciseName = new JTextField(theFitnessExercise.getExerciseName());
		tfExerciseName.setHorizontalAlignment(JTextField.RIGHT);
		tfExerciseName.setEditable(false);
		theLayout.putConstraint(SpringLayout.VERTICAL_CENTER, tfExerciseName, 0, SpringLayout.VERTICAL_CENTER, lblExerciseName);
		theLayout.putConstraint(SpringLayout.WEST, tfExerciseName, 25, SpringLayout.EAST, lblExerciseName);
		theLayout.putConstraint(SpringLayout.EAST, tfExerciseName, 325, SpringLayout.WEST, lblExerciseName);
		tfExerciseName.setColumns(10);
		add(tfExerciseName);
		
		this.lblTrainedMuscle = new JLabel("Trained muscle");
		theLayout.putConstraint(SpringLayout.NORTH, lblTrainedMuscle, 35, SpringLayout.NORTH, lblExerciseName);
		theLayout.putConstraint(SpringLayout.WEST, lblTrainedMuscle, 0, SpringLayout.WEST, lblExerciseName);
		theLayout.putConstraint(SpringLayout.EAST, lblTrainedMuscle, 0, SpringLayout.EAST, lblExerciseName);
		add(lblTrainedMuscle);
		
		setCbMuscles();
				
		this.lbSeriesNumber = new JLabel("Number repetitions");
		theLayout.putConstraint(SpringLayout.NORTH, lbSeriesNumber, 35, SpringLayout.NORTH, lblTrainedMuscle);
		theLayout.putConstraint(SpringLayout.WEST, lbSeriesNumber, 0, SpringLayout.WEST, lblTrainedMuscle);
		theLayout.putConstraint(SpringLayout.EAST, lbSeriesNumber, 0, SpringLayout.EAST, lblTrainedMuscle);
		add(lbSeriesNumber);
		
		//Definimos un modelo que obligará a elegir entre uno y 5 ejercicios 
		SpinnerModel spNumberRepetitionsModel = new SpinnerNumberModel(5, 1, MAX_SERIES, 1);		
		this.spNumberSeries = new JSpinner(spNumberRepetitionsModel);
		theLayout.putConstraint(SpringLayout.WEST, spNumberSeries, 0, SpringLayout.WEST, tfExerciseName);
		theLayout.putConstraint(SpringLayout.VERTICAL_CENTER, spNumberSeries, 0, SpringLayout.VERTICAL_CENTER, lbSeriesNumber);
		theLayout.putConstraint(SpringLayout.EAST, spNumberSeries, 0, SpringLayout.EAST, tfExerciseName);
		spNumberSeries.addChangeListener(this);
		add(spNumberSeries);
		
		this.lblSerieRepetitions = new JLabel("Number repetitions");
		theLayout.putConstraint(SpringLayout.NORTH, lblSerieRepetitions, 35, SpringLayout.NORTH, lbSeriesNumber);
		theLayout.putConstraint(SpringLayout.WEST, lblSerieRepetitions, 0, SpringLayout.WEST, lbSeriesNumber);
		theLayout.putConstraint(SpringLayout.EAST, lblSerieRepetitions, 0, SpringLayout.EAST, lbSeriesNumber);	
		add(lblSerieRepetitions);
		
		this.lblSerieMaxWeight = new JLabel("Max weight");
		theLayout.putConstraint(SpringLayout.NORTH, lblSerieMaxWeight, 35, SpringLayout.NORTH, lblSerieRepetitions);
		theLayout.putConstraint(SpringLayout.WEST, lblSerieMaxWeight, 0, SpringLayout.WEST, lblSerieRepetitions);
		theLayout.putConstraint(SpringLayout.EAST, lblSerieMaxWeight, 0, SpringLayout.EAST, lblSerieRepetitions);	
		add(lblSerieMaxWeight);
		
		//Colocamos los 5 campos para el numero de repetitiones y peso en las diferentes series, después mediante el ComboBox, se ocultarán o no dependiendo de las series que escojamos
		JFormattedTextField tmpFtfNumberRepetitions;
		MaskFormatter mascara;
		int tmpWestPosition = 0;
		for (int i = 0; i < MAX_SERIES; i++) {
			tmpFtfNumberRepetitions = new JFormattedTextField();
			try {
				mascara = new MaskFormatter("**");
				mascara.setValidCharacters(" 0123456789");
				tmpFtfNumberRepetitions = new JFormattedTextField(mascara);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
			theListTfNumberRepetitions.add(tmpFtfNumberRepetitions);

			theLayout.putConstraint(SpringLayout.NORTH, theListTfNumberRepetitions.get(i), 35, SpringLayout.NORTH, spNumberSeries);
			theLayout.putConstraint(SpringLayout.WEST, theListTfNumberRepetitions.get(i), tmpWestPosition, SpringLayout.WEST, spNumberSeries);
			theLayout.putConstraint(SpringLayout.EAST, theListTfNumberRepetitions.get(i), 35, SpringLayout.WEST, theListTfNumberRepetitions.get(i));
			add(theListTfNumberRepetitions.get(i));
			tmpWestPosition += 38;
		}
		
		JFormattedTextField tmpFtfMaxWeight;
		tmpWestPosition = 0;
		for (int i=0; i<MAX_SERIES; i++) {
			tmpFtfMaxWeight = new JFormattedTextField();
			try {
				mascara = new MaskFormatter("**.**");
				mascara.setValidCharacters(" 0123456789");
				tmpFtfMaxWeight = new JFormattedTextField(mascara);				
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
			theListTfMaxWeight.add(tmpFtfMaxWeight);
			
			theLayout.putConstraint(SpringLayout.NORTH, theListTfMaxWeight.get(i), 70, SpringLayout.NORTH, spNumberSeries);
			theLayout.putConstraint(SpringLayout.WEST, theListTfMaxWeight.get(i), tmpWestPosition, SpringLayout.WEST, spNumberSeries);
			theLayout.putConstraint(SpringLayout.EAST, theListTfMaxWeight.get(i), 35, SpringLayout.WEST, theListTfMaxWeight.get(i));
			add(theListTfMaxWeight.get(i));
			tmpWestPosition += 38;			
		}	
		
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

	private void setCbMuscles() {
		theMusclesList = mainController.getMuscles();
		
		this.cbTrainedMuscle = new JComboBox<Muscle>(ConversorUtilitiy.obtainMuscles(theMusclesList));
		
		// Ambas soluciones son correctas, en un principio fallaba porque al comparar los objetos, los identificaba como diferentes, sobrescribir el metodo equals conforme nuestros intereses soluciona el problema	
		this.cbTrainedMuscle.setSelectedItem(theFitnessExercise.getTrainedMuscle());
//		this.cbTrainedMuscle.setSelectedIndex(theMusclesList.indexOf(theFitnessExercise.getTrainedMuscle()));
		this.cbTrainedMuscle.setEnabled(false);
		theLayout.putConstraint(SpringLayout.WEST, cbTrainedMuscle, 0, SpringLayout.WEST, tfExerciseName);
		theLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cbTrainedMuscle, 0, SpringLayout.VERTICAL_CENTER, lblTrainedMuscle);
		theLayout.putConstraint(SpringLayout.EAST, cbTrainedMuscle, 0, SpringLayout.EAST, tfExerciseName);
		add(cbTrainedMuscle);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		int tmpRepetitions = getNumberSeries();
		
		for (int i = 0; i < MAX_SERIES; i++) {
			if(i < tmpRepetitions){
				theListTfMaxWeight.get(i).setVisible(true);
				theListTfNumberRepetitions.get(i).setVisible(true);
			}				
			else{
				theListTfMaxWeight.get(i).setVisible(false);
				theListTfNumberRepetitions.get(i).setVisible(false);
			}				
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent anEvent) {
		//TODO Guardar el ejercicio o modificarlo, dependiendo desde que constructor se llame sabremos si es modificacion o creacion
		setExerciseStatusSeries();
		switch(anEvent.getActionCommand()){
			case "SAVE":
				saveExercise();
				break;
					
			case "MODIFY":
				modifyExercise();
				break;
		}			
		
	}
	
	private void saveExercise(){
		if(!theErrors){
			mainController.saveExercise(theFitnessExercise);
		}
		else{
			JOptionPane.showMessageDialog(this, "There are any error(s) in the data for any or multiple exercises. Check it first");
		}
	}
	
	private void modifyExercise(){
		// TODO Modificar ejercicio, desde el panel de Rutinas
	}
	
	private int getNumberSeries() {
		//Recogemos el valor escogido en el Spinner para mostrar u ocultar los diferentes JTFF para el peso de cada serie
		SpinnerNumberModel tmpNumberModel = (SpinnerNumberModel) spNumberSeries.getModel();
		int tmpSeriesNumber = tmpNumberModel.getNumber().intValue();
		
		return tmpSeriesNumber;
	}
	
	private void setExerciseStatusSeries(){
		try {
			Serie tmpSerie;
			for(int tmpI = 0; tmpI < getNumberSeries(); tmpI++){
				tmpSerie = new Serie();
				tmpSerie.setRepetitions(Integer.parseInt(this.theListTfNumberRepetitions.get(tmpI).getText().trim()));
				tmpSerie.setWeight(Double.parseDouble(this.theListTfMaxWeight.get(tmpI).getText().trim()));
				if(tmpSerie.getRepetitions() != 0){
					this.theFitnessExercise.getSeries().add(tmpSerie);
				}
				else{
					theErrors = true;
				}
				
			}
		} catch (NumberFormatException e) {
			theErrors = true;
		}		
	}

}
