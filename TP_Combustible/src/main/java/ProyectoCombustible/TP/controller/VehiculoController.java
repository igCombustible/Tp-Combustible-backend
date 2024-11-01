package ProyectoCombustible.TP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ProyectoCombustible.TP.dto.VehiculoDto;
import ProyectoCombustible.TP.model.Vehiculo;
import ProyectoCombustible.TP.service.VehiculoService;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {
	@Autowired
	private VehiculoService vehiculoService;
	
	@GetMapping()
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoService.findAll();
    }

    @GetMapping("/{patente}")
    public ResponseEntity<Vehiculo> getVehiculo(@PathVariable String patente) {
        return vehiculoService.findByPatente(patente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String>  createVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
        	return  ResponseEntity.ok(vehiculoService.agregarVehiculo(vehiculo));
        }catch (IllegalArgumentException e){
        	return ResponseEntity.status(HttpStatus.CONFLICT).body("Vehiculo duplicado");
        }
    }
    
    @PutMapping("/{patente}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public VehiculoDto editarVehivulo(@PathVariable String patente, @RequestBody Vehiculo vehiculo) {
    	return this.vehiculoService.editarVehiculo(patente, vehiculo);
    }
  
    @DeleteMapping("/{patente}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminarVehiculo(@PathVariable String patente) {
        try {
            this.vehiculoService.delete(patente);
            return ResponseEntity.ok("Vehículo eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el vehículo");
        }
    }
    
    
    
    
}
