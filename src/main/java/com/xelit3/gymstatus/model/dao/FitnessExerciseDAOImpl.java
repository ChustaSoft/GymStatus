package com.xelit3.gymstatus.model.dao;

import java.util.ArrayList;

import org.hibernate.Query;

import com.xelit3.gymstatus.model.dto.Exercise;

public class FitnessExerciseDAOImpl extends ExerciseDAO {

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
		
		Query selectAll = session.createQuery("FROM Exercise WHERE EXERCISE_TYPE='FITNESS'");
		
		tmpList = (ArrayList<Exercise>) selectAll.list();
		
		this.closeSession();
		
		return tmpList;
	}

	@Override
	public boolean deleteExercise(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
