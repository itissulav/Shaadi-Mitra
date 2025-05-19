package com.shaadimitra.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shaadimitra.config.DbConfig;
import com.shaadimitra.model.PartnerModel;

public class ValidationService {
	private Connection dbConn;
	
	public ValidationService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public Boolean checkUsername(PartnerModel partner) {
	    String usernameCheckQuery = "SELECT * FROM partners WHERE username = ?";
	    
	    try (PreparedStatement stmt = dbConn.prepareStatement(usernameCheckQuery)) {
	        stmt.setString(1, partner.getUsername());
	        ResultSet rs = stmt.executeQuery();

	        // Return true if a record was found
	        return rs.next(); 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null; 
	    }
	}
}
