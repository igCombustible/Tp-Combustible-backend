package ar.edu.unq.grupo4.combustible.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupo4.combustible.dto.InfoVehiculoDto;
import ar.edu.unq.grupo4.combustible.dto.VehiculoDto;
import ar.edu.unq.grupo4.combustible.model.Vehiculo;
import ar.edu.unq.grupo4.combustible.repository.VehiculoRepository;
import jakarta.transaction.Transactional;


@Service
public class VehiculoService {
	 	@Autowired
	    private VehiculoRepository vehiculoRepository;

	    public List<Vehiculo> findAll() {
	        return vehiculoRepository.findByDeletedFalse();
	    }

	    public Optional<Vehiculo> findByPatente(String patente) {
	        return vehiculoRepository.findByPatente(patente);
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
   @Autowired TicketService ticketService;


     
     public Integer getTotalVehiculos() {
       return vehiculoRepository.getTotalVehiculos();
}
  
     public Integer totalKilometros() {
      return vehiculoRepository.getTotalKilometros();
}


}
	
//	    public Integer totalKilometros() {
//	 	        return vehiculoRepository.findByDeletedFalse()
//	 	                .stream()
//	 	                .mapToInt(Vehiculo::getUltimoValorConocidoKm)
//	 	                .sum();
//	 	    }
//
//	    
//	    public Double totalCombustible() {
//	        List<InfoVehiculoDto> infoVehiculos = ticketService.infoVehiculo();
//                   return infoVehiculos.stream()
//	                .mapToDouble(InfoVehiculoDto::getConsumo)
//	                .sum();
		
	    
	    


