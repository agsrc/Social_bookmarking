package edu.gwu.akshay.sb.dao;

import edu.gwu.akshay.sb.DataStore;
import edu.gwu.akshay.sb.constants.Gender;
import edu.gwu.akshay.sb.constants.UserType;
import edu.gwu.akshay.sb.entities.User;
import edu.gwu.akshay.sb.managers.UserManager;

import java.sql.*;
import java.util.List;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}



	public User getUser(long userId) {
		User user = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
			 Statement stmt = conn.createStatement();) {
			String query = "Select * from User where id = " + userId;
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				long id = rs.getLong("id");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int gender_id = rs.getInt("gender_id");
				Gender gender = Gender.values()[gender_id];
				int user_type_id = rs.getInt("user_type_id");
				UserType userType = UserType.values()[user_type_id];

				user = UserManager.getInstance().createUser(id, email, password, firstName, lastName, gender, userType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}



	public long authenticate(String email, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
			 Statement stmt = conn.createStatement();) {
			String query = "Select id from User where email = '" + email + "' and password = '" + password + "'";
			System.out.println("query: " + query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				return rs.getLong("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}
}

