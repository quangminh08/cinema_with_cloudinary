package vn.dev.cinema.model;

import java.util.Date;

public class MessageModel extends BaseModel {
	private Integer senderId;
	private Integer receiverId;
	private String content;



	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
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

	public MessageModel(Integer id, Date createDate, Date updateDate, Integer senderId, Integer receiverId,
			String content) {
		super(id, createDate, updateDate);
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.content = content;
	}

	public MessageModel() {
	}

}
