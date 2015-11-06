package com.xelit3.gymstatus.control;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.xelit3.gymstatus.control.events.EventAction;
import com.xelit3.gymstatus.control.events.EventAction.Action;
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

/**
 * The Class Controller.
 */
public class Controller extends Observable{
	
	/** Main Frame of Application */
	MainWindow view;
	
	/** DAO layer for Exercises and subclasses */
	ExerciseDAO exerciseDao;
	
	/** DAO layer for routinbes */
	RoutineDAOImpl routineDao;
	
	/** DA layer for Muscle, just to get it */
	MuscleDAO muscleDao;
	
	/** Observers list for this controller */
	private List<Observer> observers = new LinkedList<Observer>();
		
	/**
	 * Instantiates a new controller.
	 */
	public Controller(){}
	
	/**
	 * Instantiates a new controller.
	 *
	 * @param anObserver an observer to add to the list
	 */
	public Controller(Observer anObserver){
		this.addObserver(anObserver);
	}
	
	/**
	 * This method starts the Application
	 */
	public void startApp(){
		AppSettings settings = AppSettings.getInstance();
		System.out.println("DONE - Settings loaded:\n" + settings.getUsername() + "\n" + settings.getLanguage());
						
		view = new MainWindow();
		view.setVisible(true);		
	}
	
	/**
	 * Gets the muscles inside Muscles table
	 *
	 * @return the muscles list
	 */
	public List<Muscle> getMuscles() {
		muscleDao = new MuscleDAO();
		return muscleDao.getMuscles();
	}
	
	/**
	 * Gets the exercises for every subclass demanded inside Exercises table
	 *
	 * @param exerciseClass the exercise class demanded (A kind of exercise of maybe a status)
	 * @return the exercises for the demanded class
	 */
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
	
	/**
	 * Save exercise for every subclass demanded inside Exercises table	 * 
	 *
	 * @param anExercise the exercise
	 * @return true, if successful
	 */
	public boolean saveExercise(Exercise anExercise){
		
		if(anExercise instanceof FitnessExercise){
			exerciseDao = new FitnessExerciseDAOImpl();
		}
		else if(anExercise instanceof CardioExercise){
			exerciseDao = new CardioExerciseDAOImpl();
		}
		boolean tmpFlag = exerciseDao.saveExercise(anExercise);
		
		//Nofificamos de la correcta insercion primero a los obervadores en caso de ser correcta
		EventAction tmpAction = new EventAction();
		if(tmpFlag){
			tmpAction.setAction(Action.SAVE);
			tmpAction.setTarget(anExercise);
			notifyObservers(tmpAction);
		}			
		
		return tmpFlag;		
	}
		
	/**
	 * Update exercise.
	 *
	 * @param anExercise the exercise
	 * @return true, if successful
	 */
	public boolean updateExercise(Exercise anExercise){
		if(anExercise instanceof FitnessExercise){
			exerciseDao = new FitnessExerciseDAOImpl();
		}
		else if(anExercise instanceof CardioExercise){
			exerciseDao = new CardioExerciseDAOImpl();
		}
		boolean tmpFlag = exerciseDao.updateExercise(anExercise);
		
		//Nofificamos de la correcta insercion primero a los obervadores en caso de ser correcta
		EventAction tmpAction = new EventAction();
		if(tmpFlag){
			tmpAction.setAction(Action.MODIFY);
			tmpAction.setTarget(anExercise);
			notifyObservers(tmpAction);
		}
				
		return tmpFlag;		
	}
	
	/**
	 * Removes an exercise from database
	 *
	 * @param anExercise the exercise to remove
	 * @return true, if successful
	 */
	public boolean removeExercise(Exercise anExercise){
		if(anExercise instanceof FitnessExercise){
			exerciseDao = new FitnessExerciseDAOImpl();
		}
		else if(anExercise instanceof CardioExercise){
			exerciseDao = new CardioExerciseDAOImpl();
		}
		
		boolean tmpFlag = exerciseDao.deleteExercise(anExercise.getId());
		
		//Nofificamos del borrado primero a los obervadores en caso de ser correcto
		EventAction tmpAction = new EventAction();
		if(tmpFlag){
			tmpAction.setAction(Action.DELETE);
			tmpAction.setTarget(anExercise);
			notifyObservers(tmpAction);
		}
				
		return tmpFlag;		
	}
	
	/**
	 * Gets all routines inside database
	 *
	 * @return the routines list
	 */
	public List<Routine> getAllRoutines(){
		routineDao = new RoutineDAOImpl();
		
		return routineDao.getRoutines();
	}
	
	/**
	 * Creates the routine.
	 *
	 * @param aRoutine the routine to create
	 * @return true, if successful
	 */
	public boolean createRoutine(Routine aRoutine) {
		routineDao = new RoutineDAOImpl();
		
		return routineDao.saveRoutine(aRoutine);		
	}
	
	/**
	 * Modify routine.
	 *
	 * @param aRoutine the routine to modify
	 * @return true, if successful
	 */
	public boolean modifyRoutine(Routine aRoutine) {
		routineDao = new RoutineDAOImpl();
		
		return routineDao.modifyRoutine(aRoutine);
		
	}
			
	/**
	 * Removes a routine.
	 *
	 * @param aRoutine the routine to remove
	 * @return true, if successful
	 */
	public boolean removeRoutine(Routine aRoutine) {
		routineDao = new RoutineDAOImpl();
		
		boolean tmpFlag = routineDao.removeRoutine(aRoutine);
		
		EventAction tmpAction = null;
		if(tmpFlag){
			tmpAction = new EventAction(Action.DELETE, aRoutine);
			notifyObservers(tmpAction);
		}
		
		return tmpFlag;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observable#addObserver(java.util.Observer)
	 */
	public void addObserver(Observer o) {
		observers.add(o);
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void notifyObservers(Object o) {
		if(observers.size() > 0){
			for (Observer obs: observers)
				obs.update(this, o);
		}
	}

	/**
	 * Removes the exercise from routine.
	 *
	 * @param aRoutine the routine to remove the exercise
	 * @param anExercise the exercise to remove from routine
	 */
	public void removeExerciseFromRoutine(Routine aRoutine, Exercise anExercise) {
		routineDao = new RoutineDAOImpl();
		
		boolean tmpFlag = routineDao.removeExerciseFromRoutine(aRoutine, anExercise);
		
		EventAction tmpAction = new EventAction();
		if(tmpFlag){
			tmpAction.setAction(Action.DELETE);
			tmpAction.setTarget(anExercise);
			notifyObservers(tmpAction);
		}
		else{
			tmpAction.setAction(Action.DELETE);
			tmpAction.setTarget(anExercise);
			tmpAction.getErrrors().add("Not removed, not persistent");
			notifyObservers(tmpAction);
		}
	}
	
}
