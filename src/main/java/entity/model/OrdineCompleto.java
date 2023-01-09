package entity.model;

import java.sql.Date;
public class OrdineCompleto {
	private String email;
	private String nomeC;
	private String categoria;
	private float prezzo;
	private int quantita;
	private Date data;
	public OrdineCompleto(String email, String nomeC, String categoria, float prezzo, int quantita, Date data) {
		super();
		this.email = email;
		this.nomeC = nomeC;
		this.categoria = categoria;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.data = data;
	}
	public OrdineCompleto() {}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNomeC() {
		return nomeC;
	}
	public void setNomeC(String nomeC) {
		this.nomeC = nomeC;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Ordine Completo [email=" + email + ", nomeC=" + nomeC + ", categoria=" + categoria + ", prezzo=" + prezzo
				+ ", quantita=" + quantita + ", data=" + data + "]";
	}
	
	
	
	
}
