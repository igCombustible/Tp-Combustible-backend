package ar.edu.unq.grupo4.combustible.dto;


import java.util.List;


import ar.edu.unq.grupo4.combustible.model.Usuario;
import ar.edu.unq.grupo4.combustible.model.UsuarioRol;

public class UsuarioDto {
	private String id;
	private String name;
	private String email;
	private List<String> roles;
	
	
	public UsuarioDto(Usuario usuario) {
        this.email = usuario.getEmail();
        this.name = usuario.getName();
        this.id = usuario.getId();
        this.roles = usuario.getRoles();
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	

	
}
