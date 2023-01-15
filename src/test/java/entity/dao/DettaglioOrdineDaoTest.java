package entity.dao;

import java.sql.Date;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.connection.DbCon;
import entity.model.DettaglioOrdine;
import entity.model.OrdineCompleto;
import junit.framework.TestCase;

class DettaglioOrdineDaoTest extends TestCase {
	DettaglioOrdineDAO dDao=null;
	
	@BeforeEach
	protected void setUp() throws Exception {
		dDao=new DettaglioOrdineDAO(DbCon.getConnection());
	}
	
	@Test
	public void getAllDettaglioOrdiniTest() {
		int[] lista= {1,2,3,5,6,7,8,10,11};
		ArrayList<DettaglioOrdine> dOrdini= (ArrayList<DettaglioOrdine>) dDao.getAllDettaglioOrdini();
		int i=0;
		for(int x : lista) {
			assertEquals(x,dOrdini.get(i++).getCappello());
		}
		
	}

	@Test
	public void insertDettaglioOrdineTest() {
		ArrayList<DettaglioOrdine> lista = (ArrayList<DettaglioOrdine>) dDao.getAllDettaglioOrdini();
		int grandezza= lista.size();
		dDao.insertDettaglioOrdine(1, 1, 1);
		ArrayList<DettaglioOrdine> lista2 = (ArrayList<DettaglioOrdine>) dDao.getAllDettaglioOrdini();
		int grandezza2= lista2.size();
		assertEquals(grandezza,grandezza2-1);
	}
	
	@Test
    void updateQuantitaTest() {
        dDao.updateQuantita(10,1,1);
        assertEquals(10,dDao.getQuantita(1,1));
    }

    @Test
    void getQuantita() {
        assertEquals(1,dDao.getQuantita(2,1));
    }

    @Test
    void removeById() {
        ArrayList<DettaglioOrdine> dett= (ArrayList<DettaglioOrdine>)dDao.getAllDettaglioOrdini();
        dDao.removeById(11,7);
        assertEquals(10,dett.size());
    }

    @Test
    void searchDettaglioOrdineByOrdineIdTest() {
        ArrayList<DettaglioOrdine> dett= (ArrayList<DettaglioOrdine>)dDao.searchDettaglioOrdineByOrdineId(2);
        assertEquals(1,dett.size());
        assertEquals(new DettaglioOrdine(5,1,2),dett.get(0));
    }

    @Test
    void searchOrdiniCompletiTest() {
        ArrayList<OrdineCompleto> oc= dDao.searchOrdiniCompleti(5);
        assertEquals(1,oc.size());
        assertEquals(new OrdineCompleto("lucabianco@gmail.com","Kappa HOPPA","Invernale",(float) 11.99,1,Date.valueOf("2021-12-31")),oc.get(0));
    }


}
