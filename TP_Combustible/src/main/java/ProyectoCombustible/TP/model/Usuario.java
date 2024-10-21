package ProyectoCombustible.TP.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Usuario", schema = "combustible")
public class Usuario {

	@Id
    private String id ;
    private String name;
    private String email;
    private String password;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UsuarioRol> usuarioRoles;

    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public Usuario() {
	}
	
	public Usuario(String id, String name, String email, String password, Set<UsuarioRol> usuarioRoles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.usuarioRoles = usuarioRoles;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<UsuarioRol> getUsuarioRoles() {
		return usuarioRoles;
	}
	public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}
	
	public List<String> getRoles(){
		return this.getUsuarioRoles().stream().map(x -> x.getRol().getName()).collect(Collectors.toList());
	}
}
