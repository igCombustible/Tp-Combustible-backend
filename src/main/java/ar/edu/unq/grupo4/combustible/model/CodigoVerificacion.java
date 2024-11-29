package ar.edu.unq.grupo4.combustible.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "codigo_verificacion", schema = "combustible")
public class CodigoVerificacion {
	@Id
	private String id; 
	private String email;
	private int codigo_verificacion;
	private Date fecha_de_solicitud;
	private Date expiracion;
	
	public CodigoVerificacion(String id, String email, int codigo_verificacion,
			Date expiracion) {
		super();
		this.id = id;
		this.email = email;
		this.codigo_verificacion = codigo_verificacion;
		this.expiracion = expiracion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCodigo_verificacion() {
		return codigo_verificacion;
	}

	public void setCodigo_verificacion(int codigo_verificacion) {
		this.codigo_verificacion = codigo_verificacion;
	}

	public Date getFecha_de_solicitud() {
		return fecha_de_solicitud;
	}

	public void setFecha_de_solicitud(Date fecha_de_solicitud) {
		this.fecha_de_solicitud = fecha_de_solicitud;
	}

	public Date getExpiracion() {
		return expiracion;
	}

	public void setExpiracion(Date expiracion) {
		this.expiracion = expiracion;
	}
	
	public CodigoVerificacion() {
	}
	
	
}
