package entity.model;

public class User {
	private int id;
	private String name;
	private String cognome;
	private String email;
	private String password;
	private boolean is_Admin;
	private static int idStatico=0;
	
		public User( String name, String cognome, String email, String password, boolean isAdmin) {
		id=idStatico++;
		this.name = name;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.is_Admin = isAdmin;
	}

	public User() {
		id=idStatico++;
		name=null;
		cognome=null;
		email=null;
		password=null;
		is_Admin=false;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int x) {
		id=x;
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

	public boolean getIsAdmin() {
		return is_Admin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.is_Admin = isAdmin;
	}
	
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", cognome=" + cognome + ", email=" + email + ", password="
				+ password + ", isAdmin=" + is_Admin + "]";
	}
	
}
