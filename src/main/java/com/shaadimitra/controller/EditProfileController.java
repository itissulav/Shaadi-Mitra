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
import com.shaadimitra.service.ValidationService;
import com.shaadimitra.util.PasswordUtil;
import com.shaadimitra.util.RedirectionUtil;
import com.shaadimitra.util.SessionUtil;
import com.shaadimitra.util.ValidationUtil;

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
		
		PartnerModel partner;
		String username = (String) SessionUtil.getAttribute(request, "username");
		
		System.out.println(username);
		partner = editprofileService.getPartnerDetails(username);
		
		SessionUtil.setAttribute(request, "partnerDetails", partner);
		request.getRequestDispatcher("/WEB-INF/pages/editprofile.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		
		if ("discard".equals(action)) {
			resp.sendRedirect("profile");
			return;
		}
		
		try {
			
			String validationMessage = validateRegistrationForm(req);
			
			if (validationMessage != null) {
				handleError(req, resp, validationMessage);
				return;
			}
			String username = SessionUtil.getAttribute(req, "username").toString();
			PartnerModel partnerModel = extractPartnerModel(req, resp);
			
			Boolean isAdded = editprofileService.updatePartner(partnerModel, username);
			
			if (isAdded == null) {
				req.setAttribute("error", "Our service is under maintainance. Please try again later");
			} else if (!isAdded) {
				handleError(req, resp, "Could not register your account. Please try again later!");
			} else {
				req.setAttribute("success", "Your account was updated!");
				handleSuccess(req, resp, "Success", "WEB-INF/pages/profile.jsp");
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
	
	private String validateRegistrationForm(HttpServletRequest req) {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String salary = req.getParameter("salary");
		String profession = req.getParameter("profession");
		String religion = req.getParameter("religion");
		String dobStr = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String number = req.getParameter("number");

		// Check for null or empty fields first
		if (ValidationUtil.isNullOrEmpty(firstName))
			return "First name is required.";
		if (ValidationUtil.isNullOrEmpty(lastName))
			return "Last name is required.";
		if (ValidationUtil.isNullOrEmpty(dobStr))
			return "Date of birth is required.";
		if (ValidationUtil.isNullOrEmpty(gender))
			return "Gender is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(number))
			return "Phone number is required.";

		// Convert date of birth
		LocalDate dob;
		try {
			dob = LocalDate.parse(dobStr);
		} catch (Exception e) {
			return "Invalid date format. Please use YYYY-MM-DD.";
		}

		// Validate fields
		
		if (!ValidationUtil.isAlphabetic(firstName))
			return "Name cannot include numeric characters.";
		if (!ValidationUtil.isAlphabetic(lastName))
			return "Name cannot include numeric characters.";
		if (!ValidationUtil.isValidSalary(salary) && !ValidationUtil.isNullOrEmpty(salary) && !salary.equals("0"))
			return "Salary cannot be alphabetical.";
		if (!ValidationUtil.isAlphabetic(profession) && !ValidationUtil.isNullOrEmpty(profession))
			return "Profession cannot be numeric.";
		if (!ValidationUtil.isAlphabetic(religion) && !ValidationUtil.isNullOrEmpty(religion))
			return "Religion cannot be numeric.";
		if (!ValidationUtil.isValidGender(gender))
			return "Gender must be 'male' or 'female'.";
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPhoneNumber(number))
			return "Phone number must be 10 digits and start with 98.";

		// Check if the date of birth is at least 16 years before today
		if (!ValidationUtil.isAgeAtLeast20(dob))
			return "You must be at least 20 years old.";

		return null; // All validations passed
	}

	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

}
