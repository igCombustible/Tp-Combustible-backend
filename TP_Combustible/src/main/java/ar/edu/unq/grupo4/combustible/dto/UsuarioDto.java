package ar.edu.unq.grupo4.combustible.dto;


import java.util.Set;

import ar.edu.unq.grupo4.combustible.model.Usuario;
import ar.edu.unq.grupo4.combustible.model.UsuarioRol;




public class UsuarioDto {
		protected String name;
		protected String email;
		protected String password;
		protected Set<UsuarioRol> roles;
		
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		
		
		public UsuarioDto(Usuario usuario) {
	        this.email = usuario.getEmail();
	        this.password = usuario.getPassword();
	        this.name = usuario.getName();
	        this.roles = usuario.getUsuarioRoles();
	    }
}
