package com.hibernate.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) 
	{
		try 
		{
			// TRANSIENT OBJECT - HIBERNATE DOES NOT KNOW ABOUT THIS OBJECT
			UserAccount userAccount = new UserAccount();
			userAccount.setUserName("MasterWill");
			
			
			// create a session factory for creating sessions
			SessionFactory sessionFactory = new Configuration().configure("resources/hibernate.cfg.xml")
					.buildSessionFactory();

			// create a session from the SessionFactory
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			// PERSISTENT OBJECT - HIBERNATE KNOWS AND KEEPS TRACK OF THE OBJECT STATES
			session.save(userAccount);
			userAccount.setUserName("UpdatedUser");

			session.getTransaction().commit();
			session.close();
			
			// DETACHED OBJECT - AFTER SESSION IS CLOSED, HIBERNATE LOSES TRACK OF THIS OBJECT
			userAccount.setUserName("UpdatedUser");
			
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			System.out.println("A hibernate exception has been triggered");
		}
	}
}
