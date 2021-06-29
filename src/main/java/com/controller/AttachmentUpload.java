//$Id$
package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.object.Attachment;
import com.service.AttachmentCRUD;

@Controller
public class AttachmentUpload {

	@RequestMapping(value = "/attachmentservice/{userid}/{type}/{size}", method = RequestMethod.POST)
	@ResponseBody
	public void setattachments(@PathVariable("userid") long userid, @PathVariable("type") String type, @PathVariable("size") long size, HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {

		System.out.println("Inside file server Attachment upload");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		Map map = multipartRequest.getFileMap();
		
		Iterator<Map.Entry<String, MultipartFile>> iterator = map.entrySet().iterator();

		Gson g = new Gson();
		JSONObject json = new JSONObject();

		while (iterator.hasNext()) {
			Map.Entry<String, MultipartFile> entry = iterator.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());

			Attachment a = AttachmentCRUD.create(userid , type);

			String path = a.getAttachmentpath();

			FileOutputStream stream = new FileOutputStream(path);
			stream.write(entry.getValue().getBytes());
			stream.close();

			String link = "http://localhost:8080/FileServer/attachmentservice/" + a.getAttachmentid();
			String filename = entry.getKey();
			
			json.put("link", link );
			json.put("filename" , filename);
			json.put("size", size);
		}
		System.out.println("json string ::: " + json.toString());
		
		res.setContentType("application/json;charset=utf-8");
		res.getWriter().write(json.toString());
		return;
	}
}
