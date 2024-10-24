package ProyectoCombustible.TP.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProyectoCombustible.TP.Repository.VehiculoRepository;
import ProyectoCombustible.TP.dto.VehiculoDto;
import ProyectoCombustible.TP.model.Vehiculo;


@Service
public class VehiculoService {
	
	@Autowired
	private VehiculoRepository repository;

	public VehiculoDto editarVehiculo(String id, Vehiculo vehiculo) {
		Optional<Vehiculo> unVehiculo = this.repository.findById(id);
		unVehiculo.get().setMarca(vehiculo.getMarca());
		unVehiculo.get().setModelo(vehiculo.getModelo());
		unVehiculo.get().setPatente(vehiculo.getPatente());
		unVehiculo.get().setEstado(vehiculo.getEstado());
		unVehiculo.get().setUltimoValorConocidoKm(vehiculo.getUltimoValorConocidoKm());
		VehiculoDto vehiculoDto = new VehiculoDto(unVehiculo); 
		return vehiculoDto;
		
	}
	
	
}
