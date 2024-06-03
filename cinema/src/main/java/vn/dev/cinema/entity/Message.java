package vn.dev.cinema.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_message")
public class Message extends BaseEntity{
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="sender_id")
	User userOfMessage;
	
	@Column(name = "receiver_id", nullable = false)
	private Integer receiverId;
	
	@Column(name = "content", length = 5000, nullable = false )
	private String content;

	public Message(Integer id, Date createDate, Date updateDate, User userOfMessage, Integer receiverId,
			String content) {
		super(id, createDate, updateDate);
		this.userOfMessage = userOfMessage;
		this.receiverId = receiverId;
		this.content = content;
	}

	public Message() {
	}

	public User getUserOfMessage() {
		return userOfMessage;
	}

	public void setUserOfMessage(User userOfMessage) {
		this.userOfMessage = userOfMessage;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
