package ar.edu.unq.grupo4.combustible.dto;

import ar.edu.unq.grupo4.combustible.model.Rol;

public class RolDto {
	private String name;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RolDto(Rol rol) {
		super();
		this.name = rol.getName();
		this.setId(rol.getId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public RolDto() {
    }
	
	
}
