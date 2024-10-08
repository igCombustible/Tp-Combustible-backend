DROP TABLE combustible.usuario_roles;
DROP TABLE combustible.usuario;
DROP TABLE combustible.roles;
-- combustible.usuario definition

CREATE TABLE combustible.usuario (
  id int(11) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  roles varbinary(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY index_usuario_email (email)
);

INSERT into combustible.usuario(email,name,password,roles) 
VALUES ('admin@gmail.com','admin','admin',"ROLE_ADMIN,ROLE_USER");

CREATE TABLE combustible.roles (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(50) DEFAULT NULL,
	description varchar(256) DEFAULT NULL,
	PRIMARY KEY (id)
	);
	
CREATE TABLE combustible.usuario_roles (
	id int(11) NOT NULL AUTO_INCREMENT,
	id_usuario int(11) NOT NULL,
	id_rol int(11) NOT NULL,
	PRIMARY KEY (id),
    UNIQUE KEY index_usuario_rol (id_usuario,id_rol),
    FOREIGN KEY (id_rol) REFERENCES roles(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
	);
	
INSERT into combustible.roles (name,description)
VALUES('USER','usuario basico');

INSERT into combustible.roles(name,description)
VALUES('ADMIN','usuario con poderes');