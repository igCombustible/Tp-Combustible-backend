package ProyectoCombustible.TP.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ProyectoCombustible.TP.model.Usuario;

@Repository
public interface UserInfoRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email); // Use 'email' if that is the correct field for login
	
}
