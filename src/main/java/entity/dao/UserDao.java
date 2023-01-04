package entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.model.User;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDao(Connection con) {
		this.con = con;
	}
	
	public User userLogin(String email,String password) {
		User user=null;
		try {
			query="SELECT * FROM User WHERE email=? AND password=?";
			pst= this.con.prepareStatement(query);
			pst.setString(1,email);
			pst.setString(2,password);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				user=new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setIsAdmin(rs.getInt("is_Admin"));
				user.setIsGuest(rs.getInt("is_Guest"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user;
	}
	
	//--------------------------------------------------------------------------------
	//METODI CRUD (Create,Retrive,Update,Delete):
	
	public boolean retrivebyMail(String email) {
		try {
			query="SELECT * FROM User WHERE email=?";
			pst= this.con.prepareStatement(query);
			pst.setString(1,email);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return false;
	}
	
	
	
	
	// Metodo Create per il salvataggio del nuovo user
	public User userRec(String nome,String cognome,String email,String password, int is_Admin, int is_Guest) {
        User user=new User();
        try {
            int rSet;
            PreparedStatement pSte;
            query="INSERT INTO User VALUES(?,?,?,?,?,?,?)";
            pSte=con.prepareStatement(query);
            pSte.setInt(1,0);
            pSte.setString(2,email);
            pSte.setString(3,password);
            pSte.setString(4,nome);
            pSte.setString(5,cognome);
            pSte.setInt(6, is_Admin);
            pSte.setInt(7, is_Guest);
            rSet=pSte.executeUpdate();
            if(rSet>0) {
                user.setName(nome);
                user.setCognome(cognome);
                user.setEmail(email);
                user.setPassword(password);
                user.setIsAdmin(is_Admin);
                user.setIsAdmin(is_Guest);
            }
            else{
                return user=null;
            }
        }catch(Exception e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        return user;
    }
	
	
	//Metodo per cancellare l'user in base alla sua email --> Ancora non testato
	public boolean doDelete(String string){
		
		try{
			String query = "DELETE FROM User WHERE email = ?";
		pst= con.prepareStatement(query);
		pst.setString(1, string);
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
	
	
	public boolean doDeleteById(int id){
		
		try{
			String query = "DELETE FROM User WHERE id = ?";
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
	
	//Metodo per fare UPDATE dell'user all'interno del db.. Attributo è l'attributo dell'entità, valore è il nuovo valore che vogliamo inserire
	public void doUpdate(User utente,String attributo, String valore) throws SQLException {
		
		String query = "UPDATE User set "+attributo+"=? where email= ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, valore);
		ps.setString(2, utente.getEmail());
		ps.executeUpdate();
	}
	
	public boolean doUpdateById(int id,String attributo, String valore) throws SQLException {
		
		try{
			String query = "UPDATE User set "+attributo+"=? where id= ?";
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
