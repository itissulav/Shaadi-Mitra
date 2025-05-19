package com.shaadimitra.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shaadimitra.config.DbConfig;
import com.shaadimitra.model.PartnerModel;

public class MatchService {
	private Connection dbConn;
	
	public MatchService() {
			try {
				dbConn = DbConfig.getDbConnection();
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
	}
	
	public List <PartnerModel> getMatchedPartners(String username){
		int partner_id = Integer.parseInt(getIdFromUsername(username));
	    List<PartnerModel> matchedPartners = new ArrayList<>();

	    String query = "SELECT p.* FROM partners p " +
	                   "JOIN partner_match pm " +
	                   "ON (p.partner_id = pm.partnerone_id AND pm.partnertwo_id = ?) " +
	                   "OR (p.partner_id = pm.partnertwo_id AND pm.partnerone_id = ?)";

	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        stmt.setInt(1, partner_id);
	        stmt.setInt(2, partner_id);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            PartnerModel partner = new PartnerModel();
	            partner.setFirst_name(rs.getString("first_name"));
	            partner.setLast_name(rs.getString("last_name"));
	            partner.setGender(rs.getString("gender"));
	            partner.setSalary(rs.getInt("salary"));
	            partner.setReligion(rs.getString("religion"));
	            partner.setProfession(rs.getString("profession"));
	            partner.setEmail(rs.getString("email"));
	            partner.setNumber(rs.getString("number"));
	            partner.setUsername(rs.getString("username"));
	            partner.setDob(rs.getDate("dob").toLocalDate());
	            partner.setProfileimage(rs.getString("profileImage"));
	            // Add more setters if needed
	            matchedPartners.add(partner);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return matchedPartners;
		
	}


	private String getIdFromUsername(String username) {
		   PartnerModel partner = null;
		   String query = "SELECT * FROM partners WHERE username = ?";

		   try(PreparedStatement stmt = dbConn.prepareStatement(query)) {
		        
		        stmt.setString(1, username);
		        ResultSet rs = stmt.executeQuery();

		        if (rs.next()) {
		            partner = new PartnerModel();
		            partner.setPartner_id(String.valueOf(rs.getInt("partner_id")));
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

		    return (partner.getPartner_id());
	}

}
