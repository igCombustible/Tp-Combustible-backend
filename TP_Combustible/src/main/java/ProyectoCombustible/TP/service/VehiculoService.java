package ProyectoCombustible.TP.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProyectoCombustible.TP.Repository.VehiculoRepository;
import ProyectoCombustible.TP.dto.VehiculoDto;
import ProyectoCombustible.TP.model.Vehiculo;

import java.util.List;


@Service
public class VehiculoService {
	 	@Autowired
	    private VehiculoRepository vehiculoRepository;

	    public List<Vehiculo> findAll() {
	        return vehiculoRepository.findAll();
	    }

	    public Optional<Vehiculo> findByPatente(String patente) {
	        return vehiculoRepository.findById(patente);
	    }

	    public Vehiculo save(Vehiculo vehiculo) {
	        return vehiculoRepository.save(vehiculo);
	    }

	    public void delete(String patente) {
	        vehiculoRepository.deleteById(patente);
	    }
	    
	    public VehiculoDto editarVehiculo(String patente, Vehiculo vehiculo) {
			Optional<Vehiculo> unVehiculo = this.findByPatente(patente);
			unVehiculo.get().setMarca(vehiculo.getMarca());
			unVehiculo.get().setModelo(vehiculo.getModelo());
			unVehiculo.get().setEstado_vehiculo(vehiculo.getEstado_vehiculo());
			unVehiculo.get().setUltimoValorConocidoKm(vehiculo.getUltimoValorConocidoKm());
			
			this.vehiculoRepository.save(unVehiculo.get());
			
			VehiculoDto vehiculoDto = new VehiculoDto(unVehiculo); 
			return vehiculoDto;
			
		}
	    
	    
}

