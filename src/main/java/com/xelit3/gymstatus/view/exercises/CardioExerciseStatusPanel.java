package com.xelit3.gymstatus.view.exercises;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus.CardioExerciseIntensity;
import com.xelit3.gymstatus.model.dto.Routine;

// TODO: Auto-generated Javadoc
/**
 * The Class CardioExerciseStatusPanel.
 */
public class CardioExerciseStatusPanel extends JPanel implements ActionListener, ChangeListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The Enum PanelAction.
	 */
	private enum PanelAction{
		SAVE, MODIFY
	};
	
	/** The main controller. */
	private Controller mainController;
	
	/** The routine including. */
	private Routine theRoutineIncluding;
	
	/** The exercise. */
	private CardioExerciseStatus theExercise;

	/** The layout. */
	private SpringLayout theLayout;
	
	/** The tf exercise name. */
	private JTextField tfExerciseName;
	
	/** The sp time. */
	private JSpinner spIntensity, spTime;
	
	/** The btn action. */
	private JButton btnAction;
	
	/** The btn remove. */
	private JButton btnRemove;
	
	/**
	 * Instantiates a new cardio exercise status panel.
	 *
	 * @param anExercise the an exercise
	 * @param aController the a controller
	 */
	public CardioExerciseStatusPanel(CardioExercise anExercise, Controller aController) {
		this.theExercise = new CardioExerciseStatus(anExercise);
		this.mainController = aController;
		this.createComponents();	
		this.setBtnAction(PanelAction.SAVE);
	}
	
	/**
	 * Instantiates a new cardio exercise status panel.
	 *
	 * @param anExercise the an exercise
	 * @param aController the a controller
	 */
	public CardioExerciseStatusPanel(Routine aRoutine, CardioExerciseStatus anExercise, Controller aController) {
		this.theRoutineIncluding = aRoutine;
		this.theExercise = anExercise;
		this.mainController = aController;
		this.createComponents();	
		this.setBtnAction(PanelAction.MODIFY);
		this.setRemoveButton();
	}

	/**
	 * Creates the components.
	 */
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
		if(theExercise.getIntensity() != null)
			spIntensityModel.setValue(theExercise.getIntensity());
		
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
		if(theExercise.getTime() > 0)
			spTime.setValue(theExercise.getTime() / 60000);
		theLayout.putConstraint(SpringLayout.VERTICAL_CENTER, spTime, 35, SpringLayout.VERTICAL_CENTER, spIntensity);
		theLayout.putConstraint(SpringLayout.WEST, spTime, 25, SpringLayout.EAST, lblTime);
		theLayout.putConstraint(SpringLayout.EAST, spTime, 325, SpringLayout.WEST, lblTime);
		add(this.spTime);	
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

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		
			case "SAVE":
				saveExerciseStatus();
				break;
				
			case "MODIFY":
				modifyExerciseStatus();
				break;
				
			case "removeExercise":
				int tmpConfirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove?");
				if(tmpConfirm == 0)
					mainController.removeExerciseFromRoutine(theRoutineIncluding, theExercise);
				break;
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		CardioExerciseIntensity tmIntensity = (CardioExerciseIntensity) spIntensity.getValue();
		this.theExercise.setIntensity(tmIntensity);
	}
	
	/**
	 * Save exercise status.
	 */
	private void saveExerciseStatus() {		
		if(setData())		
			this.mainController.saveExercise(theExercise);
	}
	
	/**
	 * Modify exercise status.
	 */
	private void modifyExerciseStatus() {
		if(setData())	
			this.mainController.updateExercise(theExercise);
	}
	
	/**
	 * Sets the data.
	 *
	 * @return true, if successful
	 */
	public boolean setData(){
		try{
			int tmpMinutes = (int) this.spTime.getModel().getValue();
			long tmpSeconds = (long) (tmpMinutes * 60 * 1000);
			
			this.theExercise.setTime(tmpSeconds);
			
			stateChanged(null);
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	
}
