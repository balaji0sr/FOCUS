//$Id$
package com.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.object.Attachment;
import com.service.AttachmentCRUD;

@Controller
public class AttachmentDownload {

	@RequestMapping(value = "/attachmentservice/{attachmentid}", method = RequestMethod.GET, produces = "application/pdf")
	public void getattachments(@PathVariable("attachmentid") long attachmentid, HttpServletResponse response) throws IOException {

		Attachment a = AttachmentCRUD.read(attachmentid);
		String path = a.getAttachmentpath();

		InputStream is = new BufferedInputStream(new FileInputStream(path));
		String contenttype = "";
		
		if(a.getType().equals("jpeg")) contenttype = "image/jpeg";
		if(a.getType().equals("pdf")) contenttype = "application/pdf";

		response.setContentType(contenttype);
		StreamUtils.copy(is, response.getOutputStream());
	}
}
