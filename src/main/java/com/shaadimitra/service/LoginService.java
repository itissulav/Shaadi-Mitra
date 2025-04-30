package com.shaadimitra.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shaadimitra.config.DbConfig;
import com.shaadimitra.model.PartnerModel;
import com.shaadimitra.util.PasswordUtil;

public class LoginService {
	
	private Connection dbConn;
	private boolean isConnectionError = false;
	
	public LoginService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}
	
	public Boolean loginUser(PartnerModel partnerModel) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT username, password FROM partners WHERE username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, partnerModel.getUsername());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return validatePassword(result, partnerModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}
	
	private boolean validatePassword(ResultSet result, PartnerModel partnerModel) throws SQLException {
		String dbUsername = result.getString("username");
		String dbPassword = result.getString("password");
		
		System.out.println(dbUsername);
		System.out.println(dbPassword);
		System.out.println(PasswordUtil.decrypt(dbPassword, dbUsername));

		return dbUsername.equals(partnerModel.getUsername())
				&& PasswordUtil.decrypt(dbPassword, dbUsername).equals(partnerModel.getPassword());
	}
	
	
}
