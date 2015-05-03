package com.xelit3.gymstatus.control;

import java.util.List;
import java.util.Observable;

import com.xelit3.gymstatus.control.settings.AppSettings;
import com.xelit3.gymstatus.model.dao.CardioExerciseDAOImpl;
import com.xelit3.gymstatus.model.dao.CardioExerciseStatusDAOImpl;
import com.xelit3.gymstatus.model.dao.ExerciseDAO;
import com.xelit3.gymstatus.model.dao.FitnessExerciseDAOImpl;
import com.xelit3.gymstatus.model.dao.FitnessExerciseStatusDAOImpl;
import com.xelit3.gymstatus.model.dao.MuscleDAO;
import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExercise;
import com.xelit3.gymstatus.model.dto.Muscle;
import com.xelit3.gymstatus.test.TestApp;
import com.xelit3.gymstatus.view.MainWindow;


public class Controller extends Observable{
	MainWindow view;
	ExerciseDAO exerciseDao;
	MuscleDAO muscleDao;
		
	public Controller(){}
	
	public void startApp(){
		AppSettings settings = AppSettings.getInstance();
		//TODO Implementación futura, aprovecharemos el singleton para hacer apliación multidioma
		System.out.println("DONE - Settings loaded:\n" + settings.getUsername() + "\n" + settings.getLanguage());
						
		view = new MainWindow(this);
		view.setVisible(true);		
	}
	
	public boolean saveExercise(Exercise anExercise){
		
		if(anExercise instanceof FitnessExercise){
			exerciseDao = new FitnessExerciseDAOImpl();
		}
		else if(anExercise instanceof CardioExercise){
			exerciseDao = new CardioExerciseDAOImpl();
		}
		
		return exerciseDao.saveExercise(anExercise);		
	}
	
	public List<Exercise> getExercises(Class<?> exerciseClass){
		switch(exerciseClass.getSimpleName()){
		
			case "CardioExercise":
				exerciseDao = new CardioExerciseDAOImpl();
				break;
				
			case "FitnessExercise":
				exerciseDao = new FitnessExerciseDAOImpl();
				break;
				
			case "FitnessExerciseStatus":
				exerciseDao = new FitnessExerciseStatusDAOImpl();
				break;
				
			case "CardioExerciseStatus":
				exerciseDao = new CardioExerciseStatusDAOImpl();
		
		}
		
		return exerciseDao.getExercises();		
	}
	
	public boolean updateExercise(Exercise anExercise){
		if(anExercise instanceof FitnessExercise){
			exerciseDao = new FitnessExerciseDAOImpl();
		}
		else if(anExercise instanceof CardioExercise){
			exerciseDao = new CardioExerciseDAOImpl();
		}
		
		return exerciseDao.updateExercise(anExercise);
	}
	
	public List<Muscle> getMuscles() {
		muscleDao = new MuscleDAO();
		return muscleDao.getMuscles();
	}
	
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		super.notifyObservers();
	}
	
	//Testing
	public void testApp() {
		TestApp test = new TestApp();
		test.testMethod();
	}
}
