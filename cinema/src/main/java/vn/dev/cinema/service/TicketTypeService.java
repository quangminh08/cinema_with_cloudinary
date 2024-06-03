package vn.dev.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.dev.cinema.entity.TicketType;
import vn.dev.cinema.model.TicketTypeModel;

@Service
public class TicketTypeService extends BaseService<TicketType>{

	@Override
	public Class<TicketType> clazz() {
		return TicketType.class;
	}
	
	@Autowired
	private TransmitService transmitService;
	
	
	public TicketTypeModel getModelById(Integer id) {
		return transmitService.ticketTypeToModel(super.getById(id));
	}
	
	public List<TicketTypeModel> findAllModel(){
		return transmitService.ticketTypeEntitiesToModels(super.findAll());
	}

	@Transactional
	public void saveTicketType(TicketTypeModel model) {
		super.saveOrUpdate(transmitService.ticketTypeToEntity(model));
	}
	
	@Transactional
	public void updateTiketType(TicketTypeModel model, Integer id) {
		model.setId(id);
		TicketType entity = transmitService.ticketTypeToEntity(model);
		super.saveOrUpdate(entity);
	}
	
	@Transactional
	public boolean delById(Integer id) {
		super.deleteById(id);
		return true;
	}

	
}
