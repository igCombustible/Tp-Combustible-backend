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
	public List<Ticket> findAllAceptadosPorPatente(String patente) {
	    List<Ticket> todosLosTickets = ticketRepository.findByEstadoAndVehiculo_Patente(EstadoDelTicket.ACEPTADO, patente);
	    return todosLosTickets;
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