package ar.edu.unq.grupo4.combustible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.grupo4.combustible.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, String> {  
    
	Rol findByName(String name);
    
}


