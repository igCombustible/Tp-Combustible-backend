		
package ar.edu.unq.grupo4.combustible.dto;

import ar.edu.unq.grupo4.combustible.model.Ticket;

public class TicketDto {
    
	private String id; 
    private Ticket ticket;
    private String userName; 
    private String patente;  

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	

	

}
