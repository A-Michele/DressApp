package entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PaymentDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public PaymentDao(Connection con) {
		this.con=con;
	}
	
	public boolean insertPayment(int u_id,String proprietario, String scadenza, String num, int cvi) {
		boolean result=false;
		try {
			query="INSERT INTO pagamento (id_utente,proprietario,numero_carta,data_scadenza,cvi) VALUES(?,?,?,?,?)";
			pst=this.con.prepareStatement(query);
			pst.setInt(1,u_id);
			pst.setString(2,proprietario);
			pst.setString(3,num);
			pst.setString(4,scadenza);
			pst.setInt(5,cvi);
			pst.executeUpdate();
			result=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
