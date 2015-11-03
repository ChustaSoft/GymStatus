package com.xelit3.gymstatus.model.dao;

import java.util.List;

import com.xelit3.gymstatus.model.dto.Exercise;

// TODO: Auto-generated Javadoc
/**
 * The Class ExerciseDAO.
 */
public abstract class ExerciseDAO extends GenericGymStatDAO {
	
	/**
	 * Save exercise.
	 *
	 * @param anExercise the an exercise
	 * @return true, if successful
	 */
	public abstract boolean saveExercise(Exercise anExercise);
	
	/**
	 * Update exercise.
	 *
	 * @param anExercise the an exercise
	 * @return true, if successful
	 */
	public abstract boolean updateExercise(Exercise anExercise);
	
	/**
	 * Gets the exercise.
	 *
	 * @param id the id
	 * @return the exercise
	 */
	public abstract Exercise getExercise(int id);
	
	/**
	 * Gets the exercises.
	 *
	 * @return the exercises
	 */
	public abstract List<Exercise> getExercises();
	
	/**
	 * Delete exercise.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public abstract boolean deleteExercise(int id);		
	
}
