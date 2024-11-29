package ar.edu.unq.grupo4.combustible.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.grupo4.combustible.dto.RolDto;
import ar.edu.unq.grupo4.combustible.model.Rol;
import ar.edu.unq.grupo4.combustible.service.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
	private RolService rolService;
	
	@GetMapping("/{name}")
	public ResponseEntity<String> buscarRolPorNombre(@PathVariable String name ) {
		Rol rol = this.rolService.getRolByName(name);
		RolDto rolDto = new RolDto(rol);
		return ResponseEntity.ok(rolDto.getId());
	}
	@GetMapping
	public ResponseEntity<List<String>> obtenerTodosLosRoles(){
		return ResponseEntity.ok(this.rolService.todosLosRoles());
	}

}
