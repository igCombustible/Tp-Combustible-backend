package ar.edu.unq.grupo4.combustible.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupo4.combustible.dto.ConsumoDto;
import ar.edu.unq.grupo4.combustible.dto.PromedioDto;
import ar.edu.unq.grupo4.combustible.dto.VehiculoDto;
import ar.edu.unq.grupo4.combustible.model.Vehiculo;
import ar.edu.unq.grupo4.combustible.repository.VehiculoRepository;
import jakarta.transaction.Transactional;


@Service
public class VehiculoService {
	 	@Autowired
	    private VehiculoRepository vehiculoRepository;
	 	@Autowired
	 	private TicketService ticketService;

	    public List<Vehiculo> findAll() {
	        return vehiculoRepository.findByDeletedFalse();
	    }

	    public Optional<Vehiculo> findByPatente(String patente) {
	        return vehiculoRepository.findByPatente(patente);
	    }
	    
	    
	    public ConsumoDto consumoVehiculo(String patente) {
	    	Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findByPatente(patente);
	    	if (vehiculoOptional.isEmpty()) {
	            throw new RuntimeException("Vehículo no encontrado para la patente: " + patente);
	    	}
	    	Vehiculo vehiculo = vehiculoOptional.get();
	    	Double consumo = ticketService.sumarCantidadDeSolicitudPorVehiculo(vehiculo);
	    	System.out.println("Se creo un ConsumoDto");
	    	ConsumoDto consumoVehiculo = new ConsumoDto(vehiculo, consumo);
	    	return consumoVehiculo;
	    }
	    
	    public PromedioDto promedioVehiculo(String patente) {
	    	ConsumoDto consumoDto = consumoVehiculo(patente);
	    	Integer km = consumoDto.getKm();
	    	if (km == null) {
	            km = 0; // O maneja de otra manera el caso cuando km es null
	        }
	    	Double consumo = consumoDto.getConsumo();
	    	Double promedio = (km/consumo);
	    	Double numeroRedondeado = Math.round(promedio * 100.0) / 100.0;
	    	PromedioDto promedioDto = new PromedioDto(consumoDto, numeroRedondeado);
	    	return promedioDto;
	    }
	    
	    @Transactional
	    public String agregarVehiculo(Vehiculo vehicle) {
	        // Verificar si ya existe un vehículo con la misma patente
	        Optional<Vehiculo> vehiculoExistente = vehiculoRepository.findByPatente(vehicle.getPatente());
	        
	        if (vehiculoExistente.isPresent()) {
	            throw new IllegalArgumentException("Ya existe un vehículo con la misma patente.");
	        }
	        vehiculoRepository.save(vehicle);
	        
	        return "Vehículo agregado exitosamente";
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
	
		
	    
	    


