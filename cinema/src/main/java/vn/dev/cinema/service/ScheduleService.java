package vn.dev.cinema.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.dev.cinema.entity.Schedule;
import vn.dev.cinema.model.ScheduleModel;

@Service
public class ScheduleService extends BaseService<Schedule>{

	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private FilmService filmService;
	
	public ScheduleModel getModelById(Integer id) {
		return scheduleToModel(super.getById(id));
	}
	
	
	public List<ScheduleModel> findAllModel() {
		return scheduleEntitiesToModels(super.findAll());
	}

	@Transactional
	public void saveSchedule(ScheduleModel model) {
		if(model.getFilmId() == null || model.getCinemaId() == null) {
			throw new ExceptionInInitializerError("film id and cinema id is not null");
		}
		Schedule entity = scheduleToEntity(model);
		entity.setCreateDate(new Date());
		super.saveOrUpdate(entity);
	}
	
	@Transactional
	public boolean delById(Integer id) {
		super.deleteById(id);
		return true;
	}

	@Transactional
	public void updateSchedule(ScheduleModel model, Integer id) {
		if(model.getFilmId() == null || model.getCinemaId() == null) {
			throw new ExceptionInInitializerError("film id and cinema id is not null");
		}
		Schedule entity = scheduleToEntity(model);
		entity.setId(id);
		entity.setUpdateDate(new Date());
		super.saveOrUpdate(entity);
	}

	public List<ScheduleModel> getModelByModelSearch(String _date) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parseDate = sdf.parse(_date);
		String date = sdf.format(parseDate);
		System.out.println("DATE EEEEEEEE == " + date);
		
		String sql = "SELECT * FROM cinimakhoa.tbl_schedule ";
		
		if (date != null && date.trim() != "") {
			sql = sql + " where time between '" + date.trim() + " 00:00:00" +"' and '" + date.trim() + " 23:59:59'" + ";";
			
		}
		System.out.println("SQL === " + sql);
		List<Schedule> entities = super.executeNativeSql(sql);
		return scheduleEntitiesToModels(entities);
	}
	
	List<ScheduleModel> scheduleEntitiesToModels(List<Schedule> scheduleEntities){
		List<ScheduleModel> schedules = scheduleEntities.stream()
				.map(entity -> new ScheduleModel(
						entity.getId(),
						entity.getCreateDate(),
						entity.getUpdateDate(),						
						entity.getCinema().getId(),
						entity.getFilm().getId(),
						entity.getTime()
						)).collect(Collectors.toList());
		return schedules;
	}
	
	ScheduleModel scheduleToModel(Schedule entity) {
		ScheduleModel model = new ScheduleModel();
			model.setId(entity.getId());
			model.setCreateDate(entity.getCreateDate());
			model.setUpdateDate(entity.getUpdateDate());
			model.setCinemaId(entity.getCinema().getId());
			model.setFilmId(entity.getFilm().getId());
			model.setTime(entity.getTime());
		return model;
	}

	
	Schedule scheduleToEntity(ScheduleModel model) {
		Schedule entity = new Schedule();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setCinema(cinemaService.getById(model.getCinemaId()));
		entity.setFilm(filmService.getById(model.getFilmId()));
		entity.setTime(model.getTime());
		return entity;
	}

	@Override
	public Class<Schedule> clazz() {
		return Schedule.class;
	}
}

