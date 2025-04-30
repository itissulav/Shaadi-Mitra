package com.shaadimitra.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.shaadimitra.config.DbConfig;
import com.shaadimitra.model.PartnerModel;
import com.shaadimitra.util.SessionUtil;

public class EditProfileService {
	private Connection dbConn;
	
	public EditProfileService() {
			try {
				dbConn = DbConfig.getDbConnection();
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
	}
		
	public Boolean updatePartner(PartnerModel partnerModel, String username) {
		
		String updateQuery = "UPDATE partners SET first_name = ?, last_name = ?, gender = ?, salary = ?, religion = ?, profession = ?, email = ?, number = ?, username = ?, dob = ? WHERE username = ?";

		try (PreparedStatement updateStmt = dbConn.prepareStatement(updateQuery)) {
			

			updateStmt.setString(1, partnerModel.getFirst_name());
			updateStmt.setString(2, partnerModel.getLast_name());
			updateStmt.setString(3, partnerModel.getGender());
			updateStmt.setInt(4, partnerModel.getSalary());
			updateStmt.setString(5, partnerModel.getReligion());
			updateStmt.setString(6, partnerModel.getProfession());
			updateStmt.setString(7, partnerModel.getEmail());
			updateStmt.setString(8, partnerModel.getNumber());
			updateStmt.setString(9, partnerModel.getUsername());
			updateStmt.setDate(10, Date.valueOf(partnerModel.getDob()));
			updateStmt.setString(11, username); // condition: where username = ?
	
			return updateStmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("Error updating partner: " + e.getMessage());
			e.printStackTrace();
			System.out.println("got nothing");
			return null;
		}
	
	}
		
		
	}
	
