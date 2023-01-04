package entity.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public void doSave(Date data, int user, boolean is_buy) {
		Ordine ordine= new Ordine();
		try {
            query="INSERT INTO Ordine(id,data,user,is_buy)"
            		+ " VALUES(?,?,?,?)";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, ordine.getId());
            pst.setDate(2, data);
            pst.setInt(3, user);
            pst.setBoolean(4, is_buy);
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
	
	
}
