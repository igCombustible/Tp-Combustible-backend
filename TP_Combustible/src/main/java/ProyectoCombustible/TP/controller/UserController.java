package ProyectoCombustible.TP.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ProyectoCombustible.TP.dto.LoginDto;
import ProyectoCombustible.TP.model.Usuario;
import ProyectoCombustible.TP.model.UsuarioRoles;
import ProyectoCombustible.TP.request.AuthRequest;
import ProyectoCombustible.TP.service.JwtService;
import ProyectoCombustible.TP.service.UserInfoService;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody Usuario userInfo) {
        if (userInfo.getUsuarioRoles() != null) {
            for (UsuarioRoles usuarioRol : userInfo.getUsuarioRoles()) {
                usuarioRol.setUsuario(userInfo);
            }
        }
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public LoginDto authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
             String token = jwtService.generateToken(authRequest.getUsername());
             Optional<Usuario> usuario = this.service.buscarUsuario(authRequest.getUsername());
             LoginDto loginDto = new LoginDto(token, (usuario.get().getRoles())) ;
            return loginDto;
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
