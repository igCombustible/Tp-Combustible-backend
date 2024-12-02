package ar.edu.unq.grupo4.combustible.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ar.edu.unq.grupo4.combustible.model.Vehiculo;



@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {

	Optional<Vehiculo> findByPatente(String patente);
	
	List<Vehiculo> findByDeletedFalse();
	
	@EntityGraph(value = "Vehiculo.tickets", type = EntityGraph.EntityGraphType.FETCH)
	@Query("SELECT DISTINCT v FROM Vehiculo v JOIN v.tickets t WHERE t.estado = 'ACEPTADO' AND v.deleted = false")
	List<Vehiculo> findVehiculosWithTicketsAceptados();

	
}
