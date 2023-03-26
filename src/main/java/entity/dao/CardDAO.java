package entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.model.Card;

public class CardDAO {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public CardDAO(Connection con) {
		this.con=con;
	}
	
	public CardDAO() {
	}
	
	public ArrayList<Card> getAllCards(int card_user){
		ArrayList<Card> cards=new ArrayList<Card>();
		try {
			query="SELECT * FROM Card WHERE user=" + card_user;
			//Connection c = DbCon.getConnection();
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Card row=new Card();
				row.setId(rs.getInt("id"));
				row.setProprietario(rs.getString("proprietario"));
				row.setNumeroCarta(rs.getString("numero_carta"));
				row.setDataScadenza(rs.getString("data_scadenza"));
				row.setCvv(rs.getInt("cvv"));
				row.setUser(rs.getInt("user"));
				cards.add(row);
			}
			return cards;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void removeCard(int card_id) {
		query="DELETE FROM Card WHERE id=" + card_id;
			 try {
				pst=this.con.prepareStatement(query);
				pst.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
	}
	
	
	public boolean insertCard(String proprietario,String numeroCarta,String dataScadenza,int cvv, int user) {
        try {
            query="INSERT INTO Card(proprietario,numero_carta,data_scadenza,cvv,user)"
            		+ " VALUES(?,?,?,?,?)";
            pst=this.con.prepareStatement(query);
            pst.setString(1, proprietario);
            pst.setString(2, numeroCarta);
            pst.setString(3, dataScadenza);
            pst.setInt(4, cvv);
            pst.setInt(5, user);
            pst.executeUpdate();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            }
        return false;
	}
	
	public Card retriveCardById(int card_id) {
		Card p=new Card();
		try {

            query="SELECT * from Card where id=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, card_id);
            rs=pst.executeQuery();
            while(rs.next()) {
            	p.setId(rs.getInt("id"));
            	p.setProprietario(rs.getString("proprietario"));
            	p.setNumeroCarta(rs.getString("numeroCarta"));
            	p.setDataScadenza(rs.getString("dataScadenza"));
            	p.setCvv(rs.getInt("cvv"));
            	p.setUser(rs.getInt("user"));
            }
            return p;
			
        }catch(Exception e){
            e.printStackTrace();
        }
		return null;
	}
	
	public boolean exist(String proprietario, String data, String numero, int cvv, int user) {
		query= "SELECT * FROM Card WHERE proprietario = ? AND numero_carta = ? AND data_scadenza =? AND cvv = ? AND user = ?";
		try {
			pst=this.con.prepareStatement(query);
			pst.setString(1,proprietario);
			pst.setString(2,numero);
			pst.setString(3,data);
			pst.setInt(4,cvv);
			pst.setInt(5,user);
			rs=pst.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
		
	}
	
}
