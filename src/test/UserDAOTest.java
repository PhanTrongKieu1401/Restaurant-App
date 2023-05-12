package test;

import org.junit.Assert;
import org.junit.Test;

import dao.UserDAO;
import model.User;

public class UserDAOTest {
	UserDAO userDAO = new UserDAO();
	
	@Test
	public void testCheckLoginStandard() {
		User user = new User();
		user.setUsername("kieu");
		user.setPassword("140102");
		boolean value = userDAO.checkLogin(user);
		Assert.assertTrue(value);
		return;
	}
	
	@Test
	public void testCheckLoginException() {
		User user = new User();
		user.setUsername("xxxx");
		user.setPassword("140102");
		boolean value = userDAO.checkLogin(user);
		Assert.assertFalse(value);
		return;
	}
	
}
