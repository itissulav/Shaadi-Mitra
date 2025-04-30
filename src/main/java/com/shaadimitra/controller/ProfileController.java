package com.shaadimitra.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shaadimitra.model.PartnerModel;
import com.shaadimitra.service.ProfileService;
import com.shaadimitra.util.ImageUtil;
import com.shaadimitra.util.PasswordUtil;
import com.shaadimitra.util.RedirectionUtil;
import com.shaadimitra.util.SessionUtil;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ImageUtil imageUtil;
	ProfileService profileService;
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		list.add("123");
		list.add("1223");
		list.add("1232");
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			PartnerModel partner = extractPartnerModelProfile(req, resp);
			profileService.updatePartner(partner);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private PartnerModel extractPartnerModelProfile(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Part image = req.getPart("image");
		String imageUrl = imageUtil.getImageNameFromPart(image);
		String username = (String) SessionUtil.getAttribute(req, "username"); 
		String password = (String) SessionUtil.getAttribute(req, "password");
		

		return new PartnerModel(username, password, imageUrl);
	}

}
