package ar.edu.unq.grupo4.combustible.dto;

import java.util.Optional;

import ar.edu.unq.grupo4.combustible.model.Vehiculo;


public class VehiculoDto {
	private String marca;
	private String modelo;
	private Integer ultimoValorConocidoKm;
	private Boolean estado_vehiculo;
	private String patente;
	
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
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
	public Integer getUltimoValorConocidoKm() {
		return ultimoValorConocidoKm;
	}
	public void setUltimoValorConocidoKm(Integer ultimoValorConocidoKm) {
		this.ultimoValorConocidoKm = ultimoValorConocidoKm;
	}
	public Boolean getEstado_vehiculo() {
		return estado_vehiculo;
	}
	public void setEstado_vehiculo(Boolean estado_vehiculo) {
		this.estado_vehiculo = estado_vehiculo;
	}
	
	public VehiculoDto(Optional<Vehiculo> vehiculo) {
		this.patente = vehiculo.get().getPatente();
		this.marca = vehiculo.get().getMarca();
		this.modelo = vehiculo.get().getModelo();
		this.ultimoValorConocidoKm = vehiculo.get().getUltimoValorConocidoKm();
		this.estado_vehiculo = vehiculo.get().getEstado_vehiculo();
	}
	public VehiculoDto() {
		// TODO Auto-generated constructor stub
	}
	
}
