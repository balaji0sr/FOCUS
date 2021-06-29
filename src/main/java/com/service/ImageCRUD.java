//$Id$
package com.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.object.Image;

public class ImageCRUD {

	public static Image read(long imageid) {

		Session ses = Hibernate.getSessionFactory().openSession();

		Criteria criteria = ses.createCriteria(Image.class);
		Image i = (Image) criteria.add(Restrictions.eq("imageid", imageid)).uniqueResult();

		return i;
	}

	public static long read() {

		Session ses = Hibernate.getSessionFactory().openSession();

		Criteria criteria = ses.createCriteria(Image.class);
		criteria.addOrder(Order.desc("imageid"));

		Image i = (Image) criteria.setMaxResults(1).uniqueResult();

		long id = i.getImageid() + 1;

		return id;
	}

	public static long create() {
		long updatetime = System.currentTimeMillis();

		Session ses = Hibernate.getSessionFactory().openSession();
		Transaction t = ses.beginTransaction();
		
		Image i = new Image();
		i.setUpdatetime(updatetime);
		ses.save(i);

		i.setPath("/home/balaji-ztcon37/Desktop/com/fileservice/images/" + i.getImageid() + ".jpeg");
		ses.update(i);
		t.commit();
		return i.getImageid();
	}
}
