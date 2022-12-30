package entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.model.Card;
import entity.model.DettaglioOrdine;

public class DettaglioOrdineDAO {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public DettaglioOrdineDAO(Connection con) {
		this.con=con;
	}
	
	public DettaglioOrdineDAO() {
	}
	
	
	public List<DettaglioOrdine> getAllDettaglioOrdini(){
		List<DettaglioOrdine> dettagliOrdini=new ArrayList<DettaglioOrdine>();
		try {
			query="SELECT * FROM DettaglioOrdine";
			//Connection c = DbCon.getConnection();
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				DettaglioOrdine row=new DettaglioOrdine();
				row.setCappello(rs.getInt("cappello"));
				row.setQuantita(rs.getInt("quantita"));
				row.setOrdine(rs.getInt("ordine"));
				dettagliOrdini.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dettagliOrdini;
	}
	
	public void insertDettaglioOrdine(int cappelloId,int quantita, int ordineId) {
		DettaglioOrdine dettOrder= new DettaglioOrdine(); // Creo la DettaglioOrdine per fargli generare automaticamente l'id corretto da inserire nella query
        try {
            query="INSERT INTO DettaglioOrdine(cappello,ordine,quantita)"
            		+ " VALUES(?,?,?)";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, cappelloId);
            pst.setInt(2, ordineId);
            pst.setInt(3, quantita);
            pst.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            }
	}
	
//	private Restituisce i dettagli ordini contenenti L'id dell'ordine preso in input
	public DettaglioOrdine searchDettaglioOrdineByOrdineId(int ordine_id) {
		DettaglioOrdine dettOrd=new DettaglioOrdine();
		try {

            query="SELECT * from DettaglioOrdine where ordine=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, ordine_id);
            rs=pst.executeQuery();
            while(rs.next()) {
            	dettOrd.setCappello(rs.getInt("cappello"));
            	dettOrd.setQuantita(rs.getInt("quantita"));
            	dettOrd.setOrdine(rs.getInt("ordine"));
            }
            return dettOrd;
			
        }catch(Exception e){
            e.printStackTrace();
        }
		return null;
	}


//	private Restituisce i dettagli ordini contenenti L'id dell'user preso in input
	public DettaglioOrdine searchDettaglioOrdineByUserId(int user_id) {
		DettaglioOrdine dettOrd=new DettaglioOrdine();
		try {

            query="SELECT * from DettaglioOrdine where user=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, user_id);
            rs=pst.executeQuery();
            while(rs.next()) {
            	dettOrd.setCappello(rs.getInt("cappello"));
            	dettOrd.setQuantita(rs.getInt("quantita"));
            	dettOrd.setOrdine(rs.getInt("ordine"));
            }
            return dettOrd;
			
        }catch(Exception e){
            e.printStackTrace();
        }
		return null;
	}
	
}
