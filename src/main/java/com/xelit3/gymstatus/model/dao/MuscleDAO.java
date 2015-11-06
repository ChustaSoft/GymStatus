package com.xelit3.gymstatus.model.dao;

import java.util.List;

import org.hibernate.Query;

import com.xelit3.gymstatus.model.dto.Muscle;

/**
 * DAO layer for Muscle
 */
public class MuscleDAO extends GenericGymStatDAO {
	
	/**
	 * Gets the muscles.
	 *
	 * @return the muscles
	 */
	public List<Muscle> getMuscles(){
		this.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Muscle");
		
		@SuppressWarnings("unchecked")
		List<Muscle> tmpMuscles = (List<Muscle>) query.list();
		
		this.closeSession();
		
		return tmpMuscles;		
	}	
}
