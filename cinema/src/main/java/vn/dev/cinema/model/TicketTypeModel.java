package vn.dev.cinema.model;

import java.util.Date;

public class TicketTypeModel extends BaseModel {

	private String name;

	private String object;

	private String chairType;

	private String dateType;

	private Double priceCoefficient;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getChairType() {
		return chairType;
	}

	public void setChairType(String chairType) {
		this.chairType = chairType;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public Double getPriceCoefficient() {
		return priceCoefficient;
	}

	public void setPriceCoefficient(Double priceCoefficient) {
		this.priceCoefficient = priceCoefficient;
	}

	public TicketTypeModel(Integer id, Date createDate, Date updateDate, String name, String object, String chairType,
			String dateType, Double priceCoefficient) {
		super(id, createDate, updateDate);
		this.name = name;
		this.object = object;
		this.chairType = chairType;
		this.dateType = dateType;
		this.priceCoefficient = priceCoefficient;
	}

	public TicketTypeModel() {
		super();
	}

}
