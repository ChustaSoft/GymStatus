package com.xelit3.gymstatus.model.dao;

import java.util.ArrayList;

import com.xelit3.gymstatus.model.dto.Exercise;

public class FitnessExerciseDAOImpl extends ExerciseDAO {

	@Override
	public boolean saveExercise(Exercise e) {
		this.openSession();
		//TODO Error al guardar, 'org.hibernate.exception.LockTimeoutException: could not execute statement'
		session.beginTransaction();
        session.persist(e);
        session.getTransaction().commit();
        
        this.closeSession();
		
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

	@Override
	public ArrayList<Exercise> getExercises() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteExercise(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
