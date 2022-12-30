package entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.model.Cart;
import entity.model.Cappello;
import entity.model.User;

public class CartDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public CartDao(Connection con) {
		this.con=con;
	}
	
	public String getEmail(int u_id) {
		String s = "";
		try {
			query="SELECT email FROM utente WHERE id=" + u_id;
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
					s = rs.getString("email");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return s;	
	}
	public boolean AddToCart(int u_id,int p_id) {
		boolean result=false;
		int q=-1;
		try {
			query="SELECT quantita FROM carrello WHERE id_prodotto=? AND id_utente=? AND is_buy=0";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,p_id);
			pst.setInt(2,u_id);
			rs=pst.executeQuery();
			if(rs.next()) {
				q=rs.getInt("quantita");
				q++;
				query="UPDATE carrello SET quantita=? WHERE id_prodotto=? AND id_utente=?";
				pst=this.con.prepareStatement(query);
				pst.setInt(1, q);
				pst.setInt(2,p_id);
				pst.setInt(3,u_id);
				pst.executeUpdate();
				result=true;
			}else {
				query="INSERT INTO carrello (id_utente,is_guest,id_prodotto,quantita,is_buy,data) VALUES(?,?,?,?,0,CURDATE())";
				pst=this.con.prepareStatement(query);
				pst.setInt(1,u_id);
				/*if(is guest) pst.setInt(2,1);
				else pst.setInt(2,0);*/
				pst.setInt(2,0);//sett di default a 0 di is_guest
				pst.setInt(3,p_id);
				pst.setInt(4,1);
				pst.executeUpdate();
				result=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Cart> retrivePerUser(int u_id){
		ArrayList<Cart> cart=new ArrayList<Cart>();
		try {
			query="SELECT * FROM carrello WHERE id_utente=? AND is_buy=0";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,u_id);
			rs=pst.executeQuery();
			while(rs.next()) {
				Cart p=new Cart();
				p.setId(rs.getInt("id"));
				p.setId_utente(rs.getInt("id_utente"));
				p.setId_prodotto(rs.getInt("id_prodotto"));
				p.setIs_guest(rs.getInt("is_guest"));
				p.setIs_buy(rs.getInt("is_buy"));
				p.setQuantita(rs.getInt("quantita"));
				p.setData(rs.getString("data"));
				cart.add(p);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cart;
	}
	
	public boolean remove(int p_id,int u_id) {
		boolean result=false;
		try {
			query="DELETE FROM carrello WHERE id_utente=? AND id_prodotto=? AND is_buy=0";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,u_id);
			pst.setInt(2,p_id);
			pst.execute();
			result=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean checkOut(int u_id) {
		boolean result=false;
		try {
			query="UPDATE carrello SET is_buy=1 WHERE id_utente=? AND is_buy=0 AND data=CURDATE() AND is_guest=0";
			pst=this.con.prepareStatement(query);
			pst.setInt(1, u_id);
			pst.executeUpdate();
			result=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean dec(int p_id,int u_id) {
		boolean result=false;
		int q=-1;
		try {
			query="SELECT quantita FROM carrello WHERE id_prodotto=? AND id_utente=?";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,p_id);
			pst.setInt(2,u_id);
			rs=pst.executeQuery();
			if(rs.next()) {
				q=rs.getInt("quantita");
				if(q>=2) {
					q--;
					query="UPDATE carrello SET quantita=? WHERE id_prodotto=? AND id_utente=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1, q);
					pst.setInt(2,p_id);
					pst.setInt(3,u_id);
					pst.executeUpdate();
					result=true;
				}else remove(p_id,u_id);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean inc(int p_id,int u_id) {
		boolean result=false;
		int q=-1;
		try {
			query="SELECT quantita FROM carrello WHERE id_prodotto=? AND id_utente=? AND is_buy=0";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,p_id);
			pst.setInt(2,u_id);
			rs=pst.executeQuery();
			if(rs.next()) {
				q=rs.getInt("quantita");
				if(q>=1) {
					q++;
					query="UPDATE carrello SET quantita=? WHERE id_prodotto=? AND id_utente=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1, q);
					pst.setInt(2,p_id);
					pst.setInt(3,u_id);
					pst.executeUpdate();
					result=true;
				}else remove(p_id,u_id);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Cart> retriveOrdersPerUser(int u_id){
		ArrayList<Cart> cart=new ArrayList<Cart>();
		try {
			query="SELECT * FROM carrello WHERE id_utente=? AND is_buy=1";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,u_id);
			rs=pst.executeQuery();
			while(rs.next()) {
				Cart p=new Cart();
				p.setId(rs.getInt("id"));
				p.setId_utente(rs.getInt("id_utente"));
				p.setId_prodotto(rs.getInt("id_prodotto"));
				p.setIs_guest(rs.getInt("is_guest"));
				p.setIs_buy(rs.getInt("is_buy"));
				p.setQuantita(rs.getInt("quantita"));
				p.setData(rs.getString("data"));
				cart.add(p);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cart;
	}
	
	public ArrayList<Cart> retriveOrders(){
		ArrayList<Cart> cart=new ArrayList<Cart>();
		try {
			query="SELECT * FROM carrello WHERE is_buy=1";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Cart p=new Cart();
				p.setId(rs.getInt("id"));
				p.setId_utente(rs.getInt("id_utente"));
				p.setId_prodotto(rs.getInt("id_prodotto"));
				p.setIs_guest(rs.getInt("is_guest"));
				p.setIs_buy(rs.getInt("is_buy"));
				p.setQuantita(rs.getInt("quantita"));
				p.setData(rs.getString("data"));
				cart.add(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cart;
	}
	
	public boolean AddToCartAsGuest(int g_id,int p_id) {
		boolean result=false;
		int q=-1;
		try {
			query="SELECT quantita FROM carrello WHERE id_prodotto=? AND id_utente=? AND is_buy=0 AND is_guest=1";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,p_id);
			pst.setInt(2,g_id);
			rs=pst.executeQuery();
			if(rs.next()) {
				q=rs.getInt("quantita");
				q++;
				query="UPDATE carrello SET quantita=? WHERE id_prodotto=? AND id_utente=? AND is_guest=1" ;
				pst=this.con.prepareStatement(query);
				pst.setInt(1, q);
				pst.setInt(2,p_id);
				pst.setInt(3,g_id);
				pst.executeUpdate();
				result=true;
			}else {
				query="INSERT INTO carrello (id_utente,is_guest,id_prodotto,quantita,is_buy,data) VALUES(?,?,?,?,0,CURDATE())";
				pst=this.con.prepareStatement(query);
				pst.setInt(1,g_id);
				pst.setInt(2,1);
				pst.setInt(3,p_id);
				pst.setInt(4,1);
				pst.executeUpdate();
				result=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void removeCart(int p_id, int u_id) {
		query="DELETE FROM carrello WHERE id_prodotto=? AND id_utente=?";
		 try {
			pst=this.con.prepareStatement(query);
			pst.setInt(1,p_id);
			pst.setInt(2,u_id);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
