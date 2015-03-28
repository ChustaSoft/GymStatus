package com.xelit3.gymstatus.view.exercises;

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
import javax.swing.SpringLayout;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.model.dto.FitnessExercise;
import com.xelit3.gymstatus.model.dto.Muscle;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class FitnessExercisePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblExerciseName, lblTrainedMuscle;
	private JTextField tfExerciseName;
	private JComboBox cbExerciseName;
	private JComboBox cbTrainedMuscle;
	private JRadioButton rbCreate, rbModify, rbDelete;
	private JButton btnSave;
	
	private Controller mainController = new Controller();

	private List<Muscle> musclesList;
	
	public FitnessExercisePanel() {
		this.createComponents();		
	}
	
	private void createComponents(){
				
		ButtonGroup bgCrud = new ButtonGroup();
			
		rbCreate = new JRadioButton("Create");
		rbCreate.setActionCommand("setCreateForm");
		rbCreate.addActionListener(this);
		setLayout(new GridLayout(4, 2, 0, 0));
		rbCreate.setSelected(true);
		add(rbCreate);
		
		rbModify = new JRadioButton("Modify");
		rbModify.setActionCommand("setModifyForm");
		rbModify.addActionListener(this);
		add(rbModify);
		
		rbDelete = new JRadioButton("Delete");
		rbDelete.setActionCommand("setDeleteForm");
		rbDelete.addActionListener(this);
		add(rbDelete);
		
		bgCrud.add(rbCreate);
		bgCrud.add(rbModify);
		bgCrud.add(rbDelete);
		
				
		this.lblExerciseName = new JLabel("Exercise name");
		add(lblExerciseName);
		
		this.setTfExerciseName();
		
		this.lblTrainedMuscle = new JLabel("Trained muscle");
		add(lblTrainedMuscle);
		
		setUpMuscles();
		
		this.btnSave = new JButton("Save exercise");
		btnSave.setActionCommand("createExercise");
		btnSave.addActionListener(this);
		add(btnSave);	
		
		//TODO JSplttedPane. Crear panel controles arriba y JPanel abajo, el panel de abajo sera un GridLayout
		
	}

	private void setUpMuscles() {
		musclesList = mainController.getMuscles();
		
		String [] tmpMusclesStr = new String[musclesList.size()];
		
		for(int i= 0; i< musclesList.size(); i++){
			tmpMusclesStr[i] = musclesList.get(i).getMusclename();
		}		
				
		this.cbTrainedMuscle = new JComboBox(tmpMusclesStr);
		add(cbTrainedMuscle);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Realizar una enum para determinar si vamos a arreglar un ejercicio o el estado de un ejercicio, swtich case pertinenete primero antes del CRUD
		switch(e.getActionCommand()){
			case "setCreateForm":
				if(this.getTfExerciseName() == null)
					this.setTfExerciseName();				
				btnSave.setActionCommand("createExercise");
				break;
				
			case "setModifyForm":
				if(this.getCbExerciseName() == null)
					this.setCbExerciseName();
				btnSave.setActionCommand("modifyExercise");
				break;
				
			case "setDeleteForm":
				btnSave.setActionCommand("removeExercise");
				break;
				
			case "createExercise":
				saveExercise();
				break;
				
			case "modifyExercise":
				System.out.println("updating");
				break;
				
			case "removeExercise":
				System.out.println("removing");
				break;
		}
		
	}
	
	private void saveExercise(){
		FitnessExercise tmpFitnessExercise = new FitnessExercise();
		tmpFitnessExercise.setExerciseName(tfExerciseName.getText());
		tmpFitnessExercise.setTrainedMuscle(musclesList.get(cbTrainedMuscle.getSelectedIndex()));
		
		this.mainController.saveExercise(tmpFitnessExercise);
	}
	
	private void modifyExercise(){
		
	}
	
	private void removeExercise(){
		
	}

	public JTextField getTfExerciseName() {
		return tfExerciseName;
	}

	public void setTfExerciseName() {
		if(this.getCbExerciseName() != null)
			this.remove(cbExerciseName);
		
		this.tfExerciseName = new JTextField();
		tfExerciseName.setHorizontalAlignment(JTextField.RIGHT);
		tfExerciseName.setColumns(10);
		
		add(tfExerciseName);
	}

	public JComboBox getCbExerciseName() {
		return cbExerciseName;
	}

	public void setCbExerciseName() {
		if(this.getTfExerciseName() != null)
			this.remove(tfExerciseName);
	}
	
	
}
