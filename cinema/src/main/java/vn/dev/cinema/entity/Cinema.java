package vn.dev.cinema.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name="tbl_cinema")
@Entity
public class Cinema extends BaseEntity{

	@Column(name = "name", length=500)
	private String name;
	
	@Column(name = "address", length=500)
	private String address;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;
    
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cinema")
	private Set<Schedule> schedules = new HashSet<>();
	
	public void addRelationalSchedule(Schedule schedule) {
		schedules.add(schedule);
		schedule.setCinema(this);	
	}
	
	public void deleteRelationalSchedule(Schedule schedule) {
		schedules.remove(schedule);
		schedule.setCinema(null);	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Cinema(Integer id, Date createDate, Date updateDate, String name, String address, User manager) {
		super(id, createDate, updateDate);
		this.name = name;
		this.address = address;
		this.manager = manager;
	}

	public Cinema() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
