package com.shaadimitra.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.shaadimitra.model.PartnerMatch;
import com.shaadimitra.service.AdminDashboardService;

/**
 * Servlet implementation class AdminDashboardController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admindashboard" })
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AdminDashboardService dashboardService = new AdminDashboardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int totalUsers = dashboardService.getTotalUsers();
		int totalMatches = dashboardService.getTotalMatchCount();
		List <PartnerMatch> matches = dashboardService.getAllMatches();
		
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalMatches", totalMatches);
		request.setAttribute("matchList", matches);
		
		request.getRequestDispatcher("/WEB-INF/pages/admindashboard.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PartnerMatch match = extractPartnerMatchDetails(request, response);
		
		Boolean isUpdated = dashboardService.updatePartnerMatches(match);
		
		if (isUpdated) {
			handleSuccess(request, response, "Update Successfull!", "/WEB-INF/pages/admindashboard.jsp");
		} else {
			handleError(request, response, "There was an unexpected error!");
		}
		
		response.sendRedirect("admindashboard");
		
	}
	
	private PartnerMatch extractPartnerMatchDetails (HttpServletRequest request, HttpServletResponse response) {
		int partnerOneId = Integer.parseInt(request.getParameter("partnerone_id"));
		int partnerTwoId = Integer.parseInt(request.getParameter("partnertwo_id"));
		int matchPercent = Integer.parseInt(request.getParameter("matchPercent"));
		boolean success = request.getParameter("success") != null;
		String matchStatus = request.getParameter("matchStatus");
		
		return new PartnerMatch(partnerOneId, partnerTwoId, matchPercent, success, matchStatus);
		
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

	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

}
