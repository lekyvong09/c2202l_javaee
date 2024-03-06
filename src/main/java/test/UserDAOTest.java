package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ray.dao.UserDAO;
import com.ray.entity.User;

class UserDAOTest {
	private static UserDAO userDAO;

	@BeforeAll
	public static void setupClass() {
		userDAO = new UserDAO();
	}
	
	@Test
	void test() {
		User newUser = new User("test2@email.com", "test2 le", "password");
		
		User insertedUser = userDAO.create(newUser);
		
		assertTrue(insertedUser.getUserId() > 0);
	}
	
	@Test
	void testUpdate() {
		User user = userDAO.getById(2);
		user.setPassword("newpassword");
		
		User updatedUser = userDAO.update(user);
		
		assertEquals("newpassword", updatedUser.getPassword());
	}
	
//	@Test
	void testDeleteUser() {
		userDAO.deleteById(3);
		assertTrue(Objects.isNull(userDAO.getById(3)));
	}
	
	@Test 
	void testGetAll() {
		List<User> userList = userDAO.getAll();
		System.out.println(userList);
		assertTrue(userList.size() > 0);
	}
	
	@Test 
	void testGetTotalRecord() {
		Long totalRecord = userDAO.getTotalRecord();
		System.out.println(totalRecord);
		assertTrue(totalRecord > 0L);
	}
	
	@Test 
	void testGetAllWithHQL() {
		List<User> userList = userDAO.getAllWithHQL();
		System.out.println(userList);
		assertTrue(userList.size() > 0);
	}
	
	@Test 
	void testGetTotalRecordWithHQL() {
		Long totalRecord = userDAO.getTotalRecordWithHQL();
		System.out.println(totalRecord);
		assertTrue(totalRecord > 0L);
	}
	
	
	@Test
	void testGetUserByEmail() {
		User user = userDAO.getUserByEmail("test2@email.com");
		System.out.println(user);
		assertNotNull(user);
	}
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("clean up");
	}
}
