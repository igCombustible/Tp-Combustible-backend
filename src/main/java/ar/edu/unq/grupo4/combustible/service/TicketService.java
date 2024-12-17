package ar.edu.unq.grupo4.combustible.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.grupo4.combustible.dto.InfoVehiculoDto;
import ar.edu.unq.grupo4.combustible.dto.TicketDto;
import ar.edu.unq.grupo4.combustible.model.EstadoDelTicket;
import ar.edu.unq.grupo4.combustible.model.Ticket;
import ar.edu.unq.grupo4.combustible.model.Usuario;
import ar.edu.unq.grupo4.combustible.model.Vehiculo;
import ar.edu.unq.grupo4.combustible.repository.TicketRepository;
import ar.edu.unq.grupo4.combustible.repository.VehiculoRepository;

@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UserInfoService usuarioService;
	@Autowired
	private VehiculoRepository vehiculoRepository;
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
	public InfoVehiculoDto infoDelVehiculo(String patente) {
		List<Object[]> aceptados = ticketRepository.findCantidadConsumidaPorVehiculo(EstadoDelTicket.ACEPTADO, patente);
		
		Object[] row = aceptados.get(0);
		
		String patenteVehiculo = (String) row[0]; 
        String marca = (String) row[1];        
        String modelo = (String) row[2];         
        Integer ultimoKm = (Integer) row[3];
        Double cantidadConsumida = null;
        if (row[4] == null) {
        	cantidadConsumida = 0.00;
        }else {
        	cantidadConsumida = ((Number) row[4]).doubleValue();
        }
        
        return new InfoVehiculoDto(patenteVehiculo, marca, modelo, ultimoKm, cantidadConsumida);
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
	
	@Transactional
	public List<InfoVehiculoDto> infoVehiculo(){
		List<Object[]> resultados = ticketRepository.findCantidadConsumidaPorVehiculo(EstadoDelTicket.ACEPTADO);

	    return resultados.stream().map(result -> {
	        String patente = (String) result[0];
	        String marca = (String) result[1];
	        String modelo = (String) result[2];
	        Integer km = (Integer) result[3];
	        Double consumo = ((Number) result[4]).doubleValue(); // Conversión explícita de Long a Double

	        return new InfoVehiculoDto(patente, marca, modelo, km, consumo);
	    }).collect(Collectors.toList());
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

	 public Double totalCombustible() {
	        return ticketRepository.getTotalCombustible();
	 }

}
	
	