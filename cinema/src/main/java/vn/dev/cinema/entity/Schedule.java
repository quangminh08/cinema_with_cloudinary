package vn.dev.cinema.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_schedule")
public class Schedule extends BaseEntity{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cinema_id")
	Cinema cinema;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="film_id")
	Film film;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="time")
	private Date time;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schedule")
	private Set<Ticket> tickets = new HashSet<>();
	
	public void addRelationalTicket(Ticket ticket) {
		tickets.add(ticket);
		ticket.setSchedule(this);	
	}
	
	public void deleteRelationalTicket(Ticket Ticket) {
		tickets.remove(Ticket);
		Ticket.setSchedule(null);	
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

	public Schedule(Integer id, Date createDate, Date updateDate, Cinema cinema, Film film, Date time) {
		super(id, createDate, updateDate);
		this.cinema = cinema;
		this.film = film;
		this.time = time;
	}

	public Schedule() {
		super();
	}
	
}
