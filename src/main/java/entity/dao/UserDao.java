package entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			query="SELECT * FROM utente WHERE email=? AND password=?";
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
				user.setIsAdmin(rs.getInt("is_admin"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user;
	}
	
	public boolean retrivebyMail(String email) {
		try {
			query="SELECT * FROM utente WHERE email=?";
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
	
	
	
	
	
	public User userRec(String nome,String cognome,String email,String password) {
        User user=new User();
        try {
            int rSet;
            PreparedStatement pSte;
            query="INSERT INTO utente VALUES(0,0,?,?,?,?)";
            pSte=con.prepareStatement(query);
            pSte.setString(1,email);
            pSte.setString(2,password);
            pSte.setString(3,nome);
            pSte.setString(4,cognome);
            rSet=pSte.executeUpdate();
            if(rSet>0) {
                user.setName(nome);
                user.setCognome(cognome);
                user.setEmail(email);
                user.setPassword(password);
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
}
