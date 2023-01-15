package entity.model;

public class User {
	private int id;
	private String name;
	private String cognome;
	private String email;
	private String password;
	private int is_Admin;
	private int is_Guest;
	
	public User(String name, String cognome, String email, String password, int isAdmin, int isGuest) {
		this.name = name;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.is_Admin = isAdmin;
		this.is_Guest = isGuest;
	}
	

	public User() {
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
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
		return is_Admin;
	}
	
	public int getIsGuest() {
		return is_Guest;
	}
	
	public void setIsGuest(int IsGuest) {
		this.is_Guest = IsGuest;
	}
	public void setIsAdmin(int isAdmin) {
		this.is_Admin = isAdmin;
	}
	
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", cognome=" + cognome + ", email=" + email + ", password="
				+ password + ", isAdmin=" + is_Admin + ", isGuest=" + is_Guest + "]";
	}
	
	@Override
    	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(cognome, other.cognome) && Objects.equals(email, other.email) && id == other.id
                && is_Admin == other.is_Admin && is_Guest == other.is_Guest && Objects.equals(name, other.name)
                && Objects.equals(password, other.password);
    }
}
