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
import com.xelit3.gymstatus.model.dto.Routine;
import com.xelit3.gymstatus.model.dto.Serie;

/**
 * Panel for FitnessExerciseStatus
 */
public class FitnessExerciseStatusPanel extends JPanel implements ChangeListener, ActionListener {
		
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The Enum PanelAction.
	 */
	private enum PanelAction{
		SAVE, MODIFY}
	;
	
	/** The max series. */
	private static int MAX_SERIES = 5;
	
	/** The main controller. */
	private Controller mainController;
	
	/** The routine including. */
	private Routine theRoutineIncluding;
	
	/** The fitness exercise. */
	private FitnessExerciseStatus theFitnessExercise;
	
	/** The muscles list. */
	private List<Muscle> theMusclesList;
	
	/** The layout. */
	private SpringLayout theLayout;
	
	/** The lbl serie max weight. */
	private JLabel lblExerciseName, lblTrainedMuscle, lbSeriesNumber, lblSerieRepetitions, lblSerieMaxWeight;
	
	/** The tf exercise name. */
	private JTextField tfExerciseName;
	
	/** The cb trained muscle. */
	private JComboBox<Muscle> cbTrainedMuscle;
	
	/** The sp number series. */
	private JSpinner spNumberSeries;
	
	/** The list tf number repetitions. */
	private List<JFormattedTextField> theListTfNumberRepetitions = new ArrayList<JFormattedTextField>();
	
	/** The list tf max weight. */
	private List<JFormattedTextField> theListTfMaxWeight = new ArrayList<JFormattedTextField>();
	
	/** The btn action. */
	private JButton btnAction;
	
	/** The btn remove. */
	private JButton btnRemove;

	/** The errors. */
	private boolean theErrors = false;
	
	/**
	 * Instantiates a new fitness exercise status panel.
	 *
	 * @param anExercise the an exercise
	 * @param aController the a controller
	 */
	public FitnessExerciseStatusPanel(FitnessExercise anExercise, Controller aController) {
		this.theFitnessExercise = new FitnessExerciseStatus(anExercise);
		this.mainController = aController;
		this.createComponents();
		setBtnAction(PanelAction.SAVE);
	}
	
	/**
	 * Instantiates a new fitness exercise status panel.
	 * @param aRoutine 
	 *
	 * @param anExerciseStatus the an exercise status
	 * @param aController the a controller
	 */
	public FitnessExerciseStatusPanel(Routine aRoutine, FitnessExerciseStatus anExerciseStatus, Controller aController){
		theRoutineIncluding = aRoutine;
		this.theFitnessExercise = anExerciseStatus;
		this.mainController = aController;
		this.createComponents();
		setBtnAction(PanelAction.MODIFY);
		this.setRemoveButton();
	}
	
	/**
	 * Creates the components.
	 */
	private void createComponents(){
		boolean tmpUpdating = false;
		
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
		if(theFitnessExercise.getSeries().size() > 0){
			tmpUpdating = true;
			spNumberRepetitionsModel.setValue(theFitnessExercise.getSeries().size());
		}
		
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
		setDynamicControls(tmpUpdating);	
		
	}

