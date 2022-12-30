package entity.model;

public class Card {
	private int id;
	private static int idStatico= 0;
	private String  proprietario;
	private String numeroCarta;
	private String dataScadenza;
	private int cvv;
	private int user;
	
	public Card(String proprietario, String numeroCarta, String dataScadenza, int cvv, int user) {
		super();
		this.id=idStatico++;
		this.proprietario = proprietario;
		this.numeroCarta = numeroCarta;
		this.dataScadenza = dataScadenza;
		this.cvv = cvv;
		this.user = user;
	}

	public Card() {
		this.id=idStatico++;
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

	@Override
	public String toString() {
		return "Card [id=" + id + ", proprietario=" + proprietario + ", numeroCarta=" + numeroCarta + ", dataScadenza="
				+ dataScadenza + ", cvv=" + cvv + ", user=" + user + "]";
	}
	
	
	
}
