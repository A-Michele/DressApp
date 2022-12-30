package entity.model;

import java.sql.Date;

public class Cappello {
	
	private int id;
	private static int idStatico=0;
	private String nome;
	private String categoria;
	private float prezzo;
	private String foto;
	private String descrizione;
	private int disp;
	private Date data_ultima_modifica;
	

	public Cappello(String nome, String cateogria, float prezzo, String foto, String descrizione, int disp) {
		id=idStatico++;
		this.nome = nome;
		this.categoria = cateogria;
		this.prezzo = prezzo;
		this.foto=foto;
		this.descrizione=descrizione;
		this.disp=disp;
		data_ultima_modifica=new Date(System.currentTimeMillis());
	}

	public Cappello() {
		id=idStatico++;
		this.nome = null;
		this.categoria = null;
		this.prezzo = 0;
		this.foto=null;
		this.descrizione=null;
		this.disp=0;
		data_ultima_modifica=null;
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
	
	public void setId(int x) {
		id=x;
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

	public double getPrezzo() {
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
	
	public Date getDataUltimaModifica() {
		return data_ultima_modifica;
	}
	
	public void setDataUltimaModifica(Date data) {
		data_ultima_modifica=data;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", prezzo=" + prezzo + ", img="
				+ foto + ", descrizione=" + descrizione + ", disp=" + disp + ", data ultima modifica="+data_ultima_modifica+"]";
	}
}
