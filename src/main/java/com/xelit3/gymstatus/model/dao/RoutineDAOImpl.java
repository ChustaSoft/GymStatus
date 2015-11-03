package com.xelit3.gymstatus.model.dao;

import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.Routine;

// TODO: Auto-generated Javadoc
/**
 * DAO layer for Routine bean
 * TODO: Test class.
 */
public class RoutineDAOImpl extends GenericGymStatDAO {
	
	/**
	 * Save routine.
	 *
	 * @param aRoutine the a routine
	 * @return true, if successful
	 */
	public boolean saveRoutine(Routine aRoutine){
		try{
			this.openSession();
			
			session.beginTransaction();
	        session.save(aRoutine);
	        session.getTransaction().commit();
	        
	        this.closeSession();
		}
		catch(Exception exc){
			return false;
		}		
		return true;
	}
	
	/**
	 * Modify routine.
	 *
	 * @param aRoutine the a routine
	 * @return true, if successful
	 */
	public boolean modifyRoutine(Routine aRoutine){
		try{
			this.openSession();
			
			session.beginTransaction();
	        session.update(aRoutine);
	        session.getTransaction().commit();
	        
	        this.closeSession();
		}
		catch(Exception exc){
			return false;
		}		
		return true;
	}
	
	/**
	 * Removes the routine.
	 *
	 * @param aRoutine the a routine
	 * @return true, if successful
	 */
	public boolean removeRoutine(Routine aRoutine){
		try {
			this.openSession();
			
			session.beginTransaction();
			session.delete(aRoutine);
			session.getTransaction().commit();
			
			this.getClass();
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Adds an Exercise status to routine.
	 *
	 * @param aRoutine the a routine
	 * @param anExercise the exercise to include
	 * @return true, if successful
	 */
	public boolean addExerciseStatusToRoutine(Routine aRoutine, Exercise anExercise){		
		try {
			this.openSession();
			
			Routine tmpRoutine = (Routine) session.get(Routine.class, aRoutine.getId());
			tmpRoutine.getExercises().add(anExercise);
			session.beginTransaction();
			session.persist(tmpRoutine);
			session.getTransaction().commit();
			
			this.closeSession();
		} 
		catch (Exception e) {
			return false;
		}
			
		return true;
	}
	
	/**
	 * Removes an Exercise status from routine.
	 *
	 * @param aRoutine the a routine
	 * @param anExercise the an exercise
	 * @return true, if successful
	 */
	public boolean removeExerciseFromRoutine(Routine aRoutine, Exercise anExercise){
		try {
			this.openSession();
			
			Routine tmpRoutine = (Routine) session.get(Routine.class, aRoutine.getId());
			tmpRoutine.getExercises().remove(anExercise);
			session.beginTransaction();
			session.persist(tmpRoutine);
			session.getTransaction().commit();
			
			this.closeSession();
		} 
		catch (Exception e) {
			return false;
		}
			
		return true;
	}

}
