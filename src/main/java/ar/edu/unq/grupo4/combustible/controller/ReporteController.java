package ar.edu.unq.grupo4.combustible.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.grupo4.combustible.dto.InfoVehiculoDto;
import ar.edu.unq.grupo4.combustible.model.Ticket;
import ar.edu.unq.grupo4.combustible.service.TicketService;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
	@Autowired
	TicketService ticketService;
	
	@GetMapping("/info")
    public List<InfoVehiculoDto> getInfoVehiculos() {
        return ticketService.infoVehiculo();
    }
	
	@GetMapping("/info/{patente}")
    public InfoVehiculoDto getInfoVehiculo(@PathVariable String patente) {
        return ticketService.infoDelVehiculo(patente);
	}
	
	@GetMapping("ticketAceptados/{patente}")
	public List<Ticket> getTicketsAceptados(@PathVariable String patente) {
		return ticketService.findAllAceptadosPorPatente(patente);
	}
}
