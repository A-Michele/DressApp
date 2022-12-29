package entity.model;

public class Product {
	private int id;
	private String nome;
	private String categoria;
	private double prezzo;
	private String img;
	private String descrizione;
	private int disp;
	

	public Product(int id, String nome, String cateogria, double prezzo, String img, String descrizione, int disp) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = cateogria;
		this.prezzo = prezzo;
		this.img = img;
		this.descrizione=descrizione;
		this.disp=disp;
	}

	public Product() {
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
		this.id = id;
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

	public void setPrezzo(double string) {
		this.prezzo = string;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", prezzo=" + prezzo + ", img="
				+ img + ", descrizione=" + descrizione + ", disp=" + disp + "]";
	}
}
