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
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExercise;
import com.xelit3.gymstatus.model.dto.Muscle;

// TODO: Auto-generated Javadoc
/**
 * The Class FitnessExercisePanel.
 */
public class FitnessExercisePanel extends JPanel implements ActionListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The main controller. */
	private Controller mainController = new Controller();
	
	/** The lbl trained muscle. */
	private JLabel lblSelectExercise, lblExerciseName, lblTrainedMuscle;
	
	/** The tf exercise name. */
	private JTextField tfExerciseName;
	
	/** The cb exercise name. */
	private JComboBox<Exercise> cbExerciseName;
	
	/** The cb trained muscle. */
	private JComboBox<Muscle> cbTrainedMuscle;
	
	/** The rb delete. */
	private JRadioButton rbCreate, rbModify, rbDelete;
	
	/** The btn save. */
	private JButton btnSave;
	
	/** The muscles list. */
	private List<Muscle> musclesList;
	
	/**
	 * Instantiates a new fitness exercise panel.
	 */
	public FitnessExercisePanel() {
		setLayout(new GridBagLayout());
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.createComponents();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "setCreateForm":
				if(this.getCbExerciseName() != null){
					this.remove(cbExerciseName);
					this.cbExerciseName = null;
				}
				this.lblSelectExercise.setVisible(false);
				this.lblExerciseName.setVisible(true);
				this.lblTrainedMuscle.setVisible(true);
				this.setTfExerciseName();
				this.setCbMuscles();
				
				btnSave.setText("Save exercise");
				btnSave.setActionCommand("createExercise");
				this.updateUI();
				break;
				
			case "setModifyForm":
				this.setTfExerciseName();
				this.setCbExerciseName();
				this.setCbMuscles();
				this.lblSelectExercise.setVisible(true);
				this.lblExerciseName.setVisible(true);
				this.lblTrainedMuscle.setVisible(true);
				
				btnSave.setText("Modify exercise");
				btnSave.setActionCommand("modifyExercise");
				this.updateUI();
				break;
				
			case "setDeleteForm":
				if(this.getTfExerciseName() != null){
					this.remove(this.tfExerciseName);
					this.tfExerciseName = null;
				}
					
				if(this.getCbMuscles() != null){
					this.remove(cbTrainedMuscle);
					this.cbTrainedMuscle = null;
				}
				this.setCbExerciseName();
				this.lblSelectExercise.setVisible(true);
				this.lblExerciseName.setVisible(false);
				this.lblTrainedMuscle.setVisible(false);
				
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
		}
		
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
		
		this.lblTrainedMuscle = new JLabel("Trained muscle");
		this.add(lblTrainedMuscle, getGridConstraint(1, 0, 3, new Insets(0, 0, 10, 25)));
			
		this.setTfExerciseName();
		this.setCbMuscles();
				
		this.btnSave = new JButton("Save exercise");
		btnSave.setActionCommand("createExercise");
		btnSave.addActionListener(this);
		this.add(btnSave, getGridConstraint(1, 1, 4, new Insets(0, 25, 100, 0)));
	}

	/**
	 * With this method we get the constraints for a component inside the panel
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
		if(this.getCbExerciseName() == null){
			List<Exercise> tmpExerciseList = mainController.getExercises(FitnessExercise.class);

			this.cbExerciseName = new JComboBox<Exercise>(ConversorUtilitiy.obtainExercises(tmpExerciseList));
			this.add(cbExerciseName, getGridConstraint(1, 1, 1, new Insets(0, 0, 10, 0)));
		}		
	}
	
	/**
	 * Gets the cb muscles.
	 *
	 * @return the cb muscles
	 */
	public JComboBox<?> getCbMuscles(){
		return this.cbTrainedMuscle;
	}
	
	/**
	 * Sets the cb muscles.
	 */
	public void setCbMuscles() {
		if(this.getCbMuscles() == null){
			musclesList = mainController.getMuscles();
			
			this.cbTrainedMuscle = new JComboBox<Muscle>(ConversorUtilitiy.obtainMuscles(musclesList));
			this.add(cbTrainedMuscle, getGridConstraint(1, 1, 3, new Insets(0, 0, 10, 0)));
		}		
	}
	
	/**
	 * Save exercise.
	 */
	private void saveExercise(){
		FitnessExercise tmpFitnessExercise = new FitnessExercise();
		tmpFitnessExercise.setExerciseName(tfExerciseName.getText());
		tmpFitnessExercise.setTrainedMuscle(musclesList.get(cbTrainedMuscle.getSelectedIndex()));
		
		this.mainController.saveExercise(tmpFitnessExercise);
	}
	
	/**
	 * Modify exercise.
	 */
	private void modifyExercise(){		
		FitnessExercise tmpExercise = (FitnessExercise) cbExerciseName.getSelectedItem();
		tmpExercise.setExerciseName(this.getTfExerciseName().getText());
				
		boolean tmpResponse = mainController.updateExercise(tmpExercise);
		
		//We change also the name into the combobox
		if(tmpResponse)
			((FitnessExercise) cbExerciseName.getSelectedItem()).setExerciseName(this.getTfExerciseName().getText());
		
		this.updateUI();
	}
	
	/**
	 * Removes the exercise.
	 */
	private void removeExercise(){		
		boolean tmpResponse = mainController.removeExercise((FitnessExercise) this.getCbExerciseName().getSelectedItem());
		
		if(tmpResponse)
			this.getCbExerciseName().removeItemAt(this.getCbExerciseName().getSelectedIndex());;
		
		this.updateUI();
	}
	
}
