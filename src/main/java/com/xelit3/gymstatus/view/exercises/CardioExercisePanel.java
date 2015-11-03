package com.xelit3.gymstatus.view.exercises;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.control.utilities.ConversorUtilitiy;
import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.Exercise;

// TODO: Auto-generated Javadoc
//TODO: Auto-generated Javadoc
/**
 * The Class CardioExercisePanel.
 * 
 */
public class CardioExercisePanel extends JPanel implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The main controller. */
	private Controller mainController = new Controller();
	
	/** The lbl exercise name. */
	private JLabel lblSelectExercise, lblExerciseName;
	
	/** The tf exercise name. */
	private JTextField tfExerciseName;
	
	/** The cb exercise name. */
	private JComboBox<Exercise> cbExerciseName;
	
	/** The rb delete. */
	private JRadioButton rbCreate, rbModify, rbDelete;
	
	/** The btn save. */
	private JButton btnSave;
	
	/**
	 * Instantiates a new cardio exercise panel.
	 */
	public CardioExercisePanel() {
		setLayout(new GridBagLayout());
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.createComponents();
	}
	
	/**
	 * Action performed.
	 *
	 * @param e the e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Realizar una enum para determinar si vamos a arreglar un ejercicio o el estado de un ejercicio, swtich case pertinenete primero antes del CRUD
		switch(e.getActionCommand()){
			case "setCreateForm":				
				this.lblExerciseName.setVisible(true);
				this.lblSelectExercise.setVisible(false);
				this.cbExerciseName.setVisible(false);
				this.tfExerciseName.setVisible(true);
				
				btnSave.setText("Save exercise");
				btnSave.setActionCommand("createExercise");				
				break;
				
			case "setModifyForm":
				this.setCbExerciseName();
				refreshControlsModifyPanel();
				this.cbExerciseName.setVisible(true);
				this.tfExerciseName.setVisible(true);
				this.lblSelectExercise.setVisible(true);
				this.lblExerciseName.setVisible(true);
				
				btnSave.setText("Modify exercise");
				btnSave.setActionCommand("modifyExercise");				
				break;
				
			case "setDeleteForm":
				this.setCbExerciseName();
				this.cbExerciseName.removeActionListener(this);
				this.lblExerciseName.setVisible(false);
				this.lblSelectExercise.setVisible(true);
				this.cbExerciseName.setVisible(true);
				this.tfExerciseName.setVisible(false);
				
				btnSave.setText("Remove exercise");
				btnSave.setActionCommand("removeExercise");
				break;
				
			case "createExercise":
				saveExercise();
				break;
				
			case "modifyExercise":
				modifyExercise();
				break;
				
			case "removeExercise":
				removeExercise();
				break;
				
			case "changeExerciseData":
				refreshControlsModifyPanel();
				break;
		}
		
		this.updateUI();
		
	}
	
	/**
	 * Creates the components.
	 */
	private void createComponents(){
				
		//Radiobuttons control top panel 
		JPanel rgbPanel = new JPanel(new FlowLayout());
		
		ButtonGroup bgCrud = new ButtonGroup();
			
		rbCreate = new JRadioButton("Create");
		rbCreate.setActionCommand("setCreateForm");
		rbCreate.addActionListener(this);
		rbCreate.setSelected(true);
		rgbPanel.add(rbCreate);
		
		rbModify = new JRadioButton("Modify");
		rbModify.setActionCommand("setModifyForm");
		rbModify.addActionListener(this);
		rgbPanel.add(rbModify);
		
		rbDelete = new JRadioButton("Delete");
		rbDelete.setActionCommand("setDeleteForm");
		rbDelete.addActionListener(this);
		rgbPanel.add(rbDelete);
		
		bgCrud.add(rbCreate);
		bgCrud.add(rbModify);
		bgCrud.add(rbDelete);
				
		this.add(rgbPanel, getGridConstraint(2, 0, 0, new Insets(0, 0, 30, 0)));
		
		this.lblSelectExercise = new JLabel("Select exercise");
		this.add(lblSelectExercise, getGridConstraint(1, 0, 1, new Insets(0, 0, 10, 25)));
		this.lblSelectExercise.setVisible(false);
		
		this.lblExerciseName = new JLabel("Exercise name");
		this.add(lblExerciseName, getGridConstraint(1, 0, 2, new Insets(0, 0, 10, 25)));
		
		this.setCbExerciseName();
		this.setTfExerciseName();
		
		this.cbExerciseName.setVisible(false);
		
		this.btnSave = new JButton("Save exercise");
		btnSave.setActionCommand("createExercise");
		btnSave.addActionListener(this);
		this.add(btnSave, getGridConstraint(1, 1, 3, new Insets(0, 25, 100, 0)));
	}

	/**
	 * With this method we get the constraints for a component inside the panel.
	 *
	 * @param aWidth thw width for the component
	 * @param aCol the column
	 * @param aRow the row
	 * @param anInsets margins
	 * @return the formed constraint
	 */
	private GridBagConstraints getGridConstraint(int aWidth, int aCol, int aRow, Insets anInsets) {
		GridBagConstraints tmpPanelConstraints = new GridBagConstraints();
		
		tmpPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
		tmpPanelConstraints.gridwidth = aWidth;
		tmpPanelConstraints.gridx = aCol;
		tmpPanelConstraints.gridy = aRow;
		
		if(anInsets != null)
			tmpPanelConstraints.insets = anInsets;
				
		return tmpPanelConstraints;
	}

	/**
	 * Gets the tf exercise name.
	 *
	 * @return the tf exercise name
	 */
	public JTextField getTfExerciseName() {
		return tfExerciseName;
	}

	/**
	 * Sets the tf exercise name.
	 */
	public void setTfExerciseName() {
		if(this.getTfExerciseName() == null){
			this.tfExerciseName = new JTextField(JTextField.RIGHT);
			this.add(tfExerciseName, getGridConstraint(1, 1, 2, new Insets(0, 0, 10, 0)));
		}		
	}

	/**
	 * Gets the cb exercise name.
	 *
	 * @return the cb exercise name
	 */
	public JComboBox<?> getCbExerciseName() {
		return cbExerciseName;
	}

	/**
	 * Sets the cb exercise name.
	 */
	public void setCbExerciseName() {
		if(this.getCbExerciseName() != null)
			this.remove(this.getCbExerciseName());
		
		List<Exercise> tmpExerciseList = mainController.getExercises(CardioExercise.class);

		this.cbExerciseName = new JComboBox<Exercise>(ConversorUtilitiy.obtainExercises(tmpExerciseList));
		this.cbExerciseName.setActionCommand("changeExerciseData");
		this.cbExerciseName.addActionListener(this);
		
		this.add(cbExerciseName, getGridConstraint(1, 1, 1, new Insets(0, 0, 10, 0)));			
	}
	
	/**
	 * Save exercise.
	 */
	private void saveExercise(){
		CardioExercise tmpCardioExercise = new CardioExercise();
		tmpCardioExercise.setExerciseName(this.tfExerciseName.getText());
		
		mainController.saveExercise(tmpCardioExercise);
		
		this.updateUI();
	}
	
	/**
	 * Modify exercise.
	 */
	private void modifyExercise(){
		CardioExercise tmpExercise = (CardioExercise) cbExerciseName.getSelectedItem();
		tmpExercise.setExerciseName(this.getTfExerciseName().getText());		
		
		boolean tmpResponse = mainController.updateExercise(tmpExercise);
		
		//We change also the name into the combobox
		if(tmpResponse)
			((CardioExercise) cbExerciseName.getSelectedItem()).setExerciseName(this.getTfExerciseName().getText());
				
		this.updateUI();
	}
	
	/**
	 * Removes the exercise.
	 */
	private void removeExercise(){		
		boolean tmpResponse = mainController.removeExercise((CardioExercise) this.getCbExerciseName().getSelectedItem());
		
		if(tmpResponse)
			this.getCbExerciseName().removeItemAt(this.getCbExerciseName().getSelectedIndex());;
		
		this.updateUI();
	}
	
	/**
	 * Refresh controls when the modify panel is set.
	 */
	private void refreshControlsModifyPanel() {
		this.tfExerciseName.setText(((Exercise)this.cbExerciseName.getSelectedItem()).getExerciseName());
	}
	
}

