//$Id$
package com.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.object.Image;
import com.service.ImageCRUD;

@Controller
public class ImageControl {

	@RequestMapping(value = "/imageservice", method = RequestMethod.POST)
	@ResponseBody
	public void userphoto(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {

		System.out.println("Inside new image upload");

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;

		Set set = multipartRequest.getFileMap().entrySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			String fileName = (String) me.getKey();
			MultipartFile multipartFile = (MultipartFile) me.getValue();

			String encodedbase64 = new String(multipartFile.getBytes());
			String base64 = encodedbase64.substring(encodedbase64.indexOf(",") + 1);
			byte[] imagebytearray = Base64.getDecoder().decode(base64);

			long id = ImageCRUD.create();

			String name = id + ".jpeg";
			String path = "/home/balaji-ztcon37/Desktop/com/fileservice/images/" + name;

			FileOutputStream stream = new FileOutputStream(path);
			stream.write(imagebytearray);
			stream.close();

			Gson g = new Gson();
			String gsonstring = g.toJson("http://localhost:8080/FileServer/imageservice/" + id);

			res.setContentType("application/json");
			res.getWriter().write(gsonstring);
			return;
		}
	}
	
	@RequestMapping(value = "/imageservice/{imageid}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable("imageid") long imageid, HttpServletResponse response) throws IOException {

		Image i = ImageCRUD.read(imageid);
		String path = i.getPath();

		InputStream is = new BufferedInputStream(new FileInputStream(path));

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(is, response.getOutputStream());
	}
}
