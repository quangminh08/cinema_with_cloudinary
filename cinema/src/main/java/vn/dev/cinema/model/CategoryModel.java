package vn.dev.cinema.model;

import java.util.Date;

public class CategoryModel extends BaseModel {

	private String name;
	private String description;

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

	public CategoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryModel(Integer id, Date createDate, Date updateDate, String name, String description) {
		super(id, createDate, updateDate);
		this.name = name;
		this.description = description;
	}

}
