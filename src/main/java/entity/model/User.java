package entity.model;

public class User {
	private int id;
	private String name;
	private String cognome;
	private String email;
	private String password;
	private int isAdmin;
	private int isGuest;
	
	public User(int id, String name, String cognome, String email, String password, int isAdmin, int isGuest) {
		this.id = id;
		this.name = name;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isGuest = isGuest;
	}

	public User() {
	}

	public int getIsGuest() {
		return isGuest;
	}

	public void setIsGuest(int isGuest) {
		this.isGuest = isGuest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", cognome=" + cognome + ", email=" + email + ", password="
				+ password + ", isAdmin=" + isAdmin + ", isGuest=" + isGuest + "]";
	}
	
}
