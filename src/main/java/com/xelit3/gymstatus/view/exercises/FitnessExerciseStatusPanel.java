package com.xelit3.gymstatus.view.exercises;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
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

public class FitnessExerciseStatusPanel extends JPanel implements ChangeListener, ActionListener {
	
	private enum PanelAction{SAVE, MODIFY};

	private static final long serialVersionUID = 1L;
	
	private static int MAX_SERIES = 5;
	
	private Controller mainController = new Controller();
	
	private FitnessExerciseStatus theFitnessExercise;
	private List<Muscle> theMusclesList;	
	
	private JLabel lblExerciseName, lblTrainedMuscle, lbRepetitions, lblSeriesRepetitions;
	private SpringLayout springLayout;
	private JTextField tfExerciseName;
	private JComboBox<Muscle> cbTrainedMuscle;
	private JSpinner spNumberRepetitions;
	private List<JFormattedTextField> theListTfRepetitions = new ArrayList<JFormattedTextField>();
	private JButton btnAction;
	
	public FitnessExerciseStatusPanel(FitnessExercise anExercise) {
		this.theFitnessExercise = new FitnessExerciseStatus(anExercise);
		springLayout = new SpringLayout();
		setLayout(springLayout);
		this.createComponents();
		setBtnSave(PanelAction.SAVE);
	}
	
	public FitnessExerciseStatusPanel(FitnessExerciseStatus anExerciseStatus){
		this.theFitnessExercise = anExerciseStatus;
		springLayout = new SpringLayout();
		setLayout(springLayout);
		this.createComponents();
		setBtnSave(PanelAction.MODIFY);
	}
	
	private void createComponents(){
		
		ButtonGroup bgCrud = new ButtonGroup();
		
		this.lblExerciseName = new JLabel("Exercise name");
		springLayout.putConstraint(SpringLayout.NORTH, lblExerciseName, 75, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblExerciseName, 75, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblExerciseName, 200, SpringLayout.WEST, this);
		add(lblExerciseName);
		
		this.tfExerciseName = new JTextField(theFitnessExercise.getExerciseName());
		tfExerciseName.setHorizontalAlignment(JTextField.RIGHT);
		tfExerciseName.setEditable(false);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, tfExerciseName, 0, SpringLayout.VERTICAL_CENTER, lblExerciseName);
		springLayout.putConstraint(SpringLayout.WEST, tfExerciseName, 25, SpringLayout.EAST, lblExerciseName);
		springLayout.putConstraint(SpringLayout.EAST, tfExerciseName, 325, SpringLayout.WEST, lblExerciseName);
		tfExerciseName.setColumns(10);
		add(tfExerciseName);
		
		this.lblTrainedMuscle = new JLabel("Trained muscle");
		springLayout.putConstraint(SpringLayout.NORTH, lblTrainedMuscle, 35, SpringLayout.NORTH, lblExerciseName);
		springLayout.putConstraint(SpringLayout.WEST, lblTrainedMuscle, 0, SpringLayout.WEST, lblExerciseName);
		springLayout.putConstraint(SpringLayout.EAST, lblTrainedMuscle, 0, SpringLayout.EAST, lblExerciseName);
		add(lblTrainedMuscle);
		
		setCbMuscles();
				
		this.lbRepetitions = new JLabel("Repetitions");
		springLayout.putConstraint(SpringLayout.NORTH, lbRepetitions, 35, SpringLayout.NORTH, lblTrainedMuscle);
		springLayout.putConstraint(SpringLayout.WEST, lbRepetitions, 0, SpringLayout.WEST, lblTrainedMuscle);
		springLayout.putConstraint(SpringLayout.EAST, lbRepetitions, 0, SpringLayout.EAST, lblTrainedMuscle);
		add(lbRepetitions);
		
