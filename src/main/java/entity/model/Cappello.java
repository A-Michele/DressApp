package entity.model;

import java.sql.Date;

public class Cappello {
	
	private int id;
	private String nome;
	private String categoria;
	private float prezzo;
	private String foto;
	private String descrizione;
	private int disp;
	private boolean modificato;
	

	public Cappello(String nome, String cateogria, float prezzo, String foto, String descrizione, int disp) {
		this.nome = nome;
		this.categoria = cateogria;
		this.prezzo = prezzo;
		this.foto=foto;
		this.descrizione=descrizione;
		this.disp=disp;
		this.modificato=false;
	}

	public Cappello() {
	}

	public int getDisp() {
		return disp;
	}

	public void setDisp(int disp) {
		this.disp = disp;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String cateogria) {
		this.categoria = cateogria;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String img) {
		this.foto = img;
	}
	
	public boolean getModificato() {
		return modificato;
	}
	
	public void setModificato(boolean modificato) {
		this.modificatoa=modificato;
	}

	@Override
	public String toString() {
		return "Cappello [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", prezzo=" + prezzo + ", img="
				+ foto + ", descrizione=" + descrizione + ", disp=" + disp + ", modificato="+ modificato +"]";
	}
}
