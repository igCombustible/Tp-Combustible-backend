package ar.edu.unq.grupo4.combustible.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.grupo4.combustible.dto.LoginDto;
import ar.edu.unq.grupo4.combustible.dto.UsuarioDto;
import ar.edu.unq.grupo4.combustible.model.Rol;
import ar.edu.unq.grupo4.combustible.model.EstadoDelUsuario;
import ar.edu.unq.grupo4.combustible.model.Usuario;
import ar.edu.unq.grupo4.combustible.model.UsuarioRol;
import ar.edu.unq.grupo4.combustible.repository.UserInfoRepository;
import ar.edu.unq.grupo4.combustible.security.UserInfoDetails;


@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;
    

    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userDetail = repository.findByEmail(username); // Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    
    
    @Transactional(readOnly = true)
    public LoginDto buscarUsuario(String username, String token) {
    	Optional<Usuario> usuario = repository.findByEmail(username); // Assuming 'email' is used as username
    	List<String> roles = usuario.get().getRoles();
        return new LoginDto(token, roles);
    }
    
   
 
    @Transactional
    public String addUser(Usuario userInfo) {
    	if (userInfo.getUsuarioRoles() != null) {
            for (UsuarioRol usuarioRol : userInfo.getUsuarioRoles()) {
                usuarioRol.setUsuario(userInfo); 
            }
        }
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfo.setId(UUID.randomUUID().toString());
        repository.save(userInfo);
        return "User Added Successfully";
    }

    @Transactional(readOnly = true)
	public Usuario buscarPorEmail(String username) {
		Optional<Usuario> usuario = repository.findByEmail(username);
		return usuario.get();
	}

    @Transactional
    public String asignarRol(String id, Rol rol) {
        Optional<Usuario> usuario = this.repository.findById(id);
        		
        UsuarioRol usuarioRol = new UsuarioRol();
        
        usuarioRol.setUsuario(usuario.get());
        usuarioRol.setRol(rol);
        
        usuario.get().getUsuarioRoles().add(usuarioRol);        
        this.repository.save(usuario.get());
        
        return "se agrego el rol correctamente";
    }

    @Transactional(readOnly = true)
	public List<UsuarioDto> buscarTodos() {
		List<Usuario> usuarios = this.repository.findAll();
		List<UsuarioDto> usuariosDto =  usuarios.stream()
												.map(usuario -> new UsuarioDto(usuario))
												.collect(Collectors.toList());
		return usuariosDto;
  }

	
    @Transactional(readOnly = true)
    public List<Usuario> usuariosAlaEspera(){
    	List<Usuario> todosLosUsuarios = repository.findAll();
    	return todosLosUsuarios.stream().filter(u->u.getEstado() == EstadoDelUsuario.PENDIENTE)
    			                .collect(Collectors.toList());
    }
    
    
    @Transactional(readOnly = true)
    public List<Usuario> usuariosAceptados(){
    	List<Usuario> todosLosUsuarios = repository.findAll();
    	return todosLosUsuarios.stream().filter(u->u.getEstado() == EstadoDelUsuario.ACEPTADO)
    			.collect(Collectors.toList());
    }
    
    
    
    
    
    
    
    
    @Transactional
    public String aceptarAlUsuario(String id) {
    Optional<Usuario> usuario = repository.findById(id);
    usuario.get().setEstado(EstadoDelUsuario.ACEPTADO);
    this.userInfoRepository.save(usuario.get());
    return "el usuario se ha aceptado";
    }
    
     @Transactional
    public String cancelarAlUsuario (String id) {
    Optional <Usuario> usuario = userInfoRepository.findById(id);
    usuario.get().setEstado(EstadoDelUsuario.RECHAZADO);
    this.userInfoRepository.save(usuario.get());
    return "el usuario se ha rechazado";
    }

}

    
    
