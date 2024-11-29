package ar.edu.unq.grupo4.combustible.dto;


import java.util.List;

import ar.edu.unq.grupo4.combustible.model.EstadoDelUsuario;
import ar.edu.unq.grupo4.combustible.model.EstadoPassword;
import ar.edu.unq.grupo4.combustible.model.Usuario;

public class UsuarioDto {
	private String id;
	private String name;
	private String email;
	private List<String> roles;
	private EstadoDelUsuario estado;
	private EstadoPassword estadop;
	
	
	public UsuarioDto(Usuario usuario) {
        this.email = usuario.getEmail();
        this.name = usuario.getName();
        this.id = usuario.getId();
        this.roles = usuario.getRoles();
        this.estado = usuario.getEstado();
        this.setEstadop(usuario.getEstadop());
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


	public EstadoDelUsuario getEstado() {
		return estado;
	}


	public void setEstado(EstadoDelUsuario estado) {
		this.estado = estado;
	}


	public EstadoPassword getEstadop() {
		return estadop;
	}


	public void setEstadop(EstadoPassword estadop) {
		this.estadop = estadop;
	}
	
	

	
}
