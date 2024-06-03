package vn.dev.cinema.model;

import java.util.Date;

public class TicketModel extends BaseModel {

	private Integer scheduleId;
	private Integer ticketTypeId;
	private Double price;
	private String possition;
	private boolean status;

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(Integer ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPossition() {
		return possition;
	}

	public void setPossition(String possition) {
		this.possition = possition;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public TicketModel(Integer id, Date createDate, Date updateDate, Integer scheduleId, Integer ticketTypeId,
			Double price, String possition, boolean status) {
		super(id, createDate, updateDate);
		this.scheduleId = scheduleId;
		this.ticketTypeId = ticketTypeId;
		this.price = price;
		this.possition = possition;
		this.status = status;
	}

	public TicketModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
