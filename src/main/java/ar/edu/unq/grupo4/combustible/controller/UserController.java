package ar.edu.unq.grupo4.combustible.controller;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.grupo4.combustible.dto.ContraseniaDto;
import ar.edu.unq.grupo4.combustible.dto.EmailDto;
import ar.edu.unq.grupo4.combustible.dto.LoginDto;

import ar.edu.unq.grupo4.combustible.dto.RolDto;
import ar.edu.unq.grupo4.combustible.model.EstadoPassword;
import ar.edu.unq.grupo4.combustible.model.Rol;

import ar.edu.unq.grupo4.combustible.dto.UsuarioDto;
import ar.edu.unq.grupo4.combustible.dto.VerificacionDto;
import ar.edu.unq.grupo4.combustible.model.Usuario;
import ar.edu.unq.grupo4.combustible.repository.UserInfoRepository;
import ar.edu.unq.grupo4.combustible.request.AuthRequest;
import ar.edu.unq.grupo4.combustible.service.JwtService;
import ar.edu.unq.grupo4.combustible.service.RolService;
import ar.edu.unq.grupo4.combustible.service.UserInfoService;



@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserInfoService service;
    
    @Autowired
    private RolService rolService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserInfoRepository repository;

    @PostMapping("/registrarse")
    public String addNewUser(@RequestBody Usuario userInfo) {
        return service.addUser(userInfo);
    }
    
    
    @PostMapping("/generateToken")
    public LoginDto authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Usuario usuario = repository.findByEmail(authRequest.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (usuario.getEstadop() == EstadoPassword.DESHABILITADO) {
            throw new IllegalStateException("Usuario deshabilitado. Contacte al administrador.");
        }
 
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            LoginDto loginDto = service.buscarUsuario(authRequest.getUsername(), token);
            return loginDto;
        } else {
            throw new UsernameNotFoundException("Solicitud de usuario inválida");
        }
    }

    
    
    @GetMapping("/espera")
    public List<Usuario> getAllUsariosEspera(){
    	return service.usuariosAlaEspera();
    }
    
    @GetMapping("/aceptados")
    public List<Usuario> getAllUsariosAceptados(){
    	return service.usuariosAceptados();
    }
    
    
     @PutMapping("/{id}")
     @PreAuthorize("hasAuthority('ADMIN')")
     public String aceptarUsuario(@PathVariable String id) {
    	 return this.service.aceptarAlUsuario(id);
     }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('OPERADOR')")
    public ResponseEntity<String> cancelarUsuario(@PathVariable String id){
        return ResponseEntity.ok(this.service.cancelarAlUsuario(id));	
    }

    @PutMapping("/asigna/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String asignarRol(@PathVariable String id, @RequestBody RolDto rolDTO) {
        Rol rol = this.rolService.getRolByName(rolDTO.getName());
        return this.service.asignarRol(id, rol);
    }

    @GetMapping
    public List<UsuarioDto> buscarTodosLosUsuarios(){
    	return this.service.buscarTodos();
    }
    
    @PostMapping("/solicitar-codigo")
    public ResponseEntity<String> solicitarCodigoVerificacion(@RequestBody EmailDto email) {
    	return ResponseEntity.ok(this.service.generarYEnviarCodigo(email));
    }
    
    
    @PostMapping("/verificar-codigo")
    public ResponseEntity<String> verificarCodigo(@RequestBody VerificacionDto verificar) {
        boolean esValido = this.service.verificarCodigo(verificar);
        if (esValido) {
            return ResponseEntity.ok("Código verificado correctamente.");
        } else {
            return ResponseEntity.badRequest().body("El código no es válido o ha expirado.");
        }
    }
    
    @PostMapping("/restablecer-contrasenia")
    public ResponseEntity<String> restablecerContrasenia(@RequestBody ContraseniaDto contraseniaDto) {
    	return ResponseEntity.ok(this.service.restablecerContrasenia(contraseniaDto));
    }
    

    @DeleteMapping("deshabilita/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deshabilitarPassword(@PathVariable String id){
      return ResponseEntity.ok(this.service.deshabilitar(id));	
    }

}  


