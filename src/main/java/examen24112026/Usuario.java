package examen24112026;

public class Usuario {
	
	protected String username;
	protected String password;
	
	
	public Usuario() {
		super();
	}


	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
