package com.shaadimitra.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		
		String updateQuery = "UPDATE partners SET first_name = ?, last_name = ?, gender = ?, salary = ?, religion = ?, profession = ?, email = ?, number = ?, dob = ? WHERE username = ?";

		try (PreparedStatement updateStmt = dbConn.prepareStatement(updateQuery)) {
			
			updateStmt.setString(1, partnerModel.getFirst_name());
			updateStmt.setString(2, partnerModel.getLast_name());
			updateStmt.setString(3, partnerModel.getGender());
			updateStmt.setInt(4, partnerModel.getSalary());
			updateStmt.setString(5, partnerModel.getReligion());
			updateStmt.setString(6, partnerModel.getProfession());
			updateStmt.setString(7, partnerModel.getEmail());
			updateStmt.setString(8, partnerModel.getNumber());
			updateStmt.setDate(9, Date.valueOf(partnerModel.getDob()));
			updateStmt.setString(10, username); // condition: where username = ?
	
			return updateStmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("Error updating partner: " + e.getMessage());
			e.printStackTrace();
			System.out.println("got nothing");
			return null;
		}
	
	}
	
	public PartnerModel getPartnerDetails(String username) {
		   PartnerModel partner = null;
		   String query = "SELECT * FROM partners WHERE username = ?";

		   try(PreparedStatement stmt = dbConn.prepareStatement(query)) {
		        
		        stmt.setString(1, username);
		        ResultSet rs = stmt.executeQuery();

		        if (rs.next()) {
		            partner = new PartnerModel();
		            partner.setFirst_name(rs.getString("first_name"));
		            partner.setLast_name(rs.getString("last_name"));
		            partner.setGender(rs.getString("gender"));
		            partner.setSalary(rs.getInt("salary"));
		            partner.setReligion(rs.getString("religion"));
		            partner.setProfession(rs.getString("profession"));
		            partner.setEmail(rs.getString("email"));
		            partner.setNumber(rs.getString("number"));
		            partner.setUsername(username);
		            partner.setDob((rs.getDate("dob")).toLocalDate());
		            partner.setProfileimage(rs.getString("profileImage"));
		            // Add other fields as needed
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return partner;
	}
		
		
	}
	
