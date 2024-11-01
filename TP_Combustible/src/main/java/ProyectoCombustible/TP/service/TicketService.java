package ProyectoCombustible.TP.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ProyectoCombustible.TP.Repository.TicketRepository;
import ProyectoCombustible.TP.dto.TicketDto;
import ProyectoCombustible.TP.model.EstadoDelTicket;
import ProyectoCombustible.TP.model.Ticket;
import ProyectoCombustible.TP.model.Usuario;
import ProyectoCombustible.TP.model.Vehiculo;

@Service
public class TicketService {
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private UserInfoService usuarioService;
	@Autowired
	private VehiculoService vehiculoService;
	
	
	
	public List<Ticket> findAll(){
		return ticketRepository.findAll();
	}	
	public Optional<Ticket> getTicketById(String id) {
	    return ticketRepository.findById(id);
	}
	
	@Transactional
	public String save(TicketDto ticketDto) {
		Optional<Usuario> usuario = this.usuarioService.buscarPorEmail(ticketDto.getUserName());
		Optional<Vehiculo> vehiculo = this.vehiculoService.findByPatente(ticketDto.getPatente());
		
		Integer cantidadDeSolicitud = ticketDto.getTicket().getCantidadDeSolicitud();
		Date fechaDeSolicitud = ticketDto.getTicket().getFechaDeSolicitud();
		Usuario unUsuario = usuario.get();
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
	
	public void delete (String id) {
		ticketRepository.deleteById(id);
	}
} 