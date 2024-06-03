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
@Table(name = "tbl_category")
public class Category extends BaseEntity{

	@Column(name = "name", length=500)
	private String name;
	
	@Column(name = "description", length=5000)
	private String description;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Film> films = new HashSet<>();
	
	public void addRelationalFilm(Film film) {
		films.add(film);
		film.setCategory(this);	
	}
	
	public void deleteRelationalFilm(Film film) {
		films.remove(film);
		film.setCategory(null);	
	}

	public Category(Integer id, Date createDate, Date updateDate, String name, String description, Set<Film> films) {
		super(id, createDate, updateDate);
		this.name = name;
		this.description = description;
		this.films = films;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	public Category() {
		super();
	}

	
	
	
}
