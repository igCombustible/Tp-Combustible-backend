package ar.edu.unq.grupo4.combustible.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupo4.combustible.model.Vehiculo;



@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {

	Optional<Vehiculo> findByPatente(String patente);
	List<Vehiculo> findByDeletedFalse();
	
	@Query("SELECT COUNT(v) FROM Vehiculo v WHERE v.deleted = false")
    Integer getTotalVehiculos();
    
	@Query("SELECT SUM(v.ultimoValorConocidoKm) FROM Vehiculo v WHERE v.deleted = false")
    Integer getTotalKilometros();
}
