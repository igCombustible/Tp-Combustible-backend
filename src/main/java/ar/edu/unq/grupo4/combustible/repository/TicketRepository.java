package ar.edu.unq.grupo4.combustible.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupo4.combustible.model.EstadoDelTicket;
import ar.edu.unq.grupo4.combustible.model.Ticket;
import ar.edu.unq.grupo4.combustible.model.Vehiculo;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, String>{
	
	@EntityGraph(attributePaths = {"usuario.usuarioRoles.rol", "vehiculo"})
    List<Ticket> findAll();
	
	@EntityGraph(attributePaths = {"usuario.usuarioRoles.rol", "vehiculo"})
	List<Ticket> findByEstadoAndVehiculo(EstadoDelTicket aceptado, Vehiculo vehiculo);

	@EntityGraph(attributePaths = {"usuario.usuarioRoles.rol","vehiculo"})
    List<Ticket> findByEstado(EstadoDelTicket estado);
	
	@Query("SELECT t.vehiculo.patente, t.vehiculo.marca, t.vehiculo.modelo, t.vehiculo.ultimoValorConocidoKm, SUM(t.cantidadDeSolicitud) " +
	           "FROM Ticket t " +
	           "WHERE t.estado = :estado " +
	           "GROUP BY t.vehiculo.patente, t.vehiculo.marca, t.vehiculo.modelo, t.vehiculo.ultimoValorConocidoKm")
	List<Object[]> findCantidadConsumidaPorVehiculo(@Param("estado") EstadoDelTicket estado);
	
	@Query("SELECT t.vehiculo.patente, t.vehiculo.marca, t.vehiculo.modelo, t.vehiculo.ultimoValorConocidoKm, SUM(t.cantidadDeSolicitud) " +
		       "FROM Ticket t " +
		       "WHERE t.estado = :estado " +
		       "AND t.vehiculo.patente = :patente " + 
		       "GROUP BY t.vehiculo.patente, t.vehiculo.marca, t.vehiculo.modelo, t.vehiculo.ultimoValorConocidoKm")
		List<Object[]> findCantidadConsumidaPorVehiculo(@Param("estado") EstadoDelTicket estado, @Param("patente") String patente);

	
}

