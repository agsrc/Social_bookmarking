package edu.gwu.akshay.sb.managers;

import edu.gwu.akshay.sb.entities.User;

public class UserManager { // creating singletons in manager package-- to avoid unauthorized instantiation
	private static UserManager instance = new UserManager();

	private UserManager() {
	} // i.e create private constructors

	public static UserManager getInstance() {
		return instance;
	}

	public User createUser(long id, String email, String password, String firstname, String lastname, int gender,
			String userType) {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPassword(password);
		user.setGender(gender);
		user.setUserType(userType);
		return user;
	}
}
