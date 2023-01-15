package entity.dao;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.connection.DbCon;
import entity.model.Card;
import junit.framework.TestCase;

class CardDaoTest extends TestCase {
	private CardDAO cDao=null;
	private Card c=null;
			
	@BeforeEach
	private void top() {
		c=new Card("cProprietario","11111111111111","01/2025",111,8);
		try {
			cDao=new CardDAO(DbCon.getConnection());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	// Ricerca che va a buon fine
	@Test
	public void getAllCardsTest() {
		ArrayList<Card> carte= cDao.getAllCards(5);
		System.out.println(carte);
		//Mario rossi (id = 5) ha due carte
		Card card1= carte.get(0);
		Card card2= carte.get(1);
		assertEquals(6,card1.getId());
		assertEquals("Mario Rossi", card1.getProprietario());
		assertEquals("4035935938867122",card1.getNumeroCarta());
		assertEquals("03/2023",card1.getDataScadenza());
		assertEquals(463,card1.getCvv());
		
		assertEquals(7,card2.getId());
		assertEquals("Mario Rossi", card2.getProprietario());
		assertEquals("4273412906032924",card2.getNumeroCarta());
		assertEquals("03/2028",card2.getDataScadenza());
		assertEquals(461,card2.getCvv());
	}
	
	//Ricerca non va a buon fine
	@Test
	public void failGetAllCardsTest() {
		ArrayList<Card> carte= cDao.getAllCards(-1);
		System.out.println(carte);
		assertTrue(carte.isEmpty());
	}
	
	//Ricerca Corretta
	@Test
	public void retriveCardByIdTest() {
		Card generalCard=cDao.retriveCardById(2);
		assertEquals(2,generalCard.getId());
	}
	
	//Ricerca non corretta
	@Test
	public void failRetriveCardByIdTest() {
		Card generalCard=cDao.retriveCardById(-1);
		assertNull(generalCard);
	}
	
	//Cancellazione corretta
	@Test
	public void removeCardTest() {
		cDao.removeCard(1);
		assertNull(cDao.retriveCardById(1));
	}
	
	
	//Inserimento corretto
	@Test
	public void insertCardTest() {
		assertTrue(cDao.insertCard(c.getProprietario(),c.getNumeroCarta(),c.getDataScadenza(),c.getCvv(),c.getUser()));
	}
}
