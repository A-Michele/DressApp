package entity.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.connection.DbCon;
import entity.model.Ordine;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDao() {
		
	}

	public OrderDao(Connection con) {
		this.con=con;
	}
	
	//Metodo rapido per cambiare lo stato di un ordine in PAGATO
	public boolean changeState(int id) {
		try{
			String query = "UPDATE Ordine set is_buy = true where id= ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	//------METODI CRUD-----
	
	public ArrayList<Ordine> getAllOrders(){
		ArrayList<Ordine> ordini=new ArrayList<Ordine>();
		try {
			query="SELECT * FROM Ordine";
			//Connection c = DbCon.getConnection();
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Ordine row=new Ordine();
				row.setId(rs.getInt("id"));
				row.setData(rs.getDate("data"));
				row.setUser(rs.getInt("user"));
				row.setIsBuy(rs.getBoolean("is_buy"));
				ordini.add(row);
			}
			return ordini;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ordini;
	}
	
	//metodo restituisce la mail dell'utente che ha effettuato un ordine tramite id dell'ordine
		public String getMailUserbyOrderId(int id) {
			String mail=null;
			try{ 
				String query = "SELECT user.email FROM ordine JOIN user WHERE ordine.user=user.id AND ordine.id=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, id);
				rs=ps.executeQuery();
				if(rs.next())
					mail= rs.getString(1);
				return mail;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
	
	
	public void doSave(Date data, int user, boolean is_buy) {
		try {
            query="INSERT INTO Ordine(data,user,is_buy)"
            		+ " VALUES(?,?,?)";
            pst=this.con.prepareStatement(query);
            pst.setDate(1, data);
            pst.setInt(2, user);
            pst.setBoolean(3, is_buy);
            pst.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            }
	}
	
	public Ordine doRetriveById(int id) {
		Ordine ordine= new Ordine();
		try {
            query="SELECT * from Ordine where id=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1,id);
            rs=pst.executeQuery();
            while(rs.next()) {
            	ordine.setId(rs.getInt("id"));
            	ordine.setData(rs.getDate("data"));
            	ordine.setUser(rs.getInt("user"));
            	ordine.setIsBuy(rs.getBoolean("is_buy"));
            }
            return ordine;
			
        }catch(Exception e){
            e.printStackTrace();
        }
		return null;
	}
	
	public Ordine doRetriveByIdBuy(int user) {
		Ordine ordine= new Ordine();
		try {
            query="SELECT * from Ordine where user=? AND is_buy=false";
            pst=this.con.prepareStatement(query);
            pst.setInt(1,user);
            rs=pst.executeQuery();
            while(rs.next()) {
            	ordine.setId(rs.getInt("id"));
            	ordine.setData(rs.getDate("data"));
            	ordine.setUser(rs.getInt("user"));
            	ordine.setIsBuy(rs.getBoolean("is_buy"));
            }
            return ordine;
			
        }catch(Exception e){
            e.printStackTrace();
        }
		return null;
	}
	
	
	
	public boolean doDelete(int id) {
		try{
			String query = "DELETE FROM Ordine WHERE id = ?";
			pst= con.prepareStatement(query);
			pst.setInt(1, id);
			int result = pst.executeUpdate();
		
			if (result == 1) return true;
			return false;
			}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean doUpdateById(int id,String attributo, String valore) throws SQLException {
		
		try{
			String query = "UPDATE Ordine set "+attributo+"=? where id= ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, valore);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public ArrayList<Ordine> searchOrdersFromNameProduct(String nome,String inizio, String fine){
		ArrayList<Ordine> tuttiOrdini= new ArrayList<Ordine>();
		Ordine o=null;
		try {
			query="SELECT DISTINCT ordine.* FROM ordine JOIN dettaglioordine JOIN cappello "
					+ "JOIN user WHERE ordine.id=dettaglioordine.ordine AND dettaglioordine.cappello=cappello.id AND user.id=ordine.user AND ordine.is_buy=1 AND ordine.data > ? AND ordine.data < ? ";
			
			pst=this.con.prepareStatement(query);
			if(nome!=null && !(nome.equals(""))) {
				query+=" AND user.email LIKE ?";
			}
			pst=this.con.prepareStatement(query);
			if(nome!=null && !(nome.equals(""))) {
				pst.setString(3,"%"+nome+"%");
				}
			if(inizio!=null && !(inizio.equals("")) && (fine==null || (fine.equals("")))) {
				String now = "2024-12-31";
			    System.out.println();
			    System.out.println("Hai inserito solo la data iniziale "+ inizio +" e la data finale è "+ Date.valueOf(now));
			    pst.setDate(1,Date.valueOf(inizio));
				pst.setDate(2,Date.valueOf(now));
			}else if(fine!=null && !(fine.equals("")) && (inizio==null || (inizio.equals("")))) {
				Date DFine = Date.valueOf(fine);
				pst.setDate(1,Date.valueOf("2000-01-01"));
				pst.setDate(2,DFine);
			}else if((fine!=null && !(fine.equals(""))) && (inizio!=null && !(inizio.equals("")))) {
				Date Dinizio = Date.valueOf(inizio);
				Date DFine = Date.valueOf(fine);
				pst.setDate(1,Dinizio);
				pst.setDate(2,DFine);
			}else if((inizio==null || inizio.equals("")) && (fine==null || fine.equals(""))){
				pst.setDate(1,Date.valueOf("2000-01-01"));
				pst.setDate(2,Date.valueOf("2100-12-31"));
			}
			
            rs=pst.executeQuery();
            while(rs.next()) {
            	o= new Ordine();
            	o.setId(rs.getInt("id"));
            	o.setData(rs.getDate("data"));
            	o.setUser(rs.getInt("user"));
            	o.setIsBuy(rs.getBoolean("is_buy"));
            	tuttiOrdini.add(o);
            }
            if(tuttiOrdini!=null)return tuttiOrdini;
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}

	public int idGrande() {
		int x=0;
		try {
            query="SELECT MAX(id) AS i from Ordine";
            rs=pst.executeQuery(query);
            while(rs.next()) {
            	x=rs.getInt("i");
            }
            return x;
            }catch(Exception e) {
            	e.printStackTrace();
            }
		return -1;
	
	}
	public ArrayList<Ordine> getOrdersByUser(int user){
        ArrayList<Ordine> lista= new ArrayList<Ordine>();

        try {
            query="SELECT * from Ordine where user=? AND is_buy=true";
            pst=this.con.prepareStatement(query);
            pst.setInt(1,user);
            rs=pst.executeQuery();
            while(rs.next()) {
                Ordine ordine= new Ordine();
                ordine.setId(rs.getInt("id"));
                ordine.setData(rs.getDate("data"));
                ordine.setUser(rs.getInt("user"));
                ordine.setIsBuy(rs.getBoolean("is_buy"));
                lista.add(ordine);
            }
            return lista;

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
	
	
	
}
