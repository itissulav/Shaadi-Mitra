package com.shaadimitra.controller;

import java.time.LocalDate;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.shaadimitra.model.PartnerModel;
import com.shaadimitra.service.RegisterService;
import com.shaadimitra.service.ValidationService;
import com.shaadimitra.util.ImageUtil;
import com.shaadimitra.util.PasswordUtil;
import com.shaadimitra.util.RedirectionUtil;
import com.shaadimitra.util.ValidationUtil;

/**
 * @author Sulav Paudel
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/register"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
				maxFileSize = 1024 * 1024 * 10,
				maxRequestSize = 1024 * 1024 * 50)
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RegisterService registerService;
	private RedirectionUtil redirectionUtil;
	private ImageUtil imageUtil;
	private ValidationService validationService;

	@Override
	public void init() throws ServletException {
		this.registerService = new RegisterService();
		this.redirectionUtil = new RedirectionUtil();
		this.imageUtil = new ImageUtil();
		this.validationService = new ValidationService();
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			//Validate and extract student model
//			String validationMessage = validateRegistrationForm(req);
//			if (validationMessage != null) {
//				handleError(req,resp,validationMessage);
//				return;
//			}
			
			
			System.out.println("Testing with 'Tiger12@': " + ValidationUtil.isValidPassword("Tiger12@"));

			String validationMessage = validateRegistrationForm(req);
			
			if (validationMessage != null) {
				handleError(req, resp, validationMessage);
				return;
			}
			
			PartnerModel partnerModel = extractPartnerModel(req, resp);
			Boolean isDuplicateUsername = validationService.checkUsername(partnerModel);
			
			if (isDuplicateUsername) {
				handleError(req,resp, "The Username is Taken Already");
				return;
			}else {
				Boolean isAdded = registerService.addPartner(partnerModel);
				if (isAdded == null) {
					handleError(req, resp, "Our server is under maintenance. Please try again later!");
				} else if (isAdded) {
						try {
							if (uploadImage(req)) {
								handleSuccess(req, resp, "Your account is successfully created!", "/WEB-INF/pages/login.jsp");
							} else {
								handleError(req, resp, "Could not upload the image. Please try again later!");
							}
						} catch (IOException | ServletException e) {
							handleError(req, resp, "An error occurred while uploading the image. Please try again later!");
							e.printStackTrace(); // Log the exception
						}
					} else {
						handleError(req, resp, "Could not register your account. Please try again later!");
					}
			}
		} catch (Exception e) {
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
	}
	
	private String validateRegistrationForm(HttpServletRequest req) {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("userName");
		String dobStr = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String number = req.getParameter("number");
		String password = req.getParameter("password").trim();
		String retypePassword = req.getParameter("confirmpassword");
		
		System.out.println("Password to validate: " + password);


		// Check for null or empty fields first
		if (ValidationUtil.isNullOrEmpty(firstName))
			return "First name is required.";
		if (ValidationUtil.isNullOrEmpty(lastName))
			return "Last name is required.";
		if (ValidationUtil.isNullOrEmpty(username))
			return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(dobStr))
			return "Date of birth is required.";
		if (ValidationUtil.isNullOrEmpty(gender))
			return "Gender is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(number))
			return "Phone number is required.";
		if (ValidationUtil.isNullOrEmpty(password))
			return "Password is required.";
		if (ValidationUtil.isNullOrEmpty(retypePassword))
			return "Please retype the password.";

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
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidGender(gender))
			return "Gender must be 'male' or 'female'.";
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPhoneNumber(number))
			return "Phone number must be 10 digits and start with 98.";
		if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
			return "Passwords do not match.";
		if (!ValidationUtil.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";

		// Check if the date of birth is at least 16 years before today
		if (!ValidationUtil.isAgeAtLeast20(dob))
			return "You must be at least 16 years old to register.";

		return null; // All validations passed
	}
	
	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}
	
	private PartnerModel extractPartnerModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("userName");
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		String number = req.getParameter("number");
		String password = req.getParameter("password").trim();
		
		Part image = req.getPart("profilePicture");
		String imageUrl = imageUtil.getImageNameFromPart(image);
		
		password = PasswordUtil.encrypt(username, password);
		
		if (password == null) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please correct your password & retype-password!",
					RedirectionUtil.registerUrl);
			}

		return new PartnerModel(firstName, lastName, gender, email, number, username, dob, password, imageUrl);
	}
	
	private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
		Part image = req.getPart("profilePicture");
		String username = req.getParameter("userName");
		return imageUtil.uploadImage(image, username);
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
