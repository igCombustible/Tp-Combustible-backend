package ar.edu.unq.grupo4.combustible.dto;

import java.util.List;

import ar.edu.unq.grupo4.combustible.model.Ticket;

public class InfoVehiculoDto {
	private String patente;
	private String marca;
	private String modelo;
	private Integer km;
	private Integer consumo;
	private Double promedioKilometros;
	private List<Ticket> tickets;
	
	public InfoVehiculoDto() {}
	
	public InfoVehiculoDto(String patente, String marca, String modelo,Integer km, Integer consumo, List<Ticket> tickets) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.km = km;
        this.consumo = consumo;
        this.promedioKilometros = (double) (km/consumo);
        this.tickets = tickets;
    }
	
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
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
	public Integer getConsumo() {
		return consumo;
	}
	public void setConsumo(Integer consumo) {
		this.consumo = consumo;
	}

	public Integer getKm() {
		return km;
	}

	public void setKm(Integer km) {
		this.km = km;
	}

	public Double getPromedioKilometros() {
		return promedioKilometros;
	}

	public void setPromedioKilometros(Double promedioKilometros) {
		this.promedioKilometros = promedioKilometros;
	}
}
