package entity.model;
import java.sql.Date;

public class Ordine {
	private int id;
	private static int idStatico=0;
	private Date data;
	private int user;
	private boolean isBuy;


	public Ordine(int id, Date data, int user, boolean isBuy) {
		this.id = idStatico++;
		this.data = data;
		this.user = user;
		this.isBuy = isBuy;
	}

	public Ordine() {
		this.id = idStatico++;
		this.data = null;
		this.user = 0;
		this.isBuy = false;
	}

	public int getId() {
		return id;
	}
	
	public Date getDate() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public Boolean getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(Boolean isBuy) {
		this.isBuy = isBuy;
	}

	@Override
	public String toString() {
		return "Ordine [id=" + id + ", data=" + data + ", user=" + user + ","
				+ " isBuy=" + isBuy + "]";
	}
}