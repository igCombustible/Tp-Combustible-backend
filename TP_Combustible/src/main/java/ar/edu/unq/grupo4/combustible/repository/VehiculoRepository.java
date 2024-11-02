package ar.edu.unq.grupo4.combustible.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupo4.combustible.model.Vehiculo;


@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {

	Optional<Vehiculo> findByPatente(String patente);
	
}
