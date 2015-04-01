package com.xelit3.gymstatus.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public abstract class GenericGymStatDAO {
	
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

}
