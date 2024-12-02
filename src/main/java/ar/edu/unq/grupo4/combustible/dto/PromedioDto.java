package ar.edu.unq.grupo4.combustible.dto;

public class PromedioDto {
	VehiculoDto vehiculoDto;
	Double promedio;
	
	public PromedioDto(){};
	public PromedioDto(VehiculoDto vehiculo, Double promedio) {
		this.vehiculoDto = vehiculo;
		this.promedio = promedio;
	}
	public VehiculoDto getVehiculoDto() {
		return vehiculoDto;
	}
	public void setVehiculoDto(VehiculoDto vehiculoDto) {
		this.vehiculoDto = vehiculoDto;
	}
	public Double getPromedio() {
		return promedio;
	}
	public void setPromedio(Double promedio) {
		this.promedio = promedio;
	}
}
