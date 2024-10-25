package ProyectoCombustible.TP.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vehiculo {
	@Id
	private String patente;
	
	private String marca;
	private String modelo;
	@Column(name="ultimo_km")
	private Integer ultimoValorConocidoKm;
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
