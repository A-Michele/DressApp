package entity.model;

public class Cart extends Product{
	private int id;
	private int id_utente;  /* puo' essere sia un utente che un guest*/
	private int is_guest=0;
	private int id_prodotto;
	private int quantita;
	private int is_buy=0;
	private String data;
	
	public String toString() {
		return "Cart [id=" + id + ", id_utente=" + id_utente + ", is_guest=" + is_guest + ", id_prodotto=" + id_prodotto
				+ ", quantita=" + quantita + ", is_buy=" + is_buy + ", data="+ data +"]";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_utente() {
		return id_utente;
	}

	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}

	public int getIs_guest() {
		return is_guest;
	}

	public void setIs_guest(int is_guest) {
		this.is_guest = is_guest;
	}

	public int getId_prodotto() {
		return id_prodotto;
	}

	public void setId_prodotto(int id_prodotto) {
		this.id_prodotto = id_prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public int getIs_buy() {
		return is_buy;
	}

	public void setIs_buy(int is_buy) {
		this.is_buy = is_buy;
	}

	public Cart() {
	}


}
