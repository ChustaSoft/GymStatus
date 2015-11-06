package com.xelit3.gymstatus.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Generic DAO abstract class
 */
public abstract class GenericGymStatDAO {
	
	/** The Hibermate session. */
	protected Session session;
	
	/**
	 * Open Hibernate session.
	 */
	protected void openSession(){
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		
		session = sessionFactory.openSession();
	}
	
	/**
	 * Close Hibernate session.
	 */
	protected void closeSession(){
		session.close();
	}

}
