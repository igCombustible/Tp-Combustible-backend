package ar.edu.unq.grupo4.combustible.service;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupo4.combustible.model.Rol;
import ar.edu.unq.grupo4.combustible.repository.RolRepository;



@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol getRolByName(String name) {
        Rol rol = rolRepository.findByName(name);
        return rol;
    }
	public List<String> todosLosRoles() {
		return this.rolRepository.findAll().stream().map(x -> x.getName()).collect(Collectors.toList());
	}

   
}
