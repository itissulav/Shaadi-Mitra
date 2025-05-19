package com.shaadimitra.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

import com.shaadimitra.model.PartnerModel;
import com.shaadimitra.service.ProfileService;
import com.shaadimitra.util.ImageUtil;
import com.shaadimitra.util.SessionUtil;

/**
 * Servlet implementation class ProfileController
 */
@MultipartConfig
@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ImageUtil imageUtil;
	ProfileService profileService;
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.profileService = new ProfileService();
		this.imageUtil = new ImageUtil();
		
		String username = (String) SessionUtil.getAttribute(request, "username");
		
		PartnerModel partner = profileService.getPartnerDetails(username);
		
		List <String> imageList = ImageUtil.getUserImageFileNames(username);
		
		SessionUtil.setAttribute(request, "imageList", imageList);
		
		SessionUtil.setAttribute(request, "userProfileDetails", partner);
 
		request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			if (uploadImage(req)) {
				handleSuccess(req, resp, "Image Uploaded Successfully", "/WEB-INF/pages/profile.jsp");
			} else {
				handleError(req, resp, "Could not upload the image. Please try again later!");
			}
		} catch (IOException | ServletException e) {
			handleError(req, resp, "An error occurred while uploading the image. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
		
	}
	
	private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
		this.imageUtil = new ImageUtil();
		
		Part image = req.getPart("newImage");
		String username = (String) SessionUtil.getAttribute(req, "username");
		System.out.println(req.getServletContext().getRealPath("/"));
		return imageUtil.uploadImageToFeed(image, username);
	}

	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}
	
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("firstName", req.getParameter("firstName"));
		req.setAttribute("lastName", req.getParameter("lastName"));
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("dob", req.getParameter("dob"));
		req.setAttribute("gender", req.getParameter("gender"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("number", req.getParameter("phoneNumber"));
		req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
	}
	
}
