package vn.dev.cinema.model;

import java.util.Date;

public class UserModel extends BaseModel {

	private String username;
	private String passwrod;
	private String name;
	private String avatar;
	private Date dateOfBirth;
	private String description;
	private String phoneNumber;
	private String role;
	private Boolean status;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswrod() {
		if (this.passwrod == null) {
			return "";
		}
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserModel(Integer id, Date createDate, Date updateDate, String username, String passwrod, String name,
			String avatar, Date dateOfBirth, String description, String phoneNumber,
			String role, Boolean status) {
		super(id, createDate, updateDate);
		this.username = username;
		this.passwrod = passwrod;
		this.name = name;
		this.avatar = avatar;
		this.dateOfBirth = dateOfBirth;
		this.description = description;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
	}

	public UserModel() {
		super();
	}

}
