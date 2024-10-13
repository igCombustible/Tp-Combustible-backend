package ProyectoCombustible.TP.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Usuario", schema = "combustible")


public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UsuarioRoles> usuarioRoles = new HashSet<>();;

    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public Usuario() {
	}
	
	public Usuario(int id, String name, String email, String password, Set<UsuarioRoles> usuarioRoles) {
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
	public Set<UsuarioRoles> getUsuarioRoles() {
		return usuarioRoles;
	}
	public void setUsuarioRoles(Set<UsuarioRoles> usuarioRoles) {
		this.usuarioRoles = usuarioRoles;
	}

}
