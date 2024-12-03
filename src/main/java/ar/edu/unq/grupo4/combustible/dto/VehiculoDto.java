package ar.edu.unq.grupo4.combustible.dto;

import java.util.Optional;

import ar.edu.unq.grupo4.combustible.model.Vehiculo;

public class VehiculoDto {
	private String patente;
	private String marca;
	private String modelo;
	private Integer km;
	
	public VehiculoDto(Optional<Vehiculo> unVehiculo) {}
	
	public VehiculoDto(String patente, String marca, String modelo,Integer km, Double consumo) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
    }
	
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

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}
}