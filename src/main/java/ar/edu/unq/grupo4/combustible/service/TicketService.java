package ar.edu.unq.grupo4.combustible.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.grupo4.combustible.dto.TicketDto;
import ar.edu.unq.grupo4.combustible.model.EstadoDelTicket;
import ar.edu.unq.grupo4.combustible.model.Ticket;
import ar.edu.unq.grupo4.combustible.model.Usuario;
import ar.edu.unq.grupo4.combustible.model.Vehiculo;
import ar.edu.unq.grupo4.combustible.repository.TicketRepository;

@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UserInfoService usuarioService;
	@Autowired
	private VehiculoService vehiculoService;
	@Autowired
	private VehiculoService vehiculoRepository;
	
	
	@Transactional(readOnly = true)
	public List<Ticket> findAllEspera(){
		List<Ticket> todosLosTickets = ticketRepository.findByEstado(EstadoDelTicket.ESPERANDO);			
		return todosLosTickets;
	}	
	
	@Transactional(readOnly = true)
	public List<Ticket> findAllAceptados(){
		List<Ticket> todosLosTickets = ticketRepository.findByEstado(EstadoDelTicket.ACEPTADO);		
		return todosLosTickets;
	}	
	
	@Transactional(readOnly = true)
	public List<Ticket> findAllAceptadosPorPatente(Vehiculo vehiculo) {
	    List<Ticket> todosLosTickets = ticketRepository.findByEstadoAndVehiculo(EstadoDelTicket.ACEPTADO, vehiculo);
	    return todosLosTickets;
	}	
	
	@Transactional(readOnly = true)
	public List<Ticket> findAllAceptadosPorPatente(String patente) {
		Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findByPatente(patente);
		Vehiculo vehiculo = vehiculoOptional.get();
	    List<Ticket> todosLosTickets = ticketRepository.findByEstadoAndVehiculo(EstadoDelTicket.ACEPTADO, vehiculo);
	    return todosLosTickets;
	}	
	
	@Transactional(readOnly = true)
	public Double sumarCantidadDeSolicitudPorVehiculo(Vehiculo vehiculo) {
	    List<Ticket> todosLosTickets = findAllAceptadosPorPatente(vehiculo);
	    return todosLosTickets
	    		.stream()
	            .mapToDouble(Ticket::getCantidadDeSolicitud)
	            .sum();
	}
	
	
	public Double promedioKmPorConsumo(String patente) {
	    Optional<Vehiculo> buscarVehiculo = vehiculoService.findByPatente(patente);
	    
	    if (buscarVehiculo.isPresent()) {
	        Vehiculo vehiculo = buscarVehiculo.get();
	        Double consumo = sumarCantidadDeSolicitudPorVehiculo(vehiculo);
	        
	        if (consumo != 0) {
	            Double km = vehiculo.getUltimoValorConocidoKm().doubleValue();
	            return km / consumo;
	        } else {
	            System.out.println("El consumo es cero, no se puede calcular el promedio.");
	            return null;
	        }
	    } else {
	        System.out.println("Vehículo no encontrado con la patente: " + patente);
	        return null; 
	    }
	}
	
	public Optional<Ticket> getTicketById(String id) {
	    return ticketRepository.findById(id);
	}
	
	@Transactional
	public String save(TicketDto ticketDto) {
		System.out.println(ticketDto);
		Usuario usuario = this.usuarioService.buscarPorEmail(ticketDto.getUserName());
		Optional<Vehiculo> vehiculo = this.vehiculoService.findByPatente(ticketDto.getPatente());
		
		Integer cantidadDeSolicitud = ticketDto.getTicket().getCantidadDeSolicitud();
		Date fechaDeSolicitud = ticketDto.getTicket().getFechaDeSolicitud();
		Usuario unUsuario = usuario;
		Vehiculo unVehiculo = vehiculo.get();
		EstadoDelTicket estado = ticketDto.getTicket().getEstado();
		
		Ticket ticket = new Ticket(
				cantidadDeSolicitud, 
				fechaDeSolicitud,
				unUsuario,
				unVehiculo,
				estado);
		
		ticketRepository.save(ticket);
		
		return "se agrego correctamente, espera confirmacion del operador" ; 
	}
	@Transactional
	public String cancelar (String id) {
		Optional<Ticket> ticket = this.getTicketById(id);
		ticket.get().setEstado(EstadoDelTicket.RECHAZADO);
		this.ticketRepository.save(ticket.get());
		return "se cancelo el ticket";
	}
	
	
	@Transactional
	public String confirmar(String id) {
		Optional<Ticket> ticket = this.getTicketById(id);
		ticket.get().setEstado(EstadoDelTicket.ACEPTADO);
		this.ticketRepository.save(ticket.get());
		return "se acepto el ticket";
	}
} 