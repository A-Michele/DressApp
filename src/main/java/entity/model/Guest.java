package entity.model;

public class Guest {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Guest(int id) {
		this.id = id;
	}
	
	public Guest() {
	}

	public String toString() {
		return "Guest [id=" + id + "]";
	}
	
}
