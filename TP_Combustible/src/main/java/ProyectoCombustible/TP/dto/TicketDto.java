package ProyectoCombustible.TP.dto;

import java.util.Date;

import ProyectoCombustible.TP.model.EstadoDelTicket;

public class TicketDto {
    
	private String id; 
    private Integer cantidadDeSolicitud;
    private Date fechaDeSolicitud;
    private String idusuario; 
    private String patente; 
    private EstadoDelTicket estado; 

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCantidadDeSolicitud() {
        return cantidadDeSolicitud;
    }

    public void setCantidadDeSolicitud(Integer cantidadDeSolicitud) {
        this.cantidadDeSolicitud = cantidadDeSolicitud;
    }

    public Date getFechaDeSolicitud() {
        return fechaDeSolicitud;
    }

    public void setFechaDeSolicitud(Date fechaDeSolicitud) {
        this.fechaDeSolicitud = fechaDeSolicitud;
    }

   
    public EstadoDelTicket getEstado() {
        return estado;
    }

    public void setEstado(EstadoDelTicket estado) {
        this.estado = estado;
    }

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

}
