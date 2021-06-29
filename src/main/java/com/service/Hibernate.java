//$Id$
package com.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.object.Attachment;
import com.object.Image;

public class Hibernate {
	

		public static SessionFactory getSessionFactory() {

			Configuration config = new Configuration().configure().addAnnotatedClass(Image.class);

			ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();

			SessionFactory sf = config.buildSessionFactory(reg);
			return sf;
		}
		
		public static SessionFactory getSessionFactoryOfAttachment() {

			Configuration config = new Configuration().configure().addAnnotatedClass(Attachment.class);

			ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();

			SessionFactory sf = config.buildSessionFactory(reg);
			return sf;
		}

}
