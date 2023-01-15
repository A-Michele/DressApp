package entity.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.model.Cappello;

public class CappelloDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public CappelloDao(Connection con) {
		this.con=con;
	}
	
	public CappelloDao() {
	}


	public List<Cappello> getAllProducts(){
		List<Cappello> products=new ArrayList<Cappello>();
		try {
			query="SELECT * FROM cappello WHERE modificato=false";
			//Connection c = DbCon.getConnection();
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Cappello row=new Cappello();
				row.setId(rs.getInt("id"));
				row.setNome(rs.getString("nome"));
				row.setCategoria(rs.getString("categoria"));
				row.setPrezzo(rs.getFloat("prezzo"));
				row.setFoto(rs.getString("foto"));
				row.setDescrizione(rs.getString("descrizione"));
				row.setDisp(rs.getInt("disponibilita"));
				row.setModificato(rs.getBoolean("modificato"));
				products.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	//------------------------------------
	// Metodi CRUD:
	
	public ArrayList<Cappello> searchItems(String s){
        ArrayList<Cappello> products=new ArrayList<Cappello>();
        try {
            query="SELECT * FROM Cappello WHERE nome LIKE '%"+s+"%' AND modificato=false";
            pst=con.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next()) {
                Cappello p =new Cappello();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getFloat("prezzo"));
                p.setCategoria(rs.getString("categoria"));
                p.setFoto(rs.getString("foto"));
                p.setDisp(rs.getInt("disponibilita"));
                p.setModificato(rs.getBoolean("modificato"));
                products.add(p);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return products;
    }
	
	public void removeProduct(int p_id) {
		query="DELETE FROM Cappello WHERE id=" + p_id;
			 try {
				pst=this.con.prepareStatement(query);
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}		 
	}
	
	public void insertProduct(String nome,String descrizione,float costo,String categoria,String foto, int disp) {
        try {
            query="INSERT INTO Cappello(id,nome,descrizione,prezzo,categoria,foto,disponibilita,modificato)"
            		+ " VALUES(?,?,?,?,?,?,?,?)";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, 0);
            pst.setString(2, nome);
            pst.setString(3, descrizione);
            pst.setDouble(4, costo);
            pst.setString(5, categoria);
            pst.setString(6, foto);
            pst.setInt(7, disp);
            pst.setBoolean(8, false);
            pst.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
            }
	}

	public Cappello retriveProductById(int p_id) {
		Cappello p=new Cappello();
		try {

            query="SELECT * from Cappello where id=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, p_id);
            rs=pst.executeQuery();
            while(rs.next()) {
            	p.setId(rs.getInt("id"));
            	p.setNome(rs.getString("nome"));
            	p.setDescrizione(rs.getString("descrizione"));
            	p.setPrezzo(rs.getFloat("prezzo"));
            	p.setCategoria(rs.getString("categoria"));
            	p.setFoto(rs.getString("foto"));
            	p.setDisp(rs.getInt("disponibilita"));
            	p.setModificato(rs.getBoolean("modificato"));
            }
            return p;
			
        }catch(Exception e){
            e.printStackTrace();
        }
		return null;
	}

	public boolean updateProdotto(int id, String nome, String desc, float costo, String categoria, String foto,
			int dispo, Boolean modificato) {
		try {
            query="UPDATE Cappello SET nome=?, descrizione=?, prezzo=?, categoria=?,foto=?,disponibilita=?,modificato=? WHERE id=?";
            pst=this.con.prepareStatement(query);
            pst.setString(1, nome);
            pst.setString(2, desc);
            pst.setFloat(3, costo);
            pst.setString(4, categoria);
            pst.setString(5, foto);
            pst.setInt(6, dispo);
            pst.setBoolean(7, modificato);
            pst.setInt(8, id);
            pst.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
		
		return false;
	}

	public void updateDisp(int id, int dispo) {
		try {
            query="UPDATE Cappello SET disponibilita=? WHERE id=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, dispo);
            pst.setInt(2, id);
            pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
		
	}
	
	public void updateModificato(int id) {
		try {
            query="UPDATE Cappello SET modificato=true WHERE id=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	public void riduciDisponibilita(int p_id, int quantita) {
		try {
            query="UPDATE Cappello SET disponibilita=? WHERE id=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1, quantita);
            pst.setInt(2, p_id);
            pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
		
	}
}
