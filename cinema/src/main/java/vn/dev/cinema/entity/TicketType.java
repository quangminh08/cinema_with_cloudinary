package vn.dev.cinema.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_ticket_type")
public class TicketType extends BaseEntity{
	
	@Column(name = "name", length=500)
	private String name;
	
	@Column(name = "object", length=500)
	private String object;
	
	@Column(name = "chair_type", length=500)
	private String chairType;
	
	@Column(name = "date_type", length=500)
	private String dateType;
	
	@Column(name = "price_coefficient")
	private Double priceCoefficient;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ticketType")
	private Set<Ticket> tickets = new HashSet<>();
	
	public void addRelationalTicket(Ticket ticket) {
		tickets.add(ticket);
		ticket.setTicketType(this);	
	}
	
	public void deleteRelationalTicket(Ticket ticket) {
		tickets.remove(ticket);
		ticket.setTicketType(null);	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getChairType() {
		return chairType;
	}

	public void setChairType(String chairType) {
		this.chairType = chairType;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public Double getPriceCoefficient() {
		return priceCoefficient;
	}

	public void setPriceCoefficient(Double priceCoefficient) {
		this.priceCoefficient = priceCoefficient;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public TicketType(Integer id, Date createDate, Date updateDate, String name, String object, String chairType,
			String dateType, Double priceCoefficient, Set<Ticket> tickets) {
		super(id, createDate, updateDate);
		this.name = name;
		this.object = object;
		this.chairType = chairType;
		this.dateType = dateType;
		this.priceCoefficient = priceCoefficient;
		this.tickets = tickets;
	}

	public TicketType() {
		super();
	}
		
}
