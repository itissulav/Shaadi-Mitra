package com.shaadimitra.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.shaadimitra.config.DbConfig;
import com.shaadimitra.model.PartnerModel;

public class RegisterService {
	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public Boolean addPartner(PartnerModel partnerModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String insertQuery = "INSERT INTO partners (first_name, last_name, gender, salary, religion, profession, email, number, username, dob, password) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {

			// Insert student details
			insertStmt.setString(1, partnerModel.getFirst_name());
			insertStmt.setString(2, partnerModel.getLast_name());
			insertStmt.setString(3, partnerModel.getGender());
			insertStmt.setString(4, "0"); // salary as string for now, or 0
			insertStmt.setString(5, "Not Provided");
			insertStmt.setString(6, "Not Provided");
			insertStmt.setString(7, partnerModel.getEmail());
			insertStmt.setString(8, partnerModel.getNumber());
			insertStmt.setString(9, partnerModel.getUsername());
			insertStmt.setDate(10, Date.valueOf(partnerModel.getDob()));
			insertStmt.setString(11, partnerModel.getPassword());



			return insertStmt.executeUpdate() > 0;
			
		
		} catch (SQLException e) {
			System.err.println("Error during student registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}