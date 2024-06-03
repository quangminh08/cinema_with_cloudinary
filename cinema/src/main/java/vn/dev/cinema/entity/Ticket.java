package vn.dev.cinema.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_ticket")
public class Ticket extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "schedule_id")
	Schedule schedule;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ticket_type_id")
	TicketType ticketType;

	@Column(name = "price")
	private Double price;

	@Column(name = "possition")
	private String possition;

	@Column(name = "status")
	private boolean status;

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public String getPossition() {
		return possition;
	}

	public void setPossition(String possition) {
		this.possition = possition;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Ticket(Integer id, Date createDate, Date updateDate, Schedule schedule, TicketType ticketType, Double price,
			String possition, boolean status) {
		super(id, createDate, updateDate);
		this.schedule = schedule;
		this.ticketType = ticketType;
		this.price = price;
		this.possition = possition;
		this.status = status;
	}

	public Ticket() {
		super();
	}

}
