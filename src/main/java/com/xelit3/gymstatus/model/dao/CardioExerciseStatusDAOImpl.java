package com.xelit3.gymstatus.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.xelit3.gymstatus.model.dto.Exercise;

/**
 * DAO implementation for CardioExerciseStatus
 */
public class CardioExerciseStatusDAOImpl extends ExerciseDAO {

	/* (non-Javadoc)
	 * @see com.xelit3.gymstatus.model.dao.ExerciseDAO#saveExercise(com.xelit3.gymstatus.model.dto.Exercise)
	 */
	@Override
	public boolean saveExercise(Exercise anExercise) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.xelit3.gymstatus.model.dao.ExerciseDAO#updateExercise(com.xelit3.gymstatus.model.dto.Exercise)
	 */
	@Override
	public boolean updateExercise(Exercise anExercise) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.xelit3.gymstatus.model.dao.ExerciseDAO#getExercise(int)
	 */
	@Override
	public Exercise getExercise(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xelit3.gymstatus.model.dao.ExerciseDAO#getExercises()
	 */
	@Override
	public List<Exercise> getExercises() {
		this.openSession();
		ArrayList<Exercise> tmpList = new ArrayList<Exercise>();
		
		Query selectAll = session.createQuery("FROM Exercise WHERE EXERCISE_TYPE='CARDIO_STATUS'");
		
		tmpList = (ArrayList<Exercise>) selectAll.list();
		
		this.closeSession();
		
		return tmpList;
	}

	/* (non-Javadoc)
	 * @see com.xelit3.gymstatus.model.dao.ExerciseDAO#deleteExercise(int)
	 */
	@Override
	public boolean deleteExercise(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
