package ar.edu.unq.grupo4.combustible.dto;

import java.util.List;




public class LoginDto {
	private String token;
	private List<String> roles;
	
	
	public LoginDto(String token, List<String> roles ) {
		super();
		this.token = token;
		this.roles = roles;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
