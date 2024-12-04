package ar.edu.unq.grupo4.combustible.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupo4.combustible.model.EstadoDelTicket;
import ar.edu.unq.grupo4.combustible.model.Vehiculo;



@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
	
	@EntityGraph(attributePaths = {"tickets.usuario"})
	Optional<Vehiculo> findByPatente(String patente);
	
	@EntityGraph(attributePaths = {"tickets.usuario"})
	List<Vehiculo> findByDeletedFalse();
	
	@EntityGraph(attributePaths = {"tickets.usuario"})
	List<Vehiculo> findDistinctByTickets_EstadoAndDeletedFalse(EstadoDelTicket estado);
	
	@EntityGraph(attributePaths = {"tickets.usuario"})
	Vehiculo findDistinctByPatenteAndTickets_EstadoAndDeletedFalse(String patente, EstadoDelTicket estado);
}
