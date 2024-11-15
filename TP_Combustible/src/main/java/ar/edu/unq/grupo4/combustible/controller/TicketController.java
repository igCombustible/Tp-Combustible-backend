package ar.edu.unq.grupo4.combustible.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.grupo4.combustible.dto.TicketDto;
import ar.edu.unq.grupo4.combustible.model.Ticket;
import ar.edu.unq.grupo4.combustible.service.TicketService;



@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/espera")
	public List <Ticket> getAllTicketsEspera(){
		return ticketService.findAllEspera();}
	
	@GetMapping("/aceptados")
	public List <Ticket> getAllTicketsAceptados(){
		return ticketService.findAllAceptados();}
	
	@GetMapping("/infoTickets/{patente}")
	public List <Ticket> getTicketDeVehiculo(@PathVariable String patente){
		return ticketService.findAllAceptadosPorPatente(patente);
	}
	
	@GetMapping("/consumoPromedioPorKm/{patente}")
	public Double getConsumoPromedioPorKm(@PathVariable String patente) {
		return ticketService.promedioKmPorConsumo(patente);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable String id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
	
    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public String createTicket(@RequestBody TicketDto ticketDto) {
    	return  ticketService.save(ticketDto);
 	}
    
    
    @DeleteMapping("/{id}")
	public ResponseEntity<String> cancelTicket(@PathVariable String id) {
		
		return ResponseEntity.ok(this.ticketService.cancelar(id));
	}
    @PutMapping("/{id}")
    public String aceptarTicket(@PathVariable String id) {
    	return this.ticketService.confirmar(id);
    }
    
//    @PutMapping("/{id}")
//    public String cancelarTicket(@PathVariable String id) {
//    	return this.ticketService.cancelar(id);
//    }
    
}




