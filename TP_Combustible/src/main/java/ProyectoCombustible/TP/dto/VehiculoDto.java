package ProyectoCombustible.TP.dto;

import java.util.Optional;

import ProyectoCombustible.TP.model.Vehiculo;

public class VehiculoDto {
	private String marca;
	private String modelo;
	private String patente;
	private Boolean estado;
	private Integer ultimoValorConocidoKm;

	public VehiculoDto(Optional<Vehiculo> vehiculo) {
		super();
		this.marca = vehiculo.get().getMarca();
		this.modelo = vehiculo.get().getModelo();
		this.patente = vehiculo.get().getPatente();
		this.estado = vehiculo.get().getEstado();
		this.ultimoValorConocidoKm = vehiculo.get().getUltimoValorConocidoKm();
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

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getUltimoValorConocidoKm() {
		return ultimoValorConocidoKm;
	}

	public void setUltimoValorConocidoKm(Integer ultimoValorConocidoKm) {
		this.ultimoValorConocidoKm = ultimoValorConocidoKm;
	}
	
}
