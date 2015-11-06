package com.xelit3.gymstatus.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.Routine;

/**
 * DAO layer implementation for Routine
 */
public class RoutineDAOImpl extends GenericGymStatDAO {
	
	public List<Routine> getRoutines() {
		this.openSession();
		ArrayList<Routine> tmpList = new ArrayList<Routine>();
		
		Query selectAll = session.createQuery("FROM Routine");
		
		tmpList = (ArrayList<Routine>) selectAll.list();
		
		this.closeSession();
		
		return tmpList;
	}
	
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
			for(Exercise e : aRoutine.getExercises()){
				session.delete(e);
			}			
			session.delete(aRoutine);
			session.getTransaction().commit();
			
			this.getClass();
		} catch (Exception e) {
			e.printStackTrace();
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