	/**
	 * Sets the dynamic controls.
	 *
	 * @param isUpdating the new dynamic controls
	 */
	private void setDynamicControls(boolean isUpdating) {
		int tmpMax = MAX_SERIES;
		if(isUpdating)
			tmpMax = theFitnessExercise.getSeries().size();
					
		JFormattedTextField tmpFtfNumberRepetitions, tmpFtfMaxWeight;
		MaskFormatter mascaraNumberRepetitions = null, mascaraMaxWeight = null; 
		
		try {
			mascaraNumberRepetitions = new MaskFormatter("**");
			mascaraNumberRepetitions.setValidCharacters(" 0123456789");
			mascaraMaxWeight = new MaskFormatter("**.**");
			mascaraMaxWeight.setValidCharacters(" 0123456789");
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		int tmpWestPosition = 0;
		for (int i = 0; i < MAX_SERIES; i++) {
			tmpFtfNumberRepetitions = new JFormattedTextField(mascaraNumberRepetitions);
			tmpFtfMaxWeight = new JFormattedTextField(mascaraMaxWeight);
			
			if(isUpdating && i < tmpMax){
				tmpFtfNumberRepetitions.setText(String.valueOf(theFitnessExercise.getSeries().get(i).getRepetitions()));
				tmpFtfMaxWeight.setText(String.valueOf(theFitnessExercise.getSeries().get(i).getWeight()));
			}
			
			theListTfNumberRepetitions.add(tmpFtfNumberRepetitions);
			theListTfMaxWeight.add(tmpFtfMaxWeight);
			
			if(i < tmpMax){
				setDynamicControlsVisibility(i, true);
			}				
			else{
				setDynamicControlsVisibility(i, false);
			}			

			theLayout.putConstraint(SpringLayout.NORTH, theListTfNumberRepetitions.get(i), 35, SpringLayout.NORTH, spNumberSeries);
			theLayout.putConstraint(SpringLayout.WEST, theListTfNumberRepetitions.get(i), tmpWestPosition, SpringLayout.WEST, spNumberSeries);
			theLayout.putConstraint(SpringLayout.EAST, theListTfNumberRepetitions.get(i), 35, SpringLayout.WEST, theListTfNumberRepetitions.get(i));
			add(theListTfNumberRepetitions.get(i));
			
			theLayout.putConstraint(SpringLayout.NORTH, theListTfMaxWeight.get(i), 70, SpringLayout.NORTH, spNumberSeries);
			theLayout.putConstraint(SpringLayout.WEST, theListTfMaxWeight.get(i), tmpWestPosition, SpringLayout.WEST, spNumberSeries);
			theLayout.putConstraint(SpringLayout.EAST, theListTfMaxWeight.get(i), 35, SpringLayout.WEST, theListTfMaxWeight.get(i));
			add(theListTfMaxWeight.get(i));
			
			tmpWestPosition += 38;
		}		
	}

	/**
	 * Sets the btn action.
	 *
	 * @param anAction the new btn action
	 */
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
	
	/**
	 * Sets the remove button.
	 */
	private void setRemoveButton() {
		btnRemove = new JButton("Remove");
		btnRemove.setActionCommand("removeExercise");
		btnRemove.addActionListener(this);
		
		theLayout.putConstraint(SpringLayout.EAST, btnRemove, 0, SpringLayout.EAST, btnAction);
		theLayout.putConstraint(SpringLayout.NORTH, btnRemove, 5, SpringLayout.SOUTH, btnAction);
		
		add(btnRemove);
		
	}

	/**
	 * Sets the cb muscles.
	 */
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
	
	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		int tmpRepetitions = getNumberSeries();
		
		for (int i = 0; i < MAX_SERIES; i++) {
			if(i < tmpRepetitions){
				setDynamicControlsVisibility(i, true);
			}				
			else{
				setDynamicControlsVisibility(i, false);
			}				
		}
		
	}
	
	/**
	 * Sets the dynamic controls visibility.
	 *
	 * @param anIndex the an index
	 * @param aVisibility the a visibility
	 */
	private void setDynamicControlsVisibility(int anIndex, boolean aVisibility){
		theListTfMaxWeight.get(anIndex).setVisible(aVisibility);
		theListTfNumberRepetitions.get(anIndex).setVisible(aVisibility);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent anEvent) {
		//TODO Guardar el ejercicio o modificarlo, dependiendo desde que constructor se llame sabremos si es modificacion o creacion
		theErrors = false;
		setExerciseStatusSeries();
		switch(anEvent.getActionCommand()){
			case "SAVE":
				saveExercise();
				break;
					
			case "MODIFY":
				modifyExercise();
				break;
				
			case "removeExercise":
				int tmpConfirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove?");
				if(tmpConfirm == 0)
					mainController.removeExerciseFromRoutine(theRoutineIncluding, theFitnessExercise);
				break;
		}			
		
	}
	
	/**
	 * Save exercise.
	 */
	private void saveExercise(){
		if(!theErrors){
			mainController.saveExercise(theFitnessExercise);
		}
		else{
			JOptionPane.showMessageDialog(this, "There are any error(s) in the data for any or multiple exercises. Check it first");
			theErrors = false;
		}
	}
	
	/**
	 * Modify exercise.
	 */
	private void modifyExercise(){
		if(!theErrors){
			mainController.updateExercise(theFitnessExercise);
		}
		else{
			JOptionPane.showMessageDialog(this, "There are any error(s) in the data for any or multiple exercises. Check it first");
		}
	}
	
	/**
	 * Gets the number series.
	 *
	 * @return the number series
	 */
	private int getNumberSeries() {
		//Recogemos el valor escogido en el Spinner para mostrar u ocultar los diferentes JTFF para el peso de cada serie
		SpinnerNumberModel tmpNumberModel = (SpinnerNumberModel) spNumberSeries.getModel();
		int tmpSeriesNumber = tmpNumberModel.getNumber().intValue();
		
		return tmpSeriesNumber;
	}
	
	/**
	 * Sets the exercise status series.
	 */
	private void setExerciseStatusSeries(){
		this.theFitnessExercise.getSeries().clear();
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
