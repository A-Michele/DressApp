package entity.model;

import java.io.Serializable;

public class Card implements Serializable{
	private int id;
	private String  proprietario;
	private String numeroCarta;
	private String dataScadenza;
	private int cvv;
	private int user;
	
	
	public Card(String proprietario, String numeroCarta, String dataScadenza, int cvv, int user) {
		this.proprietario = proprietario;
		this.numeroCarta = numeroCarta;
		this.dataScadenza = dataScadenza;
		this.cvv = cvv;
		this.user = user;
	}

	public Card() {
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", proprietario=" + proprietario + ", numeroCarta=" + numeroCarta + ", dataScadenza="
				+ dataScadenza + ", cvv=" + cvv + ", user=" + user + "]";
	}
	
	
	
}
