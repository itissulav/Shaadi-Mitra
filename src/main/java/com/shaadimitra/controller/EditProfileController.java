package com.shaadimitra.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.shaadimitra.model.PartnerModel;
import com.shaadimitra.service.EditProfileService;
import com.shaadimitra.service.RegisterService;
import com.shaadimitra.util.PasswordUtil;
import com.shaadimitra.util.RedirectionUtil;
import com.shaadimitra.util.SessionUtil;

/**
 * Servlet implementation class EditProfileController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/editprofile" })
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EditProfileService editprofileService;
		
	@Override
	public void init() throws ServletException {
		this.editprofileService = new EditProfileService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/editprofile.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String username = SessionUtil.getAttribute(req, "username").toString();
			System.out.println("editprofile doPost username = " + username);
			
			PartnerModel partnerModel = extractPartnerModel(req, resp);
			Boolean isAdded = editprofileService.updatePartner(partnerModel, username);
			System.out.println("isAdded = " + isAdded);
			
			if (isAdded == null) {
				req.setAttribute("error", "Our service is under maintainance. Please try again later");
			} else if (!isAdded) {
				handleError(req, resp, "Could not register your account. Please try again later!");
			} else {
				req.setAttribute("success", "Your account was created!");
				SessionUtil.setAttribute(req, "username", partnerModel.getUsername());
			}
		} catch (Exception e) {
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			System.out.println("doPost got nothing");
			e.printStackTrace(); // Log the exception
		}
		
		
	
	}
	
	
	private PartnerModel extractPartnerModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("userName");
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String number = req.getParameter("number");
		int salary = Integer.parseInt(req.getParameter("salary"));
		String profession = req.getParameter("profession");
		String religion = req.getParameter("religion");

		System.out.println("extractPartnerModel username = " + username);
				

		return new PartnerModel(firstName, lastName, gender, salary, religion, profession, email, number, username, dob);
	}
	
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("firstName", req.getParameter("firstName"));
		req.setAttribute("lastName", req.getParameter("lastName"));
		req.setAttribute("username", req.getParameter("userName"));
		req.setAttribute("dob", req.getParameter("dob"));
		req.setAttribute("gender", req.getParameter("gender"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("number", req.getParameter("phoneNumber"));
		req.getRequestDispatcher("/WEB-INF/pages/editprofile.jsp").forward(req, resp);
	}

}
