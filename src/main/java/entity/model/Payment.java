package entity.model;

public class Payment {
	private int id_utente;
	private String scadenza;
	private int cvi;
	private String proprietario;
	private String num;
	
	public Payment(int id_utente, String scadenza, int cvi, String proprietario, String num) {
		super();
		this.id_utente = id_utente;
		this.scadenza = scadenza;
		this.cvi = cvi;
		this.proprietario = proprietario;
		this.num = num;
	}

	public Payment() {
		super();
	}

	public int getId_utente() {
		return id_utente;
	}

	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}

	public String getScadenza() {
		return scadenza;
	}

	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}

	public int getCvi() {
		return cvi;
	}

	public void setCvi(int cvi) {
		this.cvi = cvi;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String toString() {
		return "Payment [id_utente=" + id_utente + ", scadenza=" + scadenza + ", cvi=" + cvi + ", proprietario="
				+ proprietario + ", num=" + num + "]";
	}
	
}
