package vn.dev.cinema.model;

import java.util.Date;

public class ScheduleModel extends BaseModel {
	private Integer cinemaId;
	private Integer filmId;
	private Date time;

	public ScheduleModel(Integer id, Date createDate, Date updateDate, Integer cinemaId, Integer filmId, Date time) {
		super(id, createDate, updateDate);
		this.cinemaId = cinemaId;
		this.filmId = filmId;
		this.time = time;
	}

	public ScheduleModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}

	public Integer getFilmId() {
		return filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
