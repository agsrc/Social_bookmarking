package edu.gwu.akshay.sb.managers;

import edu.gwu.akshay.sb.constants.Gender;
import edu.gwu.akshay.sb.constants.UserType;
import edu.gwu.akshay.sb.dao.UserDao;
import edu.gwu.akshay.sb.entities.User;
import edu.gwu.akshay.sb.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

//managers would be invoked through controller
public class UserManager { // creating singletons in manager package-- to avoid unauthorized instantiation
	private static UserManager instance = new UserManager();
	private static UserDao dao = new UserDao();

	private UserManager() {
	} // i.e create private constructors

	public static UserManager getInstance() {
		return instance;
	}

	public User createUser(long id, String email, String password, String firstname, String lastname, Gender gender,
			UserType userType) {
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

	public List<User> getUsers() {
		return dao.getUsers();
	}

    public User getUser(long userId) {
		return dao.getUser(userId);
    }

	public long authenticate(String email, String password) {
	return dao.authenticate(email, StringUtil.encodePassword(password));
	}
}
