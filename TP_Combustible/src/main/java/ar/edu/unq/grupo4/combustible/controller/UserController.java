package ar.edu.unq.grupo4.combustible.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.grupo4.combustible.dto.LoginDto;
import ar.edu.unq.grupo4.combustible.model.Usuario;
import ar.edu.unq.grupo4.combustible.request.AuthRequest;
import ar.edu.unq.grupo4.combustible.service.JwtService;
import ar.edu.unq.grupo4.combustible.service.UserInfoService;



@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/registrarse")
    public String addNewUser(@RequestBody Usuario userInfo) {
        return service.addUser(userInfo);
    }
    
    
    @PostMapping("/generateToken")
    public LoginDto authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            LoginDto loginDto = service.buscarUsuario(authRequest.getUsername(), token);
            return loginDto;
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    
//
//    @GetMapping("/user/userProfile")
//    @PreAuthorize("hasAuthority('USER')")
//    public String userProfile() {
//        return "Welcome to User Profile";
//    }
//
//    @GetMapping("/admin/adminProfile")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public String adminProfile() {
//        return "Welcome to Admin Profile";
//    }
}