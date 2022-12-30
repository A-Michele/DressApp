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
	
	public List<Card> getAllProducts(){
		List<Card> products=new ArrayList<Card>();
		try {
			query="SELECT * FROM card";
			//Connection c = DbCon.getConnection();
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Card row=new Card();
				row.setId(rs.getInt("id"));
				row.setProprietario(rs.getString("proprietario"));
				row.setNumeroCarta(rs.getString("numeroCarta"));
				row.setDataScadenza(rs.getString("dataScadenza"));
				row.setCvv(rs.getInt("cvv"));
				row.setUser(rs.getInt("user"));
				products.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;
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
	
	
	public void insertCard(String proprietario,String numeroCarta,String dataScadenza,int cvv, int user) {
		Card p= new Card(); // Creo la card per fargli generare automaticamente l'id corretto da inserire nella query
        try {
            query="INSERT INTO Cappello(id,proprietario,numeroCarta,dataScadenza,cvv,user)"
            		+ " VALUES(?,?,?,?,?,?,?,?)";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, p.getId());
            pst.setString(2, proprietario);
            pst.setString(3, numeroCarta);
            pst.setString(4, dataScadenza);
            pst.setInt(5, cvv);
            pst.setInt(6, user);
            pst.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            }
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
	
}
