package com.shaadimitra.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shaadimitra.model.PartnerMatch;

import com.shaadimitra.config.DbConfig;

public class AdminDashboardService {
	
	private Connection dbConn;
	
	public AdminDashboardService(){
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public int getTotalUsers() {
	    String query = "SELECT COUNT(*) AS total FROM partners";
	    int totalUsers = 0;

	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            totalUsers = rs.getInt("total");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return totalUsers;
	}
	
	public int getTotalMatchCount() {
	    String query = "SELECT COUNT(*) AS total FROM partner_match";
	    int totalMatches = 0;

	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            totalMatches = rs.getInt("total");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return totalMatches;
	}



	public List<PartnerMatch> getAllMatches() {
	    List<PartnerMatch> matchList = new ArrayList<>();
	    String query = "SELECT * FROM partner_match";
	
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            PartnerMatch match = new PartnerMatch();
	            match.setPartnerone_id(rs.getInt("partnerone_id"));
	            match.setPartnertwo_id(rs.getInt("partnertwo_id"));
	            match.setMatchPercent(rs.getInt("matchpercent"));
	            match.setSuccess(rs.getBoolean("success"));
	            match.setMatchStatus(rs.getString("match_Status"));
	
	            matchList.add(match);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	    return matchList;
	}

	public Boolean updatePartnerMatches(PartnerMatch match) {
		
	    String query = "UPDATE partner_match SET matchpercent = ?, success = ?, match_status = ? WHERE partnerone_id = ? AND partnertwo_id = ?";
	    try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
	        stmt.setInt(1, match.getMatchPercent());
	        stmt.setBoolean(2, match.isSuccess());
	        stmt.setString(3, match.getMatchStatus());
	        stmt.setInt(4, match.getPartnerone_id());
	        stmt.setInt(5, match.getPartnertwo_id());

	        int updatedRows = stmt.executeUpdate();
	        return updatedRows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
