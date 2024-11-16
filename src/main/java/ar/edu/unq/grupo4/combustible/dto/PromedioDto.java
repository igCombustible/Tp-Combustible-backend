package ar.edu.unq.grupo4.combustible.dto;

public class PromedioDto {
	private String patente;
	private String marca;
	private String modelo;
	private Integer km;
	private Double consumo;
	private Double kmPorLitroConsumido;
	
	public PromedioDto() {}
	
	public PromedioDto (ConsumoDto consumoDto, Double kmPromedio) {
		this.patente =consumoDto.getPatente();
		this.marca = consumoDto.getMarca();
		this.modelo = consumoDto.getModelo();
		this.km = consumoDto.getKm();
		this.consumo = consumoDto.getConsumo();
		this.kmPorLitroConsumido = kmPromedio;
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
	public Double getConsumo() {
		return consumo;
	}
	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}
	public Double getKmPorLitroConsumido() {
		return kmPorLitroConsumido;
	}
	public void setKmPorLitroConsumido(Double kmPorLitroConsumido) {
		this.kmPorLitroConsumido = kmPorLitroConsumido;
	}
	
}
