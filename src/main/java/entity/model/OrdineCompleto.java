package entity.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
public class OrdineCompleto implements Serializable{
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
	@Override
	public int hashCode() {
		return Objects.hash(categoria, data, email, nomeC, prezzo, quantita);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdineCompleto other = (OrdineCompleto) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(data, other.data)
				&& Objects.equals(email, other.email) && Objects.equals(nomeC, other.nomeC)
				&& Float.floatToIntBits(prezzo) == Float.floatToIntBits(other.prezzo) && quantita == other.quantita;
	}
	
	
	
	
}
