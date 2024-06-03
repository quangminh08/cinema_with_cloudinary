package vn.dev.cinema.model;

import java.util.Date;

public class CinemaModel extends BaseModel {
	private String name;
	private String address;
	private Integer managerId;

	public CinemaModel(Integer id, Date createDate, Date updateDate, String name, String address, Integer managerId) {
		super(id, createDate, updateDate);
		this.name = name;
		this.address = address;
		this.managerId = managerId;
	}

	public CinemaModel() {
		super();
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

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

}
