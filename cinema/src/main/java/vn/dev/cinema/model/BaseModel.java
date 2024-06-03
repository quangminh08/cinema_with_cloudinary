package vn.dev.cinema.model;

import java.util.Date;

public abstract class BaseModel {
	private Integer id;
	private Date createDate;
	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public BaseModel(Integer id, Date createDate, Date updateDate) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	
	public BaseModel() {
	}

}
