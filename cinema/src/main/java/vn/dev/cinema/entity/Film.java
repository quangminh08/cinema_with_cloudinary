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
@Table(name = "tbl_film")
public class Film extends BaseEntity {

	@Column(name = "title", length = 1000)
	private String title;

	@Column(name = "avatar", length = 1000)
	private String avatar;

	@Column(name = "director", length = 100)
	private String director;

	@Column(name = "performer", length = 500)
	private String performer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	Category category;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "premiere", length = 500)
	private Date premiere;

	@Column(name = "length")
	private Integer length;

	@Column(name = "language", length = 45)
	private String language;

	@Column(name = "description", length = 5000)
	private String description;

	@Column(name = "price")
	private Double price;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "film")
	private Set<Schedule> schedules = new HashSet<>();

	public void addRelationalSchedule(Schedule schedule) {
		schedules.add(schedule);
		schedule.setFilm(this);
	}

	public void deleteRelationalSchedule(Schedule schedule) {
		schedules.remove(schedule);
		schedule.setFilm(null);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getPremiere() {
		return premiere;
	}

	public void setPremiere(Date premiere) {
		this.premiere = premiere;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Set<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}

	public Film(Integer id, Date createDate, Date updateDate, String title, String avatar, String director,
			String performer, Category category, Date premiere, Integer length, String language, String description,
			Double price, Set<Schedule> schedules) {
		super(id, createDate, updateDate);
		this.title = title;
		this.avatar = avatar;
		this.director = director;
		this.performer = performer;
		this.category = category;
		this.premiere = premiere;
		this.length = length;
		this.language = language;
		this.description = description;
		this.price = price;
		this.schedules = schedules;
	}

	public Film() {
		super();
		// TODO Auto-generated constructor stub
	}

}
