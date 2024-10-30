package ProyectoCombustible.TP.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ProyectoCombustible.TP.Repository.VehiculoRepository;
import ProyectoCombustible.TP.dto.TicketDto;
import ProyectoCombustible.TP.model.Ticket;
import ProyectoCombustible.TP.model.Usuario;
import ProyectoCombustible.TP.model.Vehiculo;
import ProyectoCombustible.TP.service.TicketService;
import ProyectoCombustible.TP.service.UserInfoService;
import ProyectoCombustible.TP.service.VehiculoService;





@RestController
@RequestMapping("/auth/ticket")
public class TicketController {
	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/todosLosTickets")
	public List <Ticket> getAllTickets(){
		return ticketService.findAll();	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable String id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

    @Autowired TicketService ticketService2;
    
    @PostMapping("/agregarTicket")
    public Ticket ceateTicket(@RequestBody TicketDto ticketdto) {
 	   
    Ticket ticket = new Ticket();
    ticket.setCantidadDeSolicitud(ticketdto.getCantidadDeSolicitud());
    ticket.setFechaDeSolicitud(ticketdto.getFechaDeSolicitud());
    ticket.setId(ticketdto.getIdusuario());
    ticket.setEstado(ticketdto.getEstado());
    
    
    
    
   return ticketService.save(ticket);
  }

    
   
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> cancelTicket(@PathVariable String id) {
       ticketService.delete(id);
       return ResponseEntity.noContent().build();
   }

    
    
    
    
}




