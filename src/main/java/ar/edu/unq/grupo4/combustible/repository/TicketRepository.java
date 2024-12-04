package ar.edu.unq.grupo4.combustible.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupo4.combustible.model.EstadoDelTicket;
import ar.edu.unq.grupo4.combustible.model.Ticket;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, String>{
	
	@EntityGraph(attributePaths = {"usuario.usuarioRoles.rol", "vehiculo"})
    List<Ticket> findAll();
	
	@EntityGraph(attributePaths = {"usuario.usuarioRoles.rol", "vehiculo.patente"})
    List<Ticket> findByEstadoAndVehiculo_Patente(EstadoDelTicket estado, String patente);

	@EntityGraph(attributePaths = {"usuario.usuarioRoles.rol","vehiculo"})
    List<Ticket> findByEstado(EstadoDelTicket estado);
}

