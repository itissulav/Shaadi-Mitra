package com.shaadimitra.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		            partner.setPassword(rs.getString("password"));
		            partner.setProfileimage(rs.getString("profileImage"));
		            // Add other fields as needed
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return partner;
	}


		
		
	}