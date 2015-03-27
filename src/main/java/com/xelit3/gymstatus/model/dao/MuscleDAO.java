package com.xelit3.gymstatus.model.dao;

import java.util.List;

import org.hibernate.Query;

import com.xelit3.gymstatus.model.dto.Muscle;

public class MuscleDAO extends GenericGymStatDAO {
	
	public List<Muscle> getMuscles(){
		this.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Muscle");
		
		List<Muscle> tmpMuscles = (List<Muscle>) query.list();
		
		this.closeSession();
		
		return tmpMuscles;		
	}	
}
