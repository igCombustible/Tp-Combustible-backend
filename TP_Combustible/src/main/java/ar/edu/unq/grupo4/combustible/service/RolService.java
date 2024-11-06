package ar.edu.unq.grupo4.combustible.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.grupo4.combustible.model.Rol;
import ar.edu.unq.grupo4.combustible.repository.RolRepository;



@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public String getRolByName(String name) {
        Rol rol = rolRepository.findByName(name);
        return rol.getId();
    }

   
}
