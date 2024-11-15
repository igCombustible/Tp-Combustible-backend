package ar.edu.unq.grupo4.combustible.model;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@SQLDelete(sql = "UPDATE vehiculo SET deleted=true WHERE patente = ?")
@FilterDef(
		name = "deletedCarFilter",
	    parameters = @ParamDef(name = "isDeleted", type = Boolean.class)
)
@Filter(
		name = "deletedCarFilter",
		condition = "deleted = :isDeleted"
)
public class Vehiculo {
	@Id
	private String patente;
	
	private String marca;
	private String modelo;
	@Column(name="ultimo_km")
	private Integer ultimoValorConocidoKm;
	private Boolean deleted;
	private Boolean estado_vehiculo;


	public Boolean getEstado_vehiculo() {
		return estado_vehiculo;
	}

	public void setEstado_vehiculo(Boolean estado_vehiculo) {
		this.estado_vehiculo = estado_vehiculo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Integer getUltimoValorConocidoKm() {
		return ultimoValorConocidoKm;
	}

	public void setUltimoValorConocidoKm(Integer ultimoValorConocidoKm) {
		this.ultimoValorConocidoKm = ultimoValorConocidoKm;
	}
	

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Vehiculo(String marca, String modelo, String patente, Integer ultimoValorConocidoKm, Boolean estado) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.patente = patente;
		this.ultimoValorConocidoKm = ultimoValorConocidoKm;
		this.estado_vehiculo = estado;
	
	}
	
	public Vehiculo() {
		
	}

}
