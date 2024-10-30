package ProyectoCombustible.TP.model;

import java.util.Date;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "ticket", schema = "combustible")
public class Ticket {    
	 @Id
	private String id = UUID.randomUUID().toString();

    private Integer cantidadDeSolicitud; 
    
    @Column(name = "fecha_de_solicitud")
    private Date fechaDeSolicitud;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

   @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "patente") 
    private Vehiculo vehiculo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoDelTicket estado;

   
    
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
