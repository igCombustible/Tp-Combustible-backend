package ar.edu.unq.grupo4.combustible.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupo4.combustible.model.CodigoVerificacion;

@Repository
public interface CodigoVerificacionRepository extends JpaRepository<CodigoVerificacion, String>{

	Optional<CodigoVerificacion> findByEmail(String email);

	void deleteByEmail(String email);

	
}
