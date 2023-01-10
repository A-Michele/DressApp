package entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.model.DettaglioOrdine;
import entity.model.Ordine;
import entity.model.OrdineCompleto;

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
	public ArrayList<DettaglioOrdine> searchDettaglioOrdineByOrdineId(int ordine_id) {
		ArrayList<DettaglioOrdine> dettagliOrdine= null;
		try {
			dettagliOrdine =new ArrayList<DettaglioOrdine>();
            query="SELECT * from DettaglioOrdine where ordine=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, ordine_id);
            rs=pst.executeQuery();
            
            while(rs.next()) {
        		DettaglioOrdine dettOrd=new DettaglioOrdine();
            	dettOrd.setCappello(rs.getInt("cappello"));
            	dettOrd.setQuantita(rs.getInt("quantita"));
            	dettOrd.setOrdine(rs.getInt("ordine"));
            	dettagliOrdine.add(dettOrd);
            }
            
            return dettagliOrdine;
			
        }catch(Exception e){
            e.printStackTrace();
        }
		
		return dettagliOrdine;
	}

	
//	private Restituisce i dettagli ordini contenenti L'id dell'ordine preso in input
	public ArrayList<OrdineCompleto> searchOrdiniCompleti(int ordineId) {
		OrdineCompleto dettOrd=null;
		ArrayList<OrdineCompleto> tuttidett= new ArrayList<OrdineCompleto>();
		try {
            query="SELECT user.email, cappello.nome, cappello.categoria, cappello.prezzo, dettaglioordine.quantita, ordine.data FROM user JOIN ordine  JOIN dettaglioordine JOIN cappello WHERE user.id=ordine.user AND ordine.id=dettaglioordine.ordine AND dettaglioordine.cappello=cappello.id AND ordine.is_buy=1 AND ordine.id=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1,ordineId);
            rs=pst.executeQuery();
            
            while(rs.next()) {
            	dettOrd=new OrdineCompleto();
            	dettOrd.setEmail(rs.getString("email"));
            	dettOrd.setNomeC(rs.getString("nome"));
            	dettOrd.setCategoria(rs.getString("categoria"));
            	dettOrd.setPrezzo(rs.getFloat("prezzo"));
            	dettOrd.setQuantita(rs.getInt("quantita"));
            	dettOrd.setData(rs.getDate("data"));
            	
            	tuttidett.add(dettOrd);
            	
            }
           
            return tuttidett;
			
        }catch(Exception e){
            e.printStackTrace();
        }
		
		return tuttidett;
	}
	
	/*
	 * public ArrayList<Ordine> searchOrder(String nomeCappello){ ArrayList<Ordine>
	 * ordini= new ArrayList<Ordine>(); try {
	 * query="SELECT user.email, cappello.nome, cappello.categoria, cappello.prezzo, dettaglioordine.quantita,ordine.data FROM user JOIN ordine JOIN dettaglioordine JOIN cappello WHERE cappello.id=dettaglioordine.cappello AND ordine.id=dettaglioordine.ordine AND ordine.user=user.id AND cappello.nome= ?"
	 * ; pst=this.con.prepareStatement(query); pst.setString(1,nomeCappello);
	 * rs=pst.executeQuery(); while(rs.next()) { ordine.setId(rs.getInt("id"));
	 * ordine.setData(rs.getDate("data")); ordine.setUser(rs.getInt("user"));
	 * ordine.setIsBuy(rs.getBoolean("is_buy")); } return ordine;
	 * 
	 * }catch(Exception e){ e.printStackTrace(); } return null; }
	 */



	public void updateQuantita(int quantita,int p_id, int ordine_id) {
		try {
            query="UPDATE DettaglioOrdine SET quantita=? WHERE ordine=? AND cappello=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, quantita);
            pst.setInt(2, ordine_id);
            pst.setInt(3, p_id);
            pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
		
	}
	
	public int getQuantita(int p_id, int o_id) {
		DettaglioOrdine dOrdine=new DettaglioOrdine();
		
		try {
            query="SELECT quantita from DettaglioOrdine where cappello=? AND ordine=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1,p_id);
            pst.setInt(2,o_id);
            rs=pst.executeQuery();
            while(rs.next()) {
            	dOrdine.setQuantita(rs.getInt("quantita"));
            }
            	return dOrdine.getQuantita();
           
        }catch(Exception e){
            e.printStackTrace();
        }
		return -1;
	}

	public void removeById(int p_id, int o_id) {
		query="DELETE FROM dettaglioordine WHERE cappello=? AND ordine=?";
		 try {
			pst=this.con.prepareStatement(query);
			pst.setInt(1,p_id);
			pst.setInt(2,o_id);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
		
			 

	
	
		
		
		
	}
	
