package com.xelit3.gymstatus.test;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.xelit3.gymstatus.model.dto.CardioExerciseStatus;
import com.xelit3.gymstatus.model.dto.CardioExerciseStatus.CardioExerciseIntensity;
import com.xelit3.gymstatus.model.dto.Exercise;
import com.xelit3.gymstatus.model.dto.FitnessExerciseStatus;
import com.xelit3.gymstatus.model.dto.Muscle;
import com.xelit3.gymstatus.model.dto.Routine;
import com.xelit3.gymstatus.model.dto.Serie;

/**
 * The Class TestApp.
 * Personal class to test some functionalities
 */
public class TestApp {
	
	/**
	 * Test method.
	 */
	public void testMethod() {
		//Iniciamos el archivo de configuracion, y la SessinFactory
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

		//Creamos la sesión, sirivendonos de la SessionFactory
		Session session = sessionFactory.openSession();

		Muscle muscle = new Muscle("triceps");
		
		CardioExerciseStatus ex1 = new CardioExerciseStatus("correr");
		ex1.setIntensity(CardioExerciseIntensity.HIGH);
		ex1.setTime(5000);
		CardioExerciseStatus ex2 = new CardioExerciseStatus("bici");
        ex2.setIntensity(CardioExerciseIntensity.MEDIUM);
		ex2.setTime(3000);
        FitnessExerciseStatus ex3 = new FitnessExerciseStatus("pess banca", muscle);
        Serie ex3_1 = new Serie(12, 20, false);
        Serie ex3_2 = new Serie(10, 24, false);
        Serie ex3_3 = new Serie(10, 28, false);
        Serie ex3_4 = new Serie(10, 30, true);
        ArrayList<Serie> series = new ArrayList<Serie>();
        series.add(ex3_1);
        series.add(ex3_2);
        series.add(ex3_3);
        series.add(ex3_4);
        ex3.setSeries(series);
        
        Routine rutina = new Routine();
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();
        exercises.add(ex1);
        exercises.add(ex2);
        exercises.add(ex3);
        rutina.setExercises(exercises);
        rutina.setRoutineName("monday");
        rutina.setInitDate(new Date(1220227200L * 1000));
        rutina.setFinishDate(new Date(1220227201L * 1000));
                
        session.beginTransaction();
        session.save(muscle);
        session.save(ex1);
        session.save(ex2);
        session.persist(ex3);
        session.persist(rutina);
        session.getTransaction().commit();
        session.close();

	}
}
