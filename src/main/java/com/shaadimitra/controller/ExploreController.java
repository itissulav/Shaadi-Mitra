package com.shaadimitra.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.shaadimitra.model.PartnerModel;
import com.shaadimitra.service.ExploreService;
import com.shaadimitra.util.SessionUtil;

/**
 * Servlet implementation class ExploreController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/explore" })
public class ExploreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ExploreService exploreService = new ExploreService();
	       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
        String username = (String) SessionUtil.getAttribute(request, "username");
        List<PartnerModel> partners = exploreService.getAllPartners(username);
        
        HttpSession session = request.getSession();
        session.setAttribute("partnerList", partners);
        if (session.getAttribute("partnerIndex") == null) {
            session.setAttribute("partnerIndex", 0);
        }
		request.getRequestDispatcher("/WEB-INF/pages/explore.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
        
		List<PartnerModel> partners = (List<PartnerModel>) session.getAttribute("partnerList");
        Integer index = (Integer) session.getAttribute("partnerIndex");
        String action = request.getParameter("action"); // yes or no
        String username = (String) session.getAttribute("username");
        Boolean matched = false;
        
        PartnerModel partnerOne = exploreService.getPartnerDetail(username);
        PartnerModel partnerTwo = exploreService.getPartnerDetail((partners.get(index).getUsername()));
        
        if ("yes".equals(action)) {
        	matched = exploreService.matchPartner(partnerOne, partnerTwo);
        }
        
        System.out.println("Matched: "+matched);

        if (partners != null && index != null && index < partners.size() - 1) {
            session.setAttribute("partnerIndex", index + 1);
        }
        
        response.sendRedirect("explore");

	}
}