		//Definimos un modelo que obligar� a elegir entre uno y 5 ejercicios 
		SpinnerModel spNumberRepetitionsModel = new SpinnerNumberModel(5, 1, MAX_SERIES, 1);		
		this.spNumberRepetitions = new JSpinner(spNumberRepetitionsModel);
		springLayout.putConstraint(SpringLayout.WEST, spNumberRepetitions, 0, SpringLayout.WEST, tfExerciseName);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, spNumberRepetitions, 0, SpringLayout.VERTICAL_CENTER, lbRepetitions);
		springLayout.putConstraint(SpringLayout.EAST, spNumberRepetitions, 0, SpringLayout.EAST, tfExerciseName);
		spNumberRepetitions.addChangeListener(this);
		add(spNumberRepetitions);
		
		this.lblSeriesRepetitions = new JLabel("Series");
		springLayout.putConstraint(SpringLayout.NORTH, lblSeriesRepetitions, 35, SpringLayout.NORTH, lbRepetitions);
		springLayout.putConstraint(SpringLayout.WEST, lblSeriesRepetitions, 0, SpringLayout.WEST, lbRepetitions);
		springLayout.putConstraint(SpringLayout.EAST, lblSeriesRepetitions, 0, SpringLayout.EAST, lbRepetitions);	
		add(lblSeriesRepetitions);
		
		//Colocamos los 5 campos para el peso en las diferentes series, despu�s mediante el ComboBox, se ocultar�n o no dependiendo de las series que escojamos
		JFormattedTextField tmpFtfRepetitions;
		MaskFormatter mascara;
		int tmpWestPosition = 0;
		for (int i=0; i<MAX_SERIES; i++) {
			tmpFtfRepetitions = new JFormattedTextField();
			try {
				mascara = new MaskFormatter("##.##");
				tmpFtfRepetitions = new JFormattedTextField(mascara);				
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
			theListTfRepetitions.add(tmpFtfRepetitions);
			
			springLayout.putConstraint(SpringLayout.NORTH, theListTfRepetitions.get(i), 35, SpringLayout.NORTH, spNumberRepetitions);
			springLayout.putConstraint(SpringLayout.WEST, theListTfRepetitions.get(i), tmpWestPosition, SpringLayout.WEST, spNumberRepetitions);
			springLayout.putConstraint(SpringLayout.EAST, theListTfRepetitions.get(i), 35, SpringLayout.WEST, theListTfRepetitions.get(i));
			add(theListTfRepetitions.get(i));
			tmpWestPosition += 38;			
		}	
		
	}

	private void setBtnSave(PanelAction anAction) {
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
		
		springLayout.putConstraint(SpringLayout.EAST, btnAction, 0, SpringLayout.EAST, tfExerciseName);
		springLayout.putConstraint(SpringLayout.NORTH, btnAction, 225, SpringLayout.NORTH, this);
		
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
		springLayout.putConstraint(SpringLayout.WEST, cbTrainedMuscle, 0, SpringLayout.WEST, tfExerciseName);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cbTrainedMuscle, 0, SpringLayout.VERTICAL_CENTER, lblTrainedMuscle);
		springLayout.putConstraint(SpringLayout.EAST, cbTrainedMuscle, 0, SpringLayout.EAST, tfExerciseName);
		add(cbTrainedMuscle);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		//Recogemos el valor escogido en el Spinner para mostrar u ocultar los diferentes JTFF para el peso de cada serie
		SpinnerNumberModel tmpNumberModel = (SpinnerNumberModel) spNumberRepetitions.getModel();
		int tmpRepetitions = tmpNumberModel.getNumber().intValue();
		
		for (int i = 0; i < MAX_SERIES; i++) {
			if(i < tmpRepetitions)
				theListTfRepetitions.get(i).setVisible(true);
			else
				theListTfRepetitions.get(i).setVisible(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Guardar el ejercicio o modificarlo, dependiendo desde que constructor se llame sabremos si es modificacion o creacion
		
	}
	
	private void saveExercise(){

	}
	
	private void modifyExercise(){
		
	}
	
	private void removeExercise(){
		
	}
}
