package com.xelit3.gymstatus.model.dao;

import java.util.ArrayList;

import org.hibernate.Query;

import com.xelit3.gymstatus.model.dto.CardioExercise;
import com.xelit3.gymstatus.model.dto.Exercise;

// TODO: Auto-generated Javadoc
/**
 * The Class CardioExerciseDAOImpl.
 */
public class CardioExerciseDAOImpl extends ExerciseDAO {
	
	/* (non-Javadoc)
	 * @see com.xelit3.gymstatus.model.dao.ExerciseDAO#saveExercise(com.xelit3.gymstatus.model.dto.Exercise)
	 */
	@Override
	public boolean saveExercise(Exercise anExercise) {
		try{
			this.openSession();
			
			session.beginTransaction();
			session.persist(anExercise);
	        session.getTransaction().commit();	        
	        
	        this.closeSession();
		} 
		catch(Exception exc){
			return false;
		}
        return true;
	}

	/* (non-Javadoc)
	 * @see com.xelit3.gymstatus.model.dao.ExerciseDAO#updateExercise(com.xelit3.gymstatus.model.dto.Exercise)
	 */
	@Override
	public boolean updateExercise(Exercise anExercise) {
		try{
			this.openSession();
			
			session.beginTransaction();
	        session.update(anExercise);
	        session.getTransaction().commit();
	        
	        this.closeSession();
		}
		catch(Exception exc){
			return false;
		}		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.xelit3.gymstatus.model.dao.ExerciseDAO#getExercise(int)
	 */
	@Override
	public Exercise getExercise(int id) {
		this.openSession();
		Exercise tmpExercise = (Exercise) session.get(Exercise.class, id);
		this.closeSession();
		return tmpExercise;
	}

	/* (non-Javadoc)
	 * @see com.xelit3.gymstatus.model.dao.ExerciseDAO#getExercises()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Exercise> getExercises() {
		this.openSession();
		ArrayList<Exercise> tmpList = new ArrayList<Exercise>();
		
		Query selectAll = session.createQuery("FROM Exercise WHERE EXERCISE_TYPE='CARDIO'");
		
		tmpList = (ArrayList<Exercise>) selectAll.list();
		
		this.closeSession();
		
		return tmpList;
	}

	/* (non-Javadoc)
	 * @see com.xelit3.gymstatus.model.dao.ExerciseDAO#deleteExercise(int)
	 */
	@Override
	public boolean deleteExercise(int id) {
		try{
			this.openSession();
			CardioExercise tmpExercise = (CardioExercise) session.get(CardioExercise.class, id);
			session.beginTransaction();
			session.delete(tmpExercise);
			session.getTransaction().commit();
			this.closeSession();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}		
	}	

}
