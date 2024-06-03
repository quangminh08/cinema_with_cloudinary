package vn.dev.cinema.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.JoinColumn;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_role")
public class Role extends BaseEntity implements GrantedAuthority{
	
	@Column(name = "name", length = 500, nullable = false)
	private String name;
	
	@Column(name = "description", length = 500, nullable = true)
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(	name = "tbl_user_role", 
				joinColumns = @JoinColumn(name = "role_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users = new ArrayList<User>();

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
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Role(Integer id, Date createDate, Date updateDate, String name, String description, List<User> users) {
		super(id, createDate, updateDate);
		this.name = name;
		this.description = description;
		this.users = users;
	}

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}
	
		
}
