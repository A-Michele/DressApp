package entity.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.connection.DbCon;
import entity.model.User;
import junit.framework.TestCase;

class UserDaoTest extends TestCase {

	private UserDao uD= null;
	//private User user= null;
	
	@BeforeEach
	protected void setUp() throws Exception {
		//user = new User();
		uD= new UserDao(DbCon.getConnection());
	}
	
	@Test
	public void doDeleteByIdTest() {
		assertTrue(uD.doDeleteById(1));
	}
	
	@Test
	void doDeleteTest() {
		assertTrue(uD.doDelete("giuseppeverdi@gmail.com"));
	}
	
	@Test
	void getMailByIdTest() {
		assertEquals("antonellafesta@gmail.com",uD.getMailById(8));
	}

}
