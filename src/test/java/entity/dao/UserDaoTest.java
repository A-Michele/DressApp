package entity.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.connection.DbCon;
import entity.model.User;
import junit.framework.TestCase;

class UserDaoTest extends TestCase {

	private UserDao uDao= null;
	
	@BeforeEach
	protected void setUp() throws Exception {
		try {
			uDao=new UserDao(DbCon.getConnection());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Login corretto
	@Test
	public void UserLoginTest(){
		User u= uDao.userLogin("alaiamichele@gmail.com", "michele12");
		assertEquals(1,u.getId());
	}
	
	//Login fallito
	@Test
	public void failUserLoginTest(){
		User u= uDao.userLogin("mariorossi@gmail.com", "mariorossi");
		assertNull(u);
	}
	
	//Ritrova Email corretta
	@Test
	public void retrivebyMailTest(){
		assertTrue(uDao.retrivebyMail("mariorossi@gmail.com"));
	}
	
	//Ritrova Email non corretta
	@Test
	public void failRetrivebyMailTest(){
		assertFalse(uDao.retrivebyMail("mariorossi@gail.com"));
	}
	
	//Ritrova Mail con id corretta
	@Test
	public void getMailbyIdTest() {
		assertEquals("mariorossi@gmail.com",uDao.getMailById(5));
	}
	
	//Ritrova Mail con id non corretta
	@Test
	public void failgetMAilbyIdTest() {
		assertNull(uDao.getMailById(-1));
	}
	
	//Registrazione corretta
	@Test
	public void userRecTest() {
		uDao.userRec("pluto","giallo","plutogiallo@gmail.com","plutogiallo",0,0);
		assertTrue(uDao.retrivebyMail("plutogiallo@gmail.com"));
	}
	//Update corretta
	@Test
	public void doUpdateTest() {
		User u=uDao.userLogin("mariorossi@gmail.com", "mario12");
		uDao.doUpdate(u, "password", "mario118");
		u=uDao.userLogin("mariorossi@gmail.com", "mario118");
		assertEquals("mario118",u.getPassword());
	}
	
	//Update con id corretta
	@Test
	public void doUpdateByIdTest() {
		User u=uDao.userLogin("lucabianco@gmail.com", "luca12");
		assertTrue(uDao.doUpdateById(u.getId(), "password", "luca118"));
		
	}
	
	//Cancellazione corretta
		@Test
		public void doDeleteByIdTest() {
			assertTrue(uDao.doDeleteById(9));
		}
		
		//Cancellazione corretta
		@Test
		public void faildoDeleteByIdTest() {
			assertFalse(uDao.doDeleteById(257));
		}
		
		//Cancellazione corretta
		@Test
		void doDeleteTest() {
			assertTrue(uDao.doDelete("giuseppeverdi@gmail.com"));
		}
		
		//Cancellazione non corretta
		@Test
		void faildoDeleteTest() {
			assertFalse(uDao.doDelete("giuseppeverdi@gil.com"));
		}
}
