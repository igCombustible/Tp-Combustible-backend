package ar.edu.unq.grupo4.combustible.dto;

import ar.edu.unq.grupo4.combustible.model.Vehiculo;

public class InfoVehiculoDto {
	private String patente;
	private String marca;
	private String modelo;
	private Integer km;
	private Double consumo;
	private Double kmPromedio;
	
	public InfoVehiculoDto (String patente, String marca, String modelo, Integer km , Double consumo) {
		this.patente = patente;
		this.marca = marca;
		this.modelo = modelo;
		this.km = km;
		this.consumo = consumo;
		this.kmPromedio = km / consumo;
	}
	
	public Double getKmPromedio() {
		return kmPromedio;
	}

	public void setKmPromedio(Double kmPromedio) {
		this.kmPromedio = kmPromedio;
	}

	public InfoVehiculoDto() {}
	
	public InfoVehiculoDto(Vehiculo vehiculo, Double consumo) {
		this.patente = vehiculo.getPatente();
		this.marca = vehiculo.getMarca();
		this.modelo = vehiculo.getModelo();
		this.km = vehiculo.getUltimoValorConocidoKm();
		this.consumo = consumo;
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
	public Double getConsumo() {
		return consumo;
	}
	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}
}
