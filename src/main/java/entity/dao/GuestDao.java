package entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.model.Cappello;

public class GuestDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public GuestDao(Connection con) {
		this.con=con;
	}
	
	public boolean addGuest() {
		boolean result=false;
		try {
			query="INSERT INTO guest() VALUES()";
			pst=this.con.prepareStatement(query);
			pst.executeUpdate();
			result=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/*
	public int getNewId() {
		int id=-1;
		try {
			query="SELECT id FROM  guest ORDER BY id DESC LIMIT 1";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			if(rs.next()) {
				id=rs.getInt(1);
			}else id=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	*/
}
