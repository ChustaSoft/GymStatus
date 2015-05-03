package com.xelit3.gymstatus.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.xelit3.gymstatus.model.dto.Exercise;

public class CardioExerciseStatusDAOImpl extends ExerciseDAO {

	@Override
	public boolean saveExercise(Exercise anExercise) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateExercise(Exercise anExercise) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Exercise getExercise(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exercise> getExercises() {
		this.openSession();
		ArrayList<Exercise> tmpList = new ArrayList<Exercise>();
		
		Query selectAll = session.createQuery("FROM Exercise WHERE EXERCISE_TYPE='CARDIO_STATUS'");
		
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
