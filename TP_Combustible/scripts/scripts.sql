DROP TABLE combustible.usuario_rol;
DROP TABLE combustible.usuario;
DROP TABLE combustible.rol;
DROP TABLE combustible.ticket;
-- combustible.usuario definition

CREATE TABLE combustible.usuario (
  id VARCHAR(36) NOT NULL,
  email varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY index_usuario_email (email)
);

INSERT into combustible.usuario(id,email,name,password) 
VALUES (UUID(),'admin@gmail.com','admin','admin');



CREATE TABLE combustible.rol (
	id VARCHAR(36) NOT NULL ,
	name varchar(50) DEFAULT NULL,
	description varchar(256) DEFAULT NULL,
	PRIMARY KEY (id)
	);

INSERT into combustible.rol (id,name,description)
VALUES(UUID(),'USER','usuario basico');

INSERT into combustible.rol (id,name,description)
VALUES(UUID(),'ADMIN','usuario con poderes');

	
CREATE TABLE combustible.usuario_rol (
	id VARCHAR(36) NOT NULL,
	id_usuario VARCHAR(36) NOT NULL,
	id_rol VARCHAR(36) NOT NULL,
	PRIMARY KEY (id),
    UNIQUE KEY index_usuario_rol (id_usuario,id_rol),
    FOREIGN KEY (id_rol) REFERENCES rol(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
	);
	
INSERT INTO combustible.usuario_rol(id, id_usuario, id_rol)
VALUES (UUID(), (select ID from combustible.usuario where  email = 'admin@gmail.com'), (select id from combustible.rol where name = 'ADMIN'));

CREATE TABLE combustible.vehiculo (
  marca varchar(255) DEFAULT NULL,
  modelo varchar(255) DEFAULT NULL,
  patente varchar(255) NOT NULL,
  ultimo_km int DEFAULT NULL,
  estado_vehiculo boolean NOT NULL,
  PRIMARY KEY (patente)  -- La clave primaria ya es única
);

CREATE TABLE combustible.ticket (
    id VARCHAR(36) NOT NULL,
    cantidad_de_solicitud INT,
    fecha_de_solicitud TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id VARCHAR(36) NOT NULL,
    patente VARCHAR(255) NOT NULL,
    estado VARCHAR(20) NOT NULL,  
    PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES combustible.usuario(id),
    FOREIGN KEY (vehiculo_id) REFERENCES combustible.vehiculo(patente)
); 




-- INSERT INTO combustible.ticket(id, cantidadDeSolicitud, fecha_de_solicitud, usuario_id, vehiculo_id, estado)
-- VALUES (UUID(), 1, CURDATE(), (SELECT id FROM combustible.usuario WHERE email = 'admin@gmail.com'), (SELECT patente FROM combustible.vehiculo LIMIT 1), 'ENTREGADO');



select * from combustible.rol r ;
select * from combustible.usuario_rol ur ;
select * from combustible.usuario u ;
select * from combustible.vehiculo v ;
SELECT * FROM combustible.ticket t;


