package ProyectoCombustible.TP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ProyectoCombustible.TP.dto.TicketDto;
import ProyectoCombustible.TP.model.Ticket;
import ProyectoCombustible.TP.service.TicketService;





@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private TicketService ticketService;
	
	
	@GetMapping
	public List <Ticket> getAllTickets(){
		return ticketService.findAll();	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable String id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public String ceateTicket(@RequestBody TicketDto ticketDto) {
    	return  ticketService.save(ticketDto);
 	}
    
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<Void> cancelTicket(@PathVariable String id) {
		ticketService.delete(id);
		return ResponseEntity.noContent().build();
	}
    
}




