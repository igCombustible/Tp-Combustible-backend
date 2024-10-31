package ProyectoCombustible.TP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ProyectoCombustible.TP.model.EstadoDelTicket;
import ProyectoCombustible.TP.model.Ticket;
import ProyectoCombustible.TP.model.Usuario;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String>{
}

