package ProyectoCombustible.TP.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vehiculo {
	@Id
	private String id;
	
	private String marca;
	private String modelo;
	private String patente;
	
	private Integer ultimoValorConocidoKm;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Vehiculo(String id, String marca, String modelo, String patente, Integer ultimoValorConocidoKm) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.patente = patente;
		this.ultimoValorConocidoKm = ultimoValorConocidoKm;
	}
	
	
}
