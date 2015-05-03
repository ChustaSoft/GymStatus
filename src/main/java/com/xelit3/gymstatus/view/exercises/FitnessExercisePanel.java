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

public class FitnessExercisePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Controller mainController = new Controller();
	
	private JLabel lblSelectExercise, lblExerciseName, lblTrainedMuscle;
	private JTextField tfExerciseName;
	private JComboBox<Exercise> cbExerciseName;
	private JComboBox<Muscle> cbTrainedMuscle;
	private JRadioButton rbCreate, rbModify, rbDelete;
	private JButton btnSave;
	
	private List<Muscle> musclesList;
	
	public FitnessExercisePanel() {
		setLayout(new GridBagLayout());
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.createComponents();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Realizar una enum para determinar si vamos a arreglar un ejercicio o el estado de un ejercicio, swtich case pertinenete primero antes del CRUD
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
	 * Gets the grid constraint.
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

	public JTextField getTfExerciseName() {
		return tfExerciseName;
	}

	public void setTfExerciseName() {
		if(this.getTfExerciseName() == null){
			this.tfExerciseName = new JTextField(JTextField.RIGHT);
			this.add(tfExerciseName, getGridConstraint(1, 1, 2, new Insets(0, 0, 10, 0)));
		}		
	}

	public JComboBox<?> getCbExerciseName() {
		return cbExerciseName;
	}

	public void setCbExerciseName() {
		if(this.getCbExerciseName() == null){
			List<Exercise> tmpExerciseList = mainController.getExercises(FitnessExercise.class);

			this.cbExerciseName = new JComboBox<Exercise>(ConversorUtilitiy.obtainExercises(tmpExerciseList));
			this.add(cbExerciseName, getGridConstraint(1, 1, 1, new Insets(0, 0, 10, 0)));
		}		
	}
	
	public JComboBox<?> getCbMuscles(){
		return this.cbTrainedMuscle;
	}
	
	public void setCbMuscles() {
		if(this.getCbMuscles() == null){
			musclesList = mainController.getMuscles();
			
			this.cbTrainedMuscle = new JComboBox<Muscle>(ConversorUtilitiy.obtainMuscles(musclesList));
			this.add(cbTrainedMuscle, getGridConstraint(1, 1, 3, new Insets(0, 0, 10, 0)));
		}		
	}
	
	private void saveExercise(){
		FitnessExercise tmpFitnessExercise = new FitnessExercise();
		tmpFitnessExercise.setExerciseName(tfExerciseName.getText());
		tmpFitnessExercise.setTrainedMuscle(musclesList.get(cbTrainedMuscle.getSelectedIndex()));
		
		this.mainController.saveExercise(tmpFitnessExercise);
	}
	
	private void modifyExercise(){
		System.out.println("updating");
		
		mainController.updateExercise((Exercise) cbExerciseName.getSelectedItem());
	}
	
	private void removeExercise(){
		System.out.println("removing");
	}
	
}
