package vn.dev.cinema.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY
	@Column(name = "id")
	private Integer id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "create_date", nullable = true)
	private Date createDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "update_date", nullable = true)
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

	public BaseEntity(Integer id, Date createDate, Date updateDate) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	
	public BaseEntity() {}

}
