package ar.edu.unq.grupo4.combustible.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ar.edu.unq.grupo4.combustible.model.Usuario;


public class UserInfoDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    
	private String username; // Changed from 'name' to 'username' for clarity
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(Usuario userInfo) {
        this.username = userInfo.getEmail(); // Assuming 'email' is used as 'username'
        this.password = userInfo.getPassword();
        this.authorities = userInfo.getUsuarioRoles()
                .stream()
                .map(usuarioRol -> new SimpleGrantedAuthority(usuarioRol.getRol().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement your logic if you need this
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement your logic if you need this
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement your logic if you need this
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement your logic if you need this
    }
}
