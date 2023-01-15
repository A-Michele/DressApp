package entity.model;


public class DettaglioOrdine{
	private int cappello;
	private int quantita;
	private int ordine;
	public DettaglioOrdine(int cappello, int quantita, int ordine) {
		this.cappello = cappello;
		this.quantita = quantita;
		this.ordine = ordine;
	}
	public DettaglioOrdine() {
	}
	public int getCappello() {
		return cappello;
	}
	public void setCappello(int cappello) {
		this.cappello = cappello;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public int getOrdine() {
		return ordine;
	}
	public void setOrdine(int ordine) {
		this.ordine = ordine;
	}
	@Override
	public String toString() {
		return "DettaglioOrdine [cappello=" + cappello + ", quantita=" + quantita + ", ordine=" + ordine + "]";
	}
	
	@Override
    	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DettaglioOrdine other = (DettaglioOrdine) obj;
        return cappello == other.cappello && ordine == other.ordine && quantita == other.quantita;
    }
}
