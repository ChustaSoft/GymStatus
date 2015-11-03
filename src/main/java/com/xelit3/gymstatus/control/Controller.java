package com.xelit3.gymstatus.control;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.xelit3.gymstatus.control.settings.AppSettings;
import com.xelit3.gymstatus.model.dao.CardioExerciseDAOImpl;
import com.xelit3.gymstatus.model.dao.CardioExerciseStatusDAOImpl;
import com.xelit3.gymstatus.model.dao.ExerciseDAO;
import com.xelit3.gymstatus.model.dao.FitnessExerciseDAOImpl;
import com.xelit3.gymstatus.model.dao.FitnessExerciseStatusDAOImpl;
import com.xelit3.gymstatus.model.dao.MuscleDAO;
import com.xelit3.gymstatus.model.dao.RoutineDAOImpl;
import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExercise;
import com.xelit3.gymstatus.model.dto.Muscle;
import com.xelit3.gymstatus.model.dto.Routine;
import com.xelit3.gymstatus.view.MainWindow;


public class Controller extends Observable{
	
	MainWindow view;
	ExerciseDAO exerciseDao;
	RoutineDAOImpl routineDa0;
	MuscleDAO muscleDao;
	
	private List<Observer> observers = new LinkedList<Observer>();
		
	public Controller(){}
	
	public Controller(Observer aObserver){
		this.addObserver(aObserver);
	}
	
	public void startApp(){
		AppSettings settings = AppSettings.getInstance();
		System.out.println("DONE - Settings loaded:\n" + settings.getUsername() + "\n" + settings.getLanguage());
						
		view = new MainWindow(this);
		view.setVisible(true);		
	}
	
	public List<Muscle> getMuscles() {
		muscleDao = new MuscleDAO();
		return muscleDao.getMuscles();
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
	
	public boolean saveExercise(Exercise anExercise){
		
		if(anExercise instanceof FitnessExercise){
			exerciseDao = new FitnessExerciseDAOImpl();
		}
		else if(anExercise instanceof CardioExercise){
			exerciseDao = new CardioExerciseDAOImpl();
		}
		boolean tmpFlag = exerciseDao.saveExercise(anExercise);
		
		//Nofificamos de la correcta insercion primero a los obervadores en caso de ser correcta
		if(tmpFlag)
			notifyObservers(anExercise);
		
		return tmpFlag;		
	}
		
	public boolean updateExercise(Exercise anExercise){
		if(anExercise instanceof FitnessExercise){
			exerciseDao = new FitnessExerciseDAOImpl();
		}
		else if(anExercise instanceof CardioExercise){
			exerciseDao = new CardioExerciseDAOImpl();
		}
		boolean tmpFlag = exerciseDao.updateExercise(anExercise);
		
		//Nofificamos de la correcta insercion primero a los obervadores en caso de ser correcta
		if(tmpFlag)
			notifyObservers(anExercise);
				
		return tmpFlag;		
	}
	
	public boolean removeExercise(Exercise anExercise){
		if(anExercise instanceof FitnessExercise){
			exerciseDao = new FitnessExerciseDAOImpl();
		}
		else if(anExercise instanceof CardioExercise){
			exerciseDao = new CardioExerciseDAOImpl();
		}
		
		return exerciseDao.deleteExercise(anExercise.getId());
	}
	
	public boolean createRoutine(Routine aRoutine) {
		routineDa0 = new RoutineDAOImpl();
		
		return routineDa0.saveRoutine(aRoutine);		
	}
		
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	@Override
	public void notifyObservers(Object o) {
		if(observers.size() > 0){
			for (Observer obs: observers)
				obs.update(this, o);
		}
	}

	
	
}
