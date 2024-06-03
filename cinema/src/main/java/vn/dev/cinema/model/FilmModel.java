package vn.dev.cinema.model;

import java.util.Date;

public class FilmModel extends BaseModel {
	private String title;
	private String avatar;
	private String director;
	private String performer;
	private Integer categoryId;
	private Date premiere;
	private Integer length;
	private String language;
	private String description;
	private Double price;

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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public FilmModel(Integer id, Date createDate, Date updateDate, String title, String avatar, String director,
			String performer, Integer categoryId, Date premiere, Integer length, String language, String description,
			Double price) {
		super(id, createDate, updateDate);
		this.title = title;
		this.avatar = avatar;
		this.director = director;
		this.performer = performer;
		this.categoryId = categoryId;
		this.premiere = premiere;
		this.length = length;
		this.language = language;
		this.description = description;
		this.price = price;
	}

	public FilmModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
