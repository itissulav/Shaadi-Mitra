package com.shaadimitra.service;

import com.shaadimitra.config.DbConfig;

import com.shaadimitra.model.PartnerModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
	
	private Connection dbConn;
	
	public DatabaseService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

    public List<PartnerModel> getAllPartners() {
        List<PartnerModel> partners = new ArrayList<>();

        String query = "SELECT * FROM partners WHERE username != ?";

        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
        	stmt.setString(1, "admin");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
	            PartnerModel partner = new PartnerModel();
	            partner.setPartner_id(String.valueOf(rs.getInt("partner_id")));
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
    
    public Boolean savePartnerDetails(PartnerModel partnerModel) {
		String updateQuery = "UPDATE partners SET first_name = ?, last_name = ?, gender = ?, salary = ?, religion = ?, profession = ?, email = ?, number = ?, username = ?, dob = ? WHERE partner_id = ?";
		
		try(PreparedStatement updateStmt = dbConn.prepareStatement(updateQuery)){
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
			updateStmt.setString(11, partnerModel.getPartner_id()); // condition: where partner_id = ?
			
			return updateStmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
            e.printStackTrace();
        }
		
		return false ;

    }
    
    public Boolean deletePartner(PartnerModel partner) {
    	String deleteQuery = "DELETE FROM partners WHERE partner_id = ?";
    	
    	try (PreparedStatement deleteStmt = dbConn.prepareStatement(deleteQuery)){
    	
    		deleteStmt.setString(1, partner.getPartner_id());
    		
    		return deleteStmt.executeUpdate() > 0;
    		
    	}catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	return false;
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
			insertStmt.setInt(4, partnerModel.getSalary()); // salary as string for now, or 0
			insertStmt.setString(5, partnerModel.getReligion());
			insertStmt.setString(6, partnerModel.getProfession());
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
	
	public List<PartnerModel> searchPartner(String searchUsername){
        List<PartnerModel> partners = new ArrayList<>();

		String searchQuery = "SELECT * FROM partners WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(searchQuery)) {
        	stmt.setString(1, searchUsername);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
	            PartnerModel partner = new PartnerModel();
	            partner.setPartner_id(String.valueOf(rs.getInt("partner_id")));
	            partner.setFirst_name(rs.getString("first_name"));
	            System.out.println(partner.getFirst_name());
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
}

