package entity.dao;

import java.util.ArrayList;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.connection.DbCon;
import entity.model.Cappello;
import junit.framework.TestCase;

class CappelloDaoTest extends TestCase {
	private CappelloDao cDao=null;
	private Cappello c= null;
	
	
	
	@BeforeEach
	private void top() {
		c=new Cappello("cNome","cCategoria",0,"cFoto","cDescrizione",1);
		try {
			cDao=new CappelloDao(DbCon.getConnection());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Ricerca di tutti i cappelli
	@Test
	public void getAllProductsTest() {
		ArrayList<Cappello> lista= (ArrayList<Cappello>) cDao.getAllProducts();
		ArrayList<String> lista2=new ArrayList<String>();
		
		lista2.add("New Era");
		lista2.add("Nike U");
		lista2.add("Nike Futura");
		lista2.add("Adidas Original");
		lista2.add("Adidas Baseball");
		lista2.add("Carhartt WIP");
		lista2.add("The North Face LOGO");
		lista2.add("Kappa HOPPA");
		lista2.add("Tommy Jeans");
		lista2.add("Champion");
		lista2.add("Calvin Klein CLASSIC");
		lista2.add("Nike Performance");
		lista2.add("Under Armour");
		lista2.add("The North Face SUN");
		lista2.add("Gucci");
		lista2.add("Off-White");
		lista2.add("FENDI KIDS");
		lista2.add("MOSCHINO");
		lista2.add("Burberry");
		lista2.add("Givenchy");
		lista2.add("Stress Days");
		
		int i=0;
		for(Cappello p:lista) {
			assertEquals(lista2.get(i++),p.getNome());
		}
	}
	
	//Ricerca di un cappello tramite il nome corretto
	@Test
	public void searchItemsTest() {
		ArrayList<Cappello> lista = (ArrayList<Cappello>)cDao.searchItems("champion");
		Cappello c= lista.get(0);
		assertEquals(10,c.getId());
	}
	
	//Rimozione corretta
	@Test
	public void removeProductTest() {
		cDao.removeProduct(20);
		assertNull(cDao.retriveProductById(20).getNome());
	}
	
	//Inserimento corretto
	@Test
	public void insertProductTest() {
		cDao.insertProduct(c.getNome(),c.getDescrizione(),c.getPrezzo(),c.getCategoria(),c.getFoto(),c.getDisp());
		ArrayList<Cappello> cappelli= (ArrayList<Cappello>)cDao.getAllProducts();
		//c.setId(cDao.idGrande());
		assertEquals(c.getNome(),cappelli.get(cappelli.size()-1).getNome());
	}
	
	//Ricerca cappello by id corretto
	@Test
	public void retriveProductByIdTest() {
		Cappello d=cDao.retriveProductById(2);
		assertEquals(2,d.getId());
	}
	
	//Ricerca cappello by id non corretto
	@Test
	public void failretriveProductByIdTest() {
		Cappello d=cDao.retriveProductById(-1);
		assertNull(d.getNome());
	}

	//Modifica prodotto corretta
	@Test
	public void updateProdottoTest() {
		assertTrue(cDao.updateProdotto(22,"Givenchy","",10,"","",100,false));
	}
	
	//Modifica prodotto non corretta
	@Test
	public void failupdateProdottoTest() {
		assertTrue(cDao.updateProdotto(-1,"","",0,"","",0,true));
	}
	
	// Modifica disponibilità prodotto corretta
	@Test
	public void updateDispTest() {
		Cappello x=cDao.retriveProductById(2);
		cDao.updateDisp(x.getId(),40);
		x=cDao.retriveProductById(2);
		assertEquals(40,x.getDisp());
	}
	 
	//Aggiornamento campo Modificato corretto
	@Test
	public void updateModificatoTest() {
		cDao.updateModificato(7);
		Cappello x= cDao.retriveProductById(7);
		System.out.println(x);
		assertTrue(x.getModificato());
	}
}
