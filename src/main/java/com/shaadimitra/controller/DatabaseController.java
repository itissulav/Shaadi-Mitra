package com.shaadimitra.controller;

import com.shaadimitra.model.PartnerModel;
import com.shaadimitra.service.DatabaseService;
import com.shaadimitra.service.ValidationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/database")
public class DatabaseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseService partnerService = new DatabaseService();
    private ValidationService validationService = new ValidationService();
    Boolean reset = true;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println(reset);
    	if (reset) {
	        List<PartnerModel> partners = partnerService.getAllPartners();
	        
	        request.setAttribute("partners", partners);
    	}
    	request.getRequestDispatcher("/WEB-INF/pages/database.jsp").forward(request, response);
    }
    
    protected void doPost (HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException {
    
    	String action = request.getParameter("action");
    	
    	if ("search".equals(action)) {
    		String username = request.getParameter("search");
    		searchPartner(request, response, username);	
    		reset = false;
    	}else if ("reset".equals(action)) {
    		reset = true;
    		response.sendRedirect("database");
    	}else {
    		reset = true;
    		
    		PartnerModel partner = extractPartnerDetails(request, response);
    		if("add".equals(action)) {
    			addPartner(request, response, partner);
    			return;
    		}else if ("delete".equals(action)) {
    			deletePartner(request, response, partner);
    			return;
    		}else if ("save".equals(action)) {
    			updatePartner(request, response, partner);
    			return;
    		}else {
    			handleError(request, response, "There was an unexpected Error");
    		}
    	}
    	
    }
    
    private void updatePartner(HttpServletRequest request, HttpServletResponse response, PartnerModel partner) throws ServletException, IOException {
    	Boolean isUpdated;
    	
    	isUpdated = partnerService.savePartnerDetails(partner);
    	
    	if (isUpdated == null) {
    		handleError(request, response, "There was an unexpected Error");
    		return;
    	} else if (!isUpdated) {
    		handleError(request, response, "Could not update User");
    		return;
    	} else {
    		handleSuccess(request, response, "Successfully Updated!", "/WEB-INF/pages/database.jsp");
    		return;
    	}
	}

	private void deletePartner(HttpServletRequest request, HttpServletResponse response, PartnerModel partner) throws ServletException, IOException {
    	Boolean isDeleted;
    	
    	isDeleted = partnerService.deletePartner(partner);
    	
    	if (isDeleted == null) {
    		handleError(request, response, "There was an unexpected Error");
    	}else if (!isDeleted) {
    		handleError(request, response, "Could not delete User");
    	}else {
    		handleSuccess(request, response, "Successfully Deleted!", "/WEB-INF/pages/database.jsp");
    	}
    	return;
	}

	private void addPartner(HttpServletRequest request, HttpServletResponse response, PartnerModel partner) throws ServletException, IOException {
		Boolean isAdded;
		Boolean duplicateUsername;
		
		duplicateUsername = validationService.checkUsername(partner);
		
		if(duplicateUsername == null) {
			handleError(request, response, "There was an unexpected error");
		}else if (duplicateUsername) {
			handleError(request, response, "Username already Exists");
		}else {
			isAdded = partnerService.addPartner(partner);
			if (isAdded == null) {
				handleError(request, response, "There was an unexpected error");
			}else if (!isAdded) {
				handleError(request, response, "User could not be added");
			}else {
				handleSuccess(request, response, "Successfully Added!", "/WEB-INF/pages/database.jsp");
			}	
		}
		return;
	}

	private void searchPartner(HttpServletRequest request, HttpServletResponse response, String username) throws ServletException, IOException {
    	List<PartnerModel> partners;
    	partners = partnerService.searchPartner(username);
    	
    	if (partners == null) {
    		handleError(request, response, "Server under maintainance");
    		return;
    	} else if (partners.isEmpty()) {
    		handleError(request, response, "User not found");
    		return;
		} else {
			request.setAttribute("partners", partners);
			request.getRequestDispatcher("/WEB-INF/pages/database.jsp").forward(request, response);
			return;
		}
    }
  
    
    private PartnerModel extractPartnerDetails(HttpServletRequest request, HttpServletResponse response) {
    	String partner_id = request.getParameter("partner_id");
    	String first_name = request.getParameter("first_name");
    	String last_name = request.getParameter("last_name");
    	String gender = request.getParameter("gender");
    	int salary = Integer.parseInt(request.getParameter("salary"));
    	String religion = request.getParameter("religion");
    	String profession = request.getParameter("profession");
    	String email = request.getParameter("email");
    	String number = request.getParameter("number");
    	String username = request.getParameter("username");
    	LocalDate dob = LocalDate.parse(request.getParameter("dob"));
    	String password = request.getParameter("password");
    	
    	return new PartnerModel(partner_id, first_name, last_name, gender, salary, religion, profession, email, number, username, dob, password);
    }
    
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
	    List<PartnerModel> partners = partnerService.getAllPartners();
	    req.setAttribute("partners", partners);
		
		req.setAttribute("error", message);
		req.setAttribute("firstName", req.getParameter("firstName"));
		req.setAttribute("lastName", req.getParameter("lastName"));
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("dob", req.getParameter("dob"));
		req.setAttribute("gender", req.getParameter("gender"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("number", req.getParameter("phoneNumber"));
		req.getRequestDispatcher("/WEB-INF/pages/database.jsp").forward(req, resp);
	}
	
	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
	    List<PartnerModel> partners = partnerService.getAllPartners();
	    req.setAttribute("partners", partners);
	    
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}
}
