package ar.edu.unq.grupo4.combustible.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupo4.combustible.model.Usuario;


@Repository
public interface UserInfoRepository extends JpaRepository<Usuario, String> {
	
	@EntityGraph(attributePaths = {"usuarioRoles.rol"})
	Optional<Usuario> findByEmail(String email); // Use 'email' if that is the correct field for login

    

	
	
	@EntityGraph(attributePaths = {"usuarioRoles.rol"})
	List<Usuario> findAll();
}
