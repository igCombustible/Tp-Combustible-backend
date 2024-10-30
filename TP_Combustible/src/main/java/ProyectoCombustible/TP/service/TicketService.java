package ProyectoCombustible.TP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProyectoCombustible.TP.Repository.TicketRepository;


import ProyectoCombustible.TP.model.Ticket;

@Service
public class TicketService {
  @Autowired
  private TicketRepository ticketRepository;
	
	
  public List<Ticket> findAll(){
	  return ticketRepository.findAll();
  }	
  public Optional<Ticket> getTicketById(String id) {
      return ticketRepository.findById(id);
  }

  public Ticket save(Ticket ticket) {
	return ticketRepository.save(ticket);
	   }
  
  
  public void delete (String id) {
	  ticketRepository.deleteById(id);
  }

} 