package ProyectoCombustible.TP.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="roles", schema="combustible")
public class Roles {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;
	
	
	@Column (name="name")
    private String name;
	
	@Column(name="description")
    private String description;
	
	@ManyToMany
	@JoinTable(name = "usuario_roles",
	joinColumns = @JoinColumn(name = "roles_id"),
	inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	
	private Set<Usuario> usuarios;


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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    

}
