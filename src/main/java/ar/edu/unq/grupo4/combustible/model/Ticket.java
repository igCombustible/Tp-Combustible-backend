package ar.edu.unq.grupo4.combustible.model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket", schema = "combustible")
public class Ticket {    
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "cantidad_de_solicitud")
    private Integer cantidadDeSolicitud; 
    
    @Column(name = "fecha_de_solicitud")
    private Date fechaDeSolicitud;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "patente") 
    private Vehiculo vehiculo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoDelTicket estado;

   
    public Ticket() {}
    
    public Ticket(Integer cantidadDeSolicitud, Date fechaDeSolicitud, Usuario usuario, Vehiculo vehiculo,
			EstadoDelTicket estado) {
		super();
		this.cantidadDeSolicitud = cantidadDeSolicitud;
		this.fechaDeSolicitud = fechaDeSolicitud;
		this.usuario = usuario;
		this.vehiculo = vehiculo;
		this.estado = estado;
	}
    
	




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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public EstadoDelTicket getEstado() {
        return estado;
    }

    public void setEstado(EstadoDelTicket estado) {
        this.estado = estado;
    }
}
