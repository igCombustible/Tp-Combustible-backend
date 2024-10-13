package ProyectoCombustible.TP.dto;


import java.util.Set;

import ProyectoCombustible.TP.model.Usuario;
import ProyectoCombustible.TP.model.UsuarioRoles;



public class UsuarioDto {
		protected String name;
		protected String email;
		protected String password;
		protected Set<UsuarioRoles> roles;
		
		
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
