package com.xelit3.gymstatus.model.dao;

import java.util.List;
import java.util.Observable;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.xelit3.gymstatus.model.dto.Muscle;

public class MuscleDAO extends Observable {
	
	protected Session session;
			
	protected void openSession(){
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		
		session = sessionFactory.openSession();
	}
	
	protected void closeSession(){
		session.close();
	}
	
	public List<Muscle> getMuscles(){
		this.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Muscle");
		
		List<Muscle> tmpMuscles = (List<Muscle>) query.list();
		
		return tmpMuscles;		
	}	
}
