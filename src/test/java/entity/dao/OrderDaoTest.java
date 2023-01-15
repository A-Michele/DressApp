package entity.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.connection.DbCon;
import entity.model.Ordine;
import junit.framework.TestCase;

class OrderDaoTest extends TestCase {

	private OrderDao oD= null;
	private Ordine ordine= null;
	private ArrayList<Ordine> ordini=null;
	
	
	@BeforeEach
	protected void setUp() throws Exception {
		ordine= new Ordine(new Date(System.currentTimeMillis()),1,false);
		oD= new OrderDao(DbCon.getConnection());
		
		//Se facciamo cosi prima di ogni test viene aggiunto un ordine al DB
		oD.doSave(ordine.getDate(),ordine.getUser(),ordine.getIsBuy());
		ordine.setId(oD.idGrande());
		
		ordini= new ArrayList<Ordine>(oD.getAllOrders());
	}
	
	// Cambio di Stato corretto
	@Test
	void changeStateTest() {
		assertTrue(oD.changeState(ordine.getId()));
	}
	
	// Cambio di Stato non corretto
	@Test
	void failchangeStateTest() {
		assertTrue(oD.changeState(-1));
	}
	
	// Recupero di tutti gli ordini corretto
	@Test
	public void getAllOrdersTest() {
		int i=1;
		for(Ordine x: ordini) {
			assertEquals(i++,x.getId());
		}
	}
	
	//Ricerca Email utente tramite id corretto
	@Test
	public void getMailUserbyOrderIdTest() {
		String x= oD.getMailUserbyOrderId(5);
		assertEquals("mariorossi@gmail.com",x);
	}
	
	//Salvataggio corretto
	@Test
	public void doSaveTest() {
		assertEquals(ordine.getId(),ordini.get(oD.idGrande()-1).getId());
	}
	
	//Ricerca ordine con id corretto
	@Test
	void doRetriveByIdTest() {
		Ordine d=oD.doRetriveById(2);
		assertEquals(2,d.getId());
	}
	
	//Ricerca ordine con id non corretto
	@Test
	void faildoRetriveByIdTest() {
		Ordine d=oD.doRetriveById(-1);
		assertNull(d.getDate());
	}
	
	// Ricerca di un ordine non ancora completato tramite l'id dell'utente corretto
	@Test
	void doRetriveByIdBuyTest() {
		Ordine ris= oD.doRetriveByIdBuy(8);
		assertFalse(ris.getIsBuy());
	}
	
	// Ricerca di un ordine non ancora completato tramite l'id dell'utente non corretto
	@Test
	void faildoRetriveByIdBuyTest() {
		Ordine ris= oD.doRetriveByIdBuy(-1);
		assertNull(ris.getDate());
	}
	
	// Ricerca ordine in base al nome di un prodotto corretto
	@Test
	public void searchOrdersFromNameProductTest() {
		ArrayList<Ordine> risultato= oD.searchOrdersFromNameProduct("kappa");
		Ordine kappa= risultato.get(0);
		assertEquals(5,kappa.getId());
		assertEquals(Date.valueOf("2021-12-31"),kappa.getDate());
		assertEquals(6,kappa.getUser());
		assertTrue(kappa.getIsBuy());
	}

	// Ricerca ordine in base al nome di un prodotto non corretto
	@Test
	public void failsearchOrdersFromNameProductTest() {
		ArrayList<Ordine> risultato= oD.searchOrdersFromNameProduct("Addiddas");
		assertTrue(risultato.isEmpty());
	}
	
	// Ricerca del Massimo Id
	@Test
	void idGrandeTest() {
		assertEquals(ordini.size(),oD.idGrande());
	}
	
	// Ricerca tutti gli ordini con l'id di un utente corretto
	@Test
	void getOrdersByUserTest() {
		ArrayList<Ordine> ordini= oD.getOrdersByUser(6);
		Ordine ordine1= ordini.get(0);
		Ordine ordine2= ordini.get(1);
		
		assertEquals(5,ordine1.getId());
		assertEquals(6,ordine2.getId());
	}
	
	
	// Ricerca tutti gli ordini con l'id di un utente non corretto
	@Test
	void failgetOrdersByUserTest() {
		ArrayList<Ordine> ordini= oD.getOrdersByUser(1);
		assertTrue(ordini.isEmpty());
	}
}
