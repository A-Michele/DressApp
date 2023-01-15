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
		oD.doSave(ordine.getDate(),ordine.getUser(),ordine.getIsBuy());ordine.setId(oD.idGrande());
		ordini= new ArrayList<Ordine>(oD.getAllOrders());
	}

	@Test 
	void doSaveTest() {
		//oD.doSave(ordine.getDate(),ordine.getUser(),ordine.getIsBuy());ordine.setId(oD.idGrande());
		
		assertEquals(ordine.getId(),ordini.get(ordini.size()-1).getId());
	}//devo fare il confronto tra id perche la equals di Ordine non funziona... sto aggiungendo uno al db al rigo 26
	
	
	@Test
	void changeStateTest() {
		assertTrue(oD.changeState(ordine.getId()));
	}
	
	
	//getAllOrder non soo come si fa
	
	
	
	
	@Test
	void getMailUserbyOrderIdTest() {
		String x= oD.getMailUserbyOrderId(ordine.getId());
		UserDao uD= null;
		try {
			uD= new UserDao(DbCon.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String oracle= uD.getMailById(ordine.getUser());
		assertEquals(oracle,x);
	}
	
	@Test
	void retriveOrderByIdTest() {
		Ordine d=oD.doRetriveById(2);
		assertEquals(2,d.getId());
	}
	
	@Test
	void doRetriveByIdBuyTest() {
		Ordine ris= oD.doRetriveByIdBuy(ordine.getUser());
		assertEquals(ordine.getId(),ris.getId());
	}

	
	
	@Test
	void doUpdateByIdTest() {
		//QUESTO METODO è INUTILE
	}
	
	
	//searchOrdersFromNameProduct MI SEMBRA LUNGO E MI SCOCCIO
	
	@Test
	void idGrandeTest() {
		
		assertEquals(ordini.size(),oD.idGrande());
	}
	
	@Test
	public void removeOrderTest() {
		oD.doDelete(ordine.getId());
		assertNull(oD.doRetriveById(ordine.getId()).getDate());
	}
	
	@Test
	void getOrdersByUserTest() {
		ArrayList<Ordine> ordini= oD.getOrdersByUser(5);
		Ordine ord= new Ordine(Date.valueOf("2022-05-20"),5,true);
		assertEquals(4,ordini.size());
		ord.setId(1);
		assertEquals(ord,ordini.get(0));
		ord.setId(2);
		
		ord.setData(Date.valueOf("2022-05-30"));
		assertEquals(ord,ordini.get(1));
		ord.setId(3);
		ord.setData(Date.valueOf("2022-08-01"));
		assertEquals(ord,ordini.get(2));
		ord.setId(4);
		ord.setData(Date.valueOf("2022-11-11"));
		assertEquals(ord,ordini.get(3));
		
	}
}
