package entity.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				String query = "SELECT user FROM ordine WHERE id= ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, id);
				rs=pst.executeQuery();
				int userId=0;
				if(rs.next())
					userId= rs.getInt("user");
				UserDao uDAO= new UserDao(DbCon.getConnection());
				mail= uDAO.getMailById(userId);
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return mail;
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
	
	//Metodo utilizzato per ricercare un ordine per il quale non è stato ancora fatto il check-out
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
	
	public ArrayList<Ordine> searchOrdersFromNameProduct(String nome){
		ArrayList<Ordine> tuttiOrdini= new ArrayList<Ordine>();
		Ordine o=null;
		try {
			query="SELECT DISTINCT ordine.* FROM ordine JOIN dettaglioordine JOIN cappello WHERE ordine.id=dettaglioordine.ordine AND dettaglioordine.cappello=cappello.id  AND ordine.is_buy=1 AND cappello.nome LIKE ?  ";
			pst=this.con.prepareStatement(query);
            pst.setString(1,"%"+nome+"%");
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
