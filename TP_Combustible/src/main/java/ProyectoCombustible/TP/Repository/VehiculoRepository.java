package ProyectoCombustible.TP.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ProyectoCombustible.TP.model.Vehiculo;


@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String>{

	Optional<Vehiculo> findById(String id);
}
