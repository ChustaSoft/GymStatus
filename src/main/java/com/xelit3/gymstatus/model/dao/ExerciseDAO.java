package com.xelit3.gymstatus.model.dao;

import java.util.List;
import java.util.Observable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.xelit3.gymstatus.model.dto.Exercise;

public abstract class ExerciseDAO extends Observable {
	
	protected Session session;
	
	public abstract boolean saveExercise(Exercise e);
	public abstract boolean updateExercise(int id, Exercise newExercise);
	public abstract Exercise getExercise(int id);
	public abstract List<Exercise> getExercises();
	public abstract boolean deleteExercise(int id);
		
	protected void openSession(){
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		
		session = sessionFactory.openSession();
	}
	
	protected void closeSession(){
		session.close();
	}
}
