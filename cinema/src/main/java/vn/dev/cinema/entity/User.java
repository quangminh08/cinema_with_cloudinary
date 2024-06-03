package vn.dev.cinema.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity implements UserDetails {

	@Column(name = "username", length = 45, nullable = false)
	private String username;

	@Column(name = "password", length = 45, nullable = false)
	private String password;

	@Column(name = "name", length = 45, nullable = true)
	private String name;

	@Column(name = "avatar", length = 255, nullable = true)
	private String avatar;

	@Column(name = "date_of_birth", length = 45, nullable = true)
	private Date dateOfBirth;

	@Column(name = "description", length = 1000, nullable = true)
	private String description;

	@Column(name = "phone_number", length = 1000, nullable = true)
	private String phoneNumber;

	@Column(name = "role", length = 45, nullable = true)
	private String role;

	// inactive account
	@Column(name = "status", nullable = true)
	private Boolean status;

	// Mapping one-to-many: product-to-sale_order_product
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userOfMessage")
	private Set<Message> messages = new HashSet<>();

	public void addRelationalMessage(Message message) {
		messages.add(message);
		message.setUserOfMessage(this);
	}

	public void deleteRelationalMessage(Message message) {
		messages.remove(message);
		message.setUserOfMessage(null);
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	private Set<Role> roles = new HashSet<>();

	public void addRole(Role role) {
		role.getUsers().add(this);
		roles.add(role);
	}

	public void deleteRole(Role role) {
		role.getUsers().remove(this);
		roles.remove(role);
	}

	@OneToOne(mappedBy = "manager")
	private Cinema cinema;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public User(Integer id, Date createDate, Date updateDate, String username, String password, String name,
			String avatar, Date dateOfBirth, String description,
			String phoneNumber, String role, Boolean status, Set<Message> messages,
			Set<Role> roles, Cinema cinema) {
		super(id, createDate, updateDate);
		this.username = username;
		this.password = password;
		this.name = name;
		this.avatar = avatar;
		this.dateOfBirth = dateOfBirth;
		this.description = description;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
		this.messages = messages;
		this.roles = roles;
		this.cinema = cinema;
	}

	public User() {
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
