package ar.edu.unq.grupo4.combustible.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupo4.combustible.dto.InfoVehiculoDto;
import ar.edu.unq.grupo4.combustible.dto.VehiculoDto;
import ar.edu.unq.grupo4.combustible.model.EstadoDelTicket;
import ar.edu.unq.grupo4.combustible.model.Ticket;
import ar.edu.unq.grupo4.combustible.model.Vehiculo;
import ar.edu.unq.grupo4.combustible.repository.VehiculoRepository;
import jakarta.transaction.Transactional;


@Service
public class VehiculoService {
	 	@Autowired
	    private VehiculoRepository vehiculoRepository;
	 	
	 	@Transactional
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
	    
	    @Transactional
	    public InfoVehiculoDto obtenerVehiculosConTicketsAceptados(String patente) {
	    	Vehiculo vehiculo = this.vehiculoRepository.findDistinctByPatenteAndTickets_EstadoAndDeletedFalse(patente, EstadoDelTicket.ACEPTADO);
	    	
	    	Hibernate.initialize(vehiculo.getTickets());
	    	
	    	List<Ticket> tickets = vehiculo.getTickets().stream()
        		    .filter(ticket -> ticket.getEstado() == EstadoDelTicket.ACEPTADO)
        		    .filter(ticket -> ticket.getVehiculo() != null && !ticket.getVehiculo().getDeleted())
        		    .collect(Collectors.toList());
	    	
	    	Integer consumo = tickets.stream()
	                .mapToInt(ticket -> ticket.getCantidadDeSolicitud() != null ? ticket.getCantidadDeSolicitud() : 0)
	                .sum();
	    	
	    	InfoVehiculoDto infoVehiculoDto = new InfoVehiculoDto (
	    			vehiculo.getPatente(),
	                vehiculo.getMarca(),
	                vehiculo.getModelo(),
	                vehiculo.getUltimoValorConocidoKm(),
	                consumo,
	                tickets);
	    			
	    	return infoVehiculoDto;	
	    }
	    
	    @Transactional
	    public List<InfoVehiculoDto> getInfoDeTodosLosVehiculos() {
	    	List<Vehiculo> vehiculos = vehiculoRepository.findDistinctByTickets_EstadoAndDeletedFalse(EstadoDelTicket.ACEPTADO);
	        
	        List<InfoVehiculoDto> infoVehiculosDtos = new ArrayList<>();
	      
	        for (Vehiculo vehiculo : vehiculos) {
	            
	        	List<Ticket> tickets = vehiculo.getTickets().stream()
	        		    .filter(ticket -> ticket.getEstado() == EstadoDelTicket.ACEPTADO)
	        		    .filter(ticket -> ticket.getVehiculo() != null && !ticket.getVehiculo().getDeleted())
	        		    .collect(Collectors.toList());
	        		    
	        	Integer consumo = tickets.stream()
	                    .mapToInt(ticket -> ticket.getCantidadDeSolicitud() != null ? ticket.getCantidadDeSolicitud() : 0)
	                    .sum();

	            InfoVehiculoDto infoVehiculoDto = new InfoVehiculoDto(
	                vehiculo.getPatente(),
	                vehiculo.getMarca(),
	                vehiculo.getModelo(),
	                vehiculo.getUltimoValorConocidoKm(),
	                consumo,
	                tickets
	            );
	            
	            infoVehiculosDtos.add(infoVehiculoDto);
	        }
	        return infoVehiculosDtos;
	    }

	}
	
		
	    
	    


