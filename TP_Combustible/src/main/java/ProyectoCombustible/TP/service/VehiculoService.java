package ProyectoCombustible.TP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProyectoCombustible.TP.Repository.VehiculoRepository;
import ProyectoCombustible.TP.model.Vehiculo;

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
}
