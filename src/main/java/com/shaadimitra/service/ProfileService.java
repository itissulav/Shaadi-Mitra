package com.shaadimitra.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.shaadimitra.config.DbConfig;
import com.shaadimitra.model.PartnerModel;

public class ProfileService {
	private Connection dbConn;
	
	public ProfileService() {
			try {
				dbConn = DbConfig.getDbConnection();
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
	}
	
	public Boolean updatePartner(PartnerModel partnerModel) {
		
		String updateQuery = "UPDATE partners image1 = ?  WHERE username = ?";

		try (PreparedStatement updateStmt = dbConn.prepareStatement(updateQuery)) {
			
			System.out.println("tried the SQL statement");

			updateStmt.setString(1, partnerModel.getImage1());
			updateStmt.setString(2, partnerModel.getUsername()); // condition: where username = ?

			return updateStmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("Error updating partner: " + e.getMessage());
			e.printStackTrace();
			System.out.println("got nothing");
			return null;
		}
	
	}
		
		
	}