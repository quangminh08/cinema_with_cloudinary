package vn.dev.cinema.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.dev.cinema.entity.Cinema;
import vn.dev.cinema.model.CinemaModel;

@Service
public class CinemaService extends BaseService<Cinema>{

	@Override
	public Class<Cinema> clazz() {
		return Cinema.class;
	}

	@Autowired
	private TransmitService transmitService;
	
	public CinemaModel getModelById(Integer id) {
		return transmitService.cinemaToModel(super.getById(id));
	}

	@Transactional
	public void saveCinema(CinemaModel model) {
		Cinema entity = transmitService.cinemaToEntity(model);
		entity.setCreateDate(new Date());
		super.saveOrUpdate(entity);
	}
	
	@Transactional
	public boolean delById(Integer id) {
		super.deleteById(id);
		return true;
	}

	@Transactional
	public void updateCinema(CinemaModel model, Integer id) {
		Cinema entity = transmitService.cinemaToEntity(model);
		entity.setId(id);
		entity.setUpdateDate(new Date());
		super.saveOrUpdate(entity);
	}

	public List<CinemaModel> findAllModel() {
		return transmitService.cinemaEntitiesToModels(super.findAll());
	}

	public List<CinemaModel> getModelByModelSearch(String name, String address) {
		String sql = "SELECT * FROM cinimakhoa.tbl_cinema";
		
		if (name != null && name.trim() != "") {
			sql = sql + " where name like '%" + name +"%' ";
			
			if(address != null && address.trim() != "") {
				sql = sql + " and address like '%" + address +"%' order by id desc;";
			}else {
				sql = sql + " order by id desc";
			}
		} else if(address != null && address.trim() != "") {
			sql = sql + " where address like '%" + address + "%' order by id desc;";
		}else {
			sql = sql + " order by id desc";
		}
		List<Cinema> entities = super.executeNativeSql(sql);
		return transmitService.cinemaEntitiesToModels(entities);
	}
}
