//$Id$
package com.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.object.Image;
import com.service.ImageCRUD;

@Controller
public class ImageDownload {

	@RequestMapping(value = "/imageservice/{imageid}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable("imageid") long imageid, HttpServletResponse response) throws IOException {

		Image i = ImageCRUD.read(imageid);
		String path = i.getPath();

		InputStream is = new BufferedInputStream(new FileInputStream(path));

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(is, response.getOutputStream());
	}
}
