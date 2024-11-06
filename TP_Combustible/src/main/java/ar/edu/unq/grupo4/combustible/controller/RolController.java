package ar.edu.unq.grupo4.combustible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.grupo4.combustible.service.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
	private RolService rolService;
	
	@GetMapping("/{name}")
	public ResponseEntity<String> buscarRolPorNombre(@PathVariable String name ) {
		String idRol = this.rolService.getRolByName(name);
		return ResponseEntity.ok(idRol);
	}

}
