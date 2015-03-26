package com.xelit3.gymstatus.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.xelit3.gymstatus.control.Controller;
import com.xelit3.gymstatus.view.exercises.ExerciseCreationPanel;
import com.xelit3.gymstatus.view.exercises.ExerciseCreationPanel.ExerciseCreationType;
import com.xelit3.gymstatus.view.exercises.ExerciseListPanel;
import com.xelit3.gymstatus.view.routines.RoutineCreationPanel;
import com.xelit3.gymstatus.view.routines.TableRoutines;
import com.xelit3.gymstatus.view.routines.TableRoutinesModel;

public class MainWindow extends JFrame implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	private Controller controller;
	
	public MainWindow(Controller controller){
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		setResizable(false);
//		setUndecorated(true);
//		setRootPane(this.createRootPane());
//		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		buildMenu();					
	}
	
	private void buildMenu(){
		JMenuBar menubar = new JMenuBar();
		JMenu menu;
		JMenuItem mitem;
		
		//Application menu
		menu = new JMenu("Application");
		
		mitem = new JMenuItem("Exit");
		mitem.setActionCommand("exit");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		mitem = new JMenuItem("Abount us");
		mitem.setActionCommand("aboutUs");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		menubar.add(menu);
		
		//Exercise menu
		menu = new JMenu("Exercise");
		
		mitem = new JMenuItem("List Exercise");
		mitem.setActionCommand("listExercises");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		mitem = new JMenuItem("Manage Exercises");
		mitem.setActionCommand("manageExercises");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		mitem = new JMenuItem("Manage Exercise status");
		mitem.setActionCommand("manageExercisesStatus");
		mitem.addActionListener(this);
		menu.add(mitem);
		
		menubar.add(menu);
		
		//Routine menu
		menu = new JMenu("Routine");
				
		mitem = new JMenuItem("List Routines");
		mitem.setActionCommand("listRoutines");
		mitem.addActionListener(this);
		menu.add(mitem);
				
		mitem = new JMenuItem("Manage Routines");
		mitem.setActionCommand("manageRoutines");
		mitem.addActionListener(this);
		menu.add(mitem);
				
		menubar.add(menu);
		
		this.setJMenuBar(menubar);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//TODO Aprovechar metodo update
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		ExerciseCreationPanel exercisePanel;
		switch(event.getActionCommand()){
		
			case "exit":
				System.exit(0);
				break;
				
			case "aboutUs":
				this.showMsg("Xavi Rueda\nChustaSoft\nGymStatus App\n2014-2015");
				break;
				
			case "listExercises":
				ExerciseListPanel listPanel = new ExerciseListPanel();
				setContentPane(listPanel);
				listPanel.updateUI();
				break;
				
			case "manageExercises":
				exercisePanel = new ExerciseCreationPanel(ExerciseCreationType.CREATE_EXERCISE);	
				setContentPane(exercisePanel);
				exercisePanel.updateUI();		
				break;
				
			case "manageExercisesStatus":
				exercisePanel = new ExerciseCreationPanel(ExerciseCreationType.SET_EXERCISE_STATUS);	
				setContentPane(exercisePanel);
				exercisePanel.updateUI();	
				break;
				
			case "listRoutines":
				TableRoutinesModel model = new TableRoutinesModel();
				TableRoutines tableRoutines = new TableRoutines(model);
				JScrollPane scrollPane = new JScrollPane(tableRoutines);
//				scrollPane.setViewportView(tableRoutines);
				setContentPane(scrollPane);
				scrollPane.updateUI();
				break;
				
			case "manageRoutines":
				RoutineCreationPanel routinePanel = new RoutineCreationPanel();
				setContentPane(routinePanel);
				routinePanel.updateUI();
				break;
		}
		
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	private void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);	
	}

}
