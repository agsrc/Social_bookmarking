package edu.gwu.akshay.sb.dao;

import edu.gwu.akshay.sb.DataStore;
import edu.gwu.akshay.sb.entities.User;

import java.util.List;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}

}
