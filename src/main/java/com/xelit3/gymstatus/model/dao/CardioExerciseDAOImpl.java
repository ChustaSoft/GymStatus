package com.xelit3.gymstatus.model.dao;

import java.util.ArrayList;

import org.hibernate.Query;

import com.xelit3.gymstatus.model.dto.Exercise;

public class CardioExerciseDAOImpl extends ExerciseDAO {
	
	@Override
	public boolean saveExercise(Exercise e) {
		try{
			openSession();
			
			session.persist(e);
	        session.getTransaction().commit();
	        session.close();
	        
	        closeSession();
		} 
		catch(Exception exc){
			return false;
		}
        return true;
	}

	@Override
	public boolean updateExercise(int id, Exercise newExercise) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Exercise getExercise(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Exercise> getExercises() {
		this.openSession();
		ArrayList<Exercise> tmpList = new ArrayList<Exercise>();
		
		Query selectAll = session.createQuery("FROM Exercise WHERE EXERCISE_TYPE='CARDIO'");
		
		tmpList = (ArrayList<Exercise>) selectAll.list();
		
		return tmpList;
	}

	@Override
	public boolean deleteExercise(int id) {
		// TODO Auto-generated method stub
		return false;
	}	

}
