package vn.dev.cinema.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.dev.cinema.entity.Category;
import vn.dev.cinema.entity.Cinema;
import vn.dev.cinema.entity.Message;
import vn.dev.cinema.entity.TicketType;
import vn.dev.cinema.model.SticketTypeModel;
import vn.dev.cinema.model.CategoryModel;
import vn.dev.cinema.model.CinemaModel;
import vn.dev.cinema.model.MessageModel;
import vn.dev.cinema.model.TicketTypeModel;



@Service
public class TransmitService {	
	
	@Autowired
	private UserService userService;
	
	
	List<MessageModel> mesageEntitiesToModels(List<Message> mesageEntities){
		List<MessageModel> messages = mesageEntities.stream()
				.map(messageEntity -> new MessageModel(
						messageEntity.getId(),
						messageEntity.getCreateDate(),
						messageEntity.getUpdateDate(),						
						messageEntity.getUserOfMessage().getId(),
						messageEntity.getReceiverId(),
						messageEntity.getContent()
						)).collect(Collectors.toList());
		return messages;
	}
	
	MessageModel messageToModel(Message entity) {
		MessageModel model = new MessageModel();
			model.setId(entity.getId());
			model.setCreateDate(entity.getCreateDate());
			model.setUpdateDate(entity.getUpdateDate());
			model.setSenderId(entity.getUserOfMessage().getId());
			model.setReceiverId(entity.getReceiverId());
			model.setContent(entity.getContent());
		return model;
	}

	
	Message messsageToEntity(MessageModel model) {
		Message entity = new Message();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setUserOfMessage(userService.getById(model.getSenderId()));
		entity.setReceiverId(model.getReceiverId());
		entity.setContent(model.getContent());
		return entity;
	}

	
	
	List<CinemaModel> cinemaEntitiesToModels(List<Cinema> entities){
		List<CinemaModel> cinemas = entities.stream()
				.map(entity -> new CinemaModel(
						entity.getId(),
						entity.getCreateDate(),
						entity.getUpdateDate(),						
						entity.getName(),
						entity.getAddress(),
						entity.getManager().getId()
						)).collect(Collectors.toList());
		return cinemas;
	}
	
	CinemaModel cinemaToModel(Cinema entity) {
		CinemaModel model = new CinemaModel();
		model.setId(entity.getId());
		model.setCreateDate(entity.getCreateDate());
		model.setUpdateDate(entity.getUpdateDate());
		model.setName(entity.getName());
		model.setAddress(entity.getAddress());
		model.setManagerId(entity.getManager().getId());
		return model;
	}
	
	Cinema cinemaToEntity(CinemaModel model) {
		Cinema entity = new Cinema();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setName(model.getName());
		entity.setAddress(model.getAddress());
		entity.setManager(userService.getById(model.getManagerId()));
		return entity;
	}
	
	
	List<CategoryModel> categoryEntitiesToModels(List<Category> entities){
		List<CategoryModel> categories = entities.stream()
				.map(entity -> new CategoryModel(
						entity.getId(),
						entity.getCreateDate(),
						entity.getUpdateDate(),						
						entity.getName(),
						entity.getDescription()
						)).collect(Collectors.toList());
		return categories;
	}
	
	CategoryModel categoryToModel(Category entity) {
		CategoryModel model = new CategoryModel();
		model.setId(entity.getId());
		model.setCreateDate(entity.getCreateDate());
		model.setUpdateDate(entity.getUpdateDate());
		model.setName(entity.getName());
		model.setDescription(entity.getDescription());
		return model;
	}
	
	Category categoryToEntity(CategoryModel model) {
		Category entity = new Category();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setName(model.getName());
		entity.setDescription(model.getDescription());
		return entity;
	}
	
	
	List<TicketTypeModel> ticketTypeEntitiesToModels(List<TicketType> entities){
		List<TicketTypeModel> ticketTypeModels = entities.stream()
				.map(entity -> new TicketTypeModel(
						entity.getId(),
						entity.getCreateDate(),
						entity.getUpdateDate(),						
						entity.getName(),
						entity.getObject(),
						entity.getChairType(),
						entity.getDateType(),
						entity.getPriceCoefficient()
						)).collect(Collectors.toList());
		return ticketTypeModels;
	}
	
	TicketTypeModel ticketTypeToModel(TicketType entity) {
		TicketTypeModel model = new TicketTypeModel();
		model.setId(entity.getId());
		model.setCreateDate(entity.getCreateDate());
		model.setUpdateDate(entity.getUpdateDate());
		model.setName(entity.getName());
		model.setObject(entity.getObject());
		model.setChairType(entity.getChairType());
		model.setDateType(entity.getDateType());
		model.setPriceCoefficient(entity.getPriceCoefficient());
		return model;
	}
	
	TicketType ticketTypeToEntity(TicketTypeModel model) {
		TicketType entity = new TicketType();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setName(model.getName());
		entity.setObject(model.getObject());
		entity.setChairType(model.getChairType());
		entity.setDateType(model.getDateType());
		entity.setPriceCoefficient(model.getPriceCoefficient());
		return entity;
	}
	

}
