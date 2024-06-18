package vn.dev.cinema.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.dev.cinema.entity.Ticket;
import vn.dev.cinema.model.TicketModel;

@Service
public class TicketService extends BaseService<Ticket>{

	@Override
	public Class<Ticket> clazz() {
		return Ticket.class;
	}

	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private TicketTypeService ticketTypeService;
	
	public TicketModel getModelById(Integer id) {
		return ticketToModel(super.getById(id));
	}
	
	
	public List<TicketModel> findAllModel() {
		return ticketEntitiesToModels(super.findAll());
	}
	
	public List<Ticket> getTicketByScheduleId(Integer scheduleId){
		String sql = "SELECT * FROM cinimakhoa.tbl_ticket where schedule_id=" + scheduleId + " ;";
		return super.executeNativeSql(sql);
	}

	@Transactional
	public String saveTicket(TicketModel model) {
		List<Ticket> soldCheck = getTicketByScheduleId(model.getScheduleId());
		for (Ticket t : soldCheck) {
			if(t.getPossition().equalsIgnoreCase(model.getPossition().trim())) {
				return "Vị trí này đã được đặt trước, vui lòng chọn vị trí khác";
			}
		}
		
		Double priceCeofficient = ticketTypeService.getById(model.getTicketTypeId()).getPriceCoefficient();
		Double filmPrice = scheduleService.getById(model.getScheduleId()).getFilm().getPrice();
		model.setPrice(filmPrice * priceCeofficient);
		model.setStatus(false);
		Ticket entity = ticketToEntity(model);
		entity.setCreateDate(new Date());
		super.saveOrUpdate(entity);
		return "Đặt vé thành công";
	}
	
	@Transactional
	public boolean delById(Integer id) {
		super.deleteById(id);
		return true;
	}

	@Transactional
	public String updateTicket(TicketModel model, Integer id) {
		List<Ticket> soldCheck = getTicketByScheduleId(model.getScheduleId());
		for (Ticket t : soldCheck) {
			if(t.getPossition().equalsIgnoreCase(model.getPossition().trim())) {
				return "Vị trí này đã được đặt trước, vui lòng chọn vị trí khác";
			}
		}
		
		Ticket entity = ticketToEntity(model);
		entity.setId(id);
		entity.setUpdateDate(new Date());
		super.saveOrUpdate(entity);
		return "Cập nhật đặt vé thành công";
	}
	
	@Transactional
	public boolean changeStatus(Integer id) {
		Ticket entity = super.getById(id);
		entity.setStatus(!entity.getStatus());
		return true;
	}

	List<TicketModel> ticketEntitiesToModels(List<Ticket> TicketEntities){
		List<TicketModel> Tickets = TicketEntities.stream()
				.map(entity -> new TicketModel(
						entity.getId(),
						entity.getCreateDate(),
						entity.getUpdateDate(),						
						entity.getSchedule().getId(),
						entity.getTicketType().getId(),
						entity.getPrice(),
						entity.getPossition(),
						entity.getStatus()
						)).collect(Collectors.toList());
		return Tickets;
	}

	
	TicketModel ticketToModel(Ticket entity) {
		TicketModel model = new TicketModel();
			model.setId(entity.getId());
			model.setCreateDate(entity.getCreateDate());
			model.setUpdateDate(entity.getUpdateDate());
			model.setScheduleId(entity.getSchedule().getId());
			model.setTicketTypeId(entity.getTicketType().getId());
			model.setPrice(entity.getPrice());
			model.setPossition(entity.getPossition());
			model.setStatus(entity.getStatus());
		return model;
	}

	
	Ticket ticketToEntity(TicketModel model) {
		Ticket entity = new Ticket();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setSchedule(scheduleService.getById(model.getScheduleId()));
		entity.setTicketType(ticketTypeService.getById(model.getTicketTypeId()));
		entity.setPrice(model.getPrice());
		entity.setPossition(model.getPossition());
		entity.setStatus(model.getStatus());
		return entity;
	}

	
}
