package vn.dev.cinema.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.dev.cinema.entity.Film;
import vn.dev.cinema.model.FilmModel;

@Service
public class FilmService extends BaseService<Film>{

	@Override
	public Class<Film> clazz() {
		return Film.class;
	}
	
	@Autowired
	private CategoryService categoryService;
	
	
	public FilmModel getModelById(Integer id) {
		return filmToModel(super.getById(id));
	}
	
	
	public List<FilmModel> findAllModel() {
		return filmEntitiesToModels(super.findAll());
	}

	@Transactional
	public void saveFilm(FilmModel model) {
		Film entity = filmToEntity(model);
		entity.setCreateDate(new Date());
		super.saveOrUpdate(entity);
	}
	
	@Transactional
	public boolean delById(Integer id) {
		super.deleteById(id);
		return true;
	}

	@Transactional
	public void updateFilm(FilmModel model, Integer id) {
		Film entity = filmToEntity(model);
		entity.setId(id);
		entity.setUpdateDate(new Date());
		super.saveOrUpdate(entity);
	}

	@Transactional
	public FilmModel uploadAvatar(String url, Integer filmId) {
		Film entity = super.getById(filmId);
		entity.setAvatar(url);
		super.saveOrUpdate(entity);
		return filmToModel(entity);
	}
	
	public List<FilmModel> getModelByModelSearch(String title, String performer) {
		String sql = "SELECT * FROM cinimakhoa.tbl_film";
		
		if (title != null && title.trim() != "") {
			sql = sql + " where title like '%" + title +"%' ";
			
			if(performer != null && performer.trim() != "") {
				sql = sql + " and performer like '%" + performer +"%' order by id desc;";
			}else {
				sql = sql + " order by id desc";
			}
		} else if(performer != null && performer.trim() != "") {
			sql = sql + " where performer like '%" + performer + "%' order by id desc;";
		}else {
			sql = sql + " order by id desc";
		}
		List<Film> entities = super.executeNativeSql(sql);
		return filmEntitiesToModels(entities);
	}
	
	List<FilmModel> filmEntitiesToModels(List<Film> entities){
		List<FilmModel> categories = entities.stream()
				.map(entity -> new FilmModel(
						entity.getId(),
						entity.getCreateDate(),
						entity.getUpdateDate(),						
						entity.getTitle(),
						entity.getAvatar(),
						entity.getDirector(),
						entity.getPerformer(),
						entity.getCategory().getId(),
						entity.getPremiere(),
						entity.getLength(),
						entity.getLanguage(),
						entity.getDescription(),
						entity.getPrice()
						)).collect(Collectors.toList());
		return categories;
	}
	
	FilmModel filmToModel(Film entity) {
		FilmModel model = new FilmModel();
		model.setId(entity.getId());
		model.setCreateDate(entity.getCreateDate());
		model.setUpdateDate(entity.getUpdateDate());
		model.setTitle(entity.getTitle());
		model.setAvatar(entity.getAvatar());
		model.setDirector(entity.getDirector());
		model.setPerformer(entity.getPerformer());
		model.setCategoryId(entity.getCategory().getId());
		model.setPremiere(entity.getPremiere());
		model.setLength(entity.getLength());
		model.setLanguage(entity.getLanguage());
		model.setDescription(entity.getDescription());
		model.setPrice(entity.getPrice());
		return model;
	}
	
	Film filmToEntity(FilmModel model) {
		Film entity = new Film();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setTitle(model.getTitle());
		entity.setAvatar(model.getAvatar());
		entity.setDirector(model.getDirector());
		entity.setPerformer(model.getPerformer());
		entity.setCategory(categoryService.getById(model.getCategoryId()));
		entity.setPremiere(model.getPremiere());
		entity.setLength(model.getLength());
		entity.setLanguage(model.getLanguage());
		entity.setDescription(model.getDescription());
		entity.setPrice(model.getPrice());
		return entity;
	}


}
