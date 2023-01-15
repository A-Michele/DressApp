package entity.model;
import java.sql.Date;
import java.util.Objects;

public class Ordine {
	private int id;
	private Date data;
	private int user;
	private boolean isBuy;


	public Ordine(Date data, int user, boolean isBuy) {
		this.data = data;
		this.user = user;
		this.isBuy = isBuy;
	}

	public Ordine() {}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
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
	
	@Override
   	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ordine other = (Ordine) obj;
        return Objects.equals(data, other.data) && id == other.id && isBuy == other.isBuy && user == other.user;
    }
}
