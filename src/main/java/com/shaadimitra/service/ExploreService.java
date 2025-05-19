package com.shaadimitra.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shaadimitra.config.DbConfig;
import com.shaadimitra.model.PartnerModel;

public class ExploreService {
	
	private boolean isConnectionError = false;
	private Connection dbConn;
	
		public ExploreService() {
			
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
		
	    public List<PartnerModel> getAllPartners(String username) {
	        List<PartnerModel> partners = new ArrayList<>();

	        if (isConnectionError) {
	            System.out.println("Connection Error!");
	            return partners;
	        }

	        String query = "SELECT * FROM partners WHERE username != ? AND username != ?";

	        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        	stmt.setString(1, username);
	        	stmt.setString(2, "admin");
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
		            partner.setDob((rs.getDate("dob")).toLocalDate());
		            partner.setProfileimage(rs.getString("profileImage"));
	               
	                partners.add(partner);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return partners;
	    }

		public PartnerModel getPartnerDetail(String username) {
	        String query = "SELECT * FROM partners WHERE username = ?";

	        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        	stmt.setString(1, username);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
		            PartnerModel partner = new PartnerModel();
		            partner.setPartner_id(rs.getString("partner_id"));
		            partner.setFirst_name(rs.getString("first_name"));
		            partner.setLast_name(rs.getString("last_name"));
		            partner.setGender(rs.getString("gender"));
		            partner.setSalary(rs.getInt("salary"));
		            partner.setReligion(rs.getString("religion"));
		            partner.setProfession(rs.getString("profession"));
		            partner.setEmail(rs.getString("email"));
		            partner.setNumber(rs.getString("number"));
		            partner.setUsername(rs.getString("username"));
		            partner.setDob((rs.getDate("dob")).toLocalDate());
		            partner.setProfileimage(rs.getString("profileImage"));
	               
	                return partner;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return null;
		}

		public Boolean matchPartner(PartnerModel partnerOne, PartnerModel partnerTwo) {
		    String matchQuery = "INSERT INTO partner_match (partnerone_id, partnertwo_id, matchpercent, success, match_status) " +
	                   "VALUES (?, ?, ?, ?, ?)";
		    try (PreparedStatement stmt = dbConn.prepareStatement(matchQuery)) {
		        stmt.setInt(1, Integer.parseInt(partnerOne.getPartner_id()));
		        stmt.setInt(2, Integer.parseInt(partnerTwo.getPartner_id()));
		        stmt.setInt(3, 0); // default match percent
		        stmt.setBoolean(4, false); // not a successful match yet
		        stmt.setString(5, "pending");
		        stmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
			return null;
		}
	}
