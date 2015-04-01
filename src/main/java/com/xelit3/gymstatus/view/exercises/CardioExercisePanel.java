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

public class CardioExercisePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private Controller mainController = new Controller();
	
	private JLabel lblExerciseName;
	private JTextField tfExerciseName;
	private JComboBox<Exercise> cbExerciseName;
	private JRadioButton rbCreate, rbModify, rbDelete;
	private JButton btnSave;
	
	public CardioExercisePanel() {
		setLayout(new GridBagLayout());
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.createComponents();
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
		
		this.lblExerciseName = new JLabel("Exercise name");
		this.add(lblExerciseName, getGridConstraint(1, 0, 1, new Insets(0, 0, 10, 25)));
		
		this.setTfExerciseName();
		
		this.btnSave = new JButton("Save exercise");
		btnSave.setActionCommand("createExercise");
		btnSave.addActionListener(this);
		this.add(btnSave, getGridConstraint(1, 1, 2, new Insets(0, 25, 100, 0)));
	}

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
		if(this.getCbExerciseName() != null)
			this.remove(cbExerciseName);
		
		this.cbExerciseName = null;
		this.tfExerciseName = new JTextField(JTextField.RIGHT);
		this.add(tfExerciseName, getGridConstraint(1, 1, 1, new Insets(0, 0, 10, 0)));
	}

	public JComboBox<?> getCbExerciseName() {
		return cbExerciseName;
	}

	public void setCbExerciseName() {
		if(this.getTfExerciseName() != null)
			this.remove(tfExerciseName);
		this.tfExerciseName = null;
		
		List<Exercise> tmpExerciseList = mainController.getExercises(CardioExercise.class);

		this.cbExerciseName = new JComboBox<Exercise>(ConversorUtilitiy.obtainExercises(tmpExerciseList));
		this.add(cbExerciseName, getGridConstraint(1, 1, 1, new Insets(0, 0, 10, 0)));
	}
	
	private void saveExercise(){
		CardioExercise tmpCardioExercise = new CardioExercise();
		tmpCardioExercise.setExerciseName(this.tfExerciseName.getText());
		
		mainController.saveExercise(tmpCardioExercise);
	}
	
	private void modifyExercise(){
		System.out.println("updating");		
		
	}
	
	private void removeExercise(){
		System.out.println("removing");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Realizar una enum para determinar si vamos a arreglar un ejercicio o el estado de un ejercicio, swtich case pertinenete primero antes del CRUD
		switch(e.getActionCommand()){
			case "setCreateForm":
				if(this.getTfExerciseName() == null)
					this.setTfExerciseName();
				btnSave.setText("Save exercise");
				btnSave.setActionCommand("createExercise");
				this.updateUI();
				break;
				
			case "setModifyForm":
				if(this.getCbExerciseName() == null)
					this.setCbExerciseName();
				btnSave.setText("Modify exercise");
				btnSave.setActionCommand("modifyExercise");
				this.updateUI();
				break;
				
			case "setDeleteForm":
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
	
}

