package com.xelit3.gymstatus.control;

import java.util.List;

import com.xelit3.gymstatus.control.settings.AppSettings;
import com.xelit3.gymstatus.model.dao.CardioExerciseDAOImpl;
import com.xelit3.gymstatus.model.dao.ExerciseDAO;
import com.xelit3.gymstatus.model.dao.FitnessExerciseDAOImpl;
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.test.TestApp;
import com.xelit3.gymstatus.view.MainWindow;


public class Controller {
	MainWindow view;
	ExerciseDAO exerciseDao;
		
	public Controller(){}
	
	public void startApp(){
		//TODO Future implementation, take advantage of this to create a multilanguage application
		AppSettings settings = AppSettings.getInstance();
		System.out.println("DONE - Settings loaded:\n" + settings.getUsername() + "\n" + settings.getLanguage());
						
		view = new MainWindow(this);
		view.setVisible(true);		
	}
	
	public boolean saveFitnessExercise(){
		return true;
	}
	
	public List<Exercise> getExercises(Class<?> exerciseClass){
		switch(exerciseClass.getSimpleName()){
		
		case "CardioExercise":
			exerciseDao = new CardioExerciseDAOImpl();
			break;
			
		case "FitnessExercise":
			exerciseDao = new FitnessExerciseDAOImpl();
			break;
		
		}
		
		return exerciseDao.getExercises();		
	}

	public void testApp() {
		TestApp test = new TestApp();
		test.testMethod();
	}
}
