package ar.edu.unq.grupo4.combustible.service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.grupo4.combustible.dto.ContraseniaDto;
import ar.edu.unq.grupo4.combustible.dto.EmailDto;
import ar.edu.unq.grupo4.combustible.dto.LoginDto;
import ar.edu.unq.grupo4.combustible.dto.UsuarioDto;
import ar.edu.unq.grupo4.combustible.dto.VerificacionDto;
import ar.edu.unq.grupo4.combustible.model.Rol;
import ar.edu.unq.grupo4.combustible.model.CodigoVerificacion;
import ar.edu.unq.grupo4.combustible.model.EstadoDelUsuario;
import ar.edu.unq.grupo4.combustible.model.EstadoPassword;
import ar.edu.unq.grupo4.combustible.model.Usuario;
import ar.edu.unq.grupo4.combustible.model.UsuarioRol;
import ar.edu.unq.grupo4.combustible.repository.UserInfoRepository;
import ar.edu.unq.grupo4.combustible.repository.CodigoVerificacionRepository;
import ar.edu.unq.grupo4.combustible.security.UserInfoDetails;


@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private CodigoVerificacionRepository codigoVerificacionRepository;
    

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
        try {
            emailService.enviarEmail(
                userInfo.getEmail(),
                "Bienvenido a la App de Combustible",
                "Gracias por registrarte. Tu cuenta queda en estado de espera hasta "
                + "que un operador te acepte"
                );
        } catch (Exception e) {
            // Log o mensaje de error
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
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
    	List<Usuario> todosLosUsuarios = repository.findByEstado(EstadoDelUsuario.PENDIENTE);
    	return todosLosUsuarios;
    }
    
    
    @Transactional(readOnly = true)
    public List<Usuario> usuariosAceptados(){
    	List<Usuario> todosLosUsuarios = repository.findByEstado(EstadoDelUsuario.ACEPTADO);
    	return todosLosUsuarios;
    }
    
    @Transactional
    public String aceptarAlUsuario(String id) {
	    Optional<Usuario> usuario = repository.findById(id);
	    usuario.get().setEstado(EstadoDelUsuario.ACEPTADO);
	    this.repository.save(usuario.get());
	    return "el usuario se ha aceptado";
    }
    
	@Transactional
	public String cancelarAlUsuario (String id) {
		Optional <Usuario> usuario = repository.findById(id);
		usuario.get().setEstado(EstadoDelUsuario.RECHAZADO);
		this.repository.save(usuario.get());
		return "el usuario se ha rechazado";
	}
	
	
	public String generarYEnviarCodigo(EmailDto unEmail) {
		String email = unEmail.getEmail();
		System.out.println(email);
		Optional<Usuario> usuario = this.repository.findByEmail(email);
	    if (!usuario.isPresent()) {
	        throw new IllegalArgumentException("El correo no esta registrado.");
	    }
        
        int codigo = generarCodigoAleatorio();

        
        Timestamp fechaDeExpiracion = new Timestamp(System.currentTimeMillis() + 2 * 60 * 1000); 

        
        CodigoVerificacion codigoVerificacion = new CodigoVerificacion(UUID.randomUUID().toString(), email, codigo, fechaDeExpiracion);
        codigoVerificacionRepository.save(codigoVerificacion);

        
        emailService.enviarEmail(
                email ,
                "Bienvenido a la App de Combustible",
                "Gracias por registrarte. Por favor verifica tu cuenta con este codigo: " + codigo
                );

        return "se envio el correo correctamente";
    }
    private int generarCodigoAleatorio() {
    	double fiveDigits = 100000 + Math.random() * 900000;
        return (int) fiveDigits;
    }

    @Transactional(readOnly = true)
    public boolean verificarCodigo(VerificacionDto verificar) {
    	Integer codigo = verificar.getCodigo();
    	String email = verificar.getEmail();
        
        Optional<CodigoVerificacion> codigoVerificacionOpt = codigoVerificacionRepository.findByEmail(email);

        if (codigoVerificacionOpt.isPresent()) {
            CodigoVerificacion codigoVerificacion = codigoVerificacionOpt.get();

            
            if (codigoVerificacion.getCodigo_verificacion() == codigo &&
                codigoVerificacion.getExpiracion().after(new Timestamp(System.currentTimeMillis()))) {
                return true;
            }
        }
        return false;
    }
    @Transactional
    public String restablecerContrasenia(ContraseniaDto contraseniaDto) {
    	String nuevaContrasenia = contraseniaDto.getNuevaContrasenia();
    	String email = contraseniaDto.getEmail();
        Usuario usuario = this.repository.findByEmail(email).get();
        String contraseniaEncode = encoder.encode(nuevaContrasenia);
        usuario.setPassword(contraseniaEncode);
        this.repository.save(usuario);
        codigoVerificacionRepository.deleteByEmail(email);
        return "se restablecio su contrasenia correctamente";
    }

    @Transactional
    public String deshabilitar(String id) {
        Optional<Usuario> usuarioOpt = repository.findById(id);

       
        Usuario usuario = usuarioOpt.get();
        usuario.setEstadop(EstadoPassword.DESHABILITADO);
        repository.save(usuario);

        
        try {
            emailService.enviarEmail(
                usuario.getEmail(),
                "Contraseña deshabilitada",
                "Estimado usuario, su contraseña ha sido deshabilitada temporalmente. "
                + "Un administrador revisará su caso y habilitará nuevamente su acceso."
            );
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }

        return "Se deshabilitó la contraseña y se notificó al usuario.";
    }


		
	

}


	
	
	

    
    
