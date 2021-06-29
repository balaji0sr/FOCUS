//$Id$
package com.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.object.Attachment;
import com.object.Image;

public class AttachmentCRUD {

	public static Attachment create(long userid , String type) throws IOException {
		long updatetime = System.currentTimeMillis();
		String path = "/home/balaji-ztcon37/Desktop/com/fileservice/" + userid;
		Files.createDirectories(Paths.get(path));

		Session ses = Hibernate.getSessionFactoryOfAttachment().openSession();
		Transaction t = ses.beginTransaction();
		
		Attachment a = new Attachment();
		a.setUpdatetime(updatetime);
		
		a.setAttachmentpath(path + "/" + updatetime + type);
		a.setUserid(userid);
		a.setType(type);
		ses.save(a);
		t.commit();
		return a;
	}

	public static Attachment read(long attachmentid) {
		Session ses = Hibernate.getSessionFactoryOfAttachment().openSession();

		Criteria criteria = ses.createCriteria(Attachment.class);
		Attachment a = (Attachment) criteria.add(Restrictions.eq("attachmentid", attachmentid)).uniqueResult();

		return a;
	}
}
