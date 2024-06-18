package vn.dev.cinema.service;

import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.dev.cinema.entity.Message;
import vn.dev.cinema.entity.User;
import vn.dev.cinema.model.MessageModel;

@Service
public class MessageService extends BaseService<Message>{

	@Override
	public Class<Message> clazz() {
		return Message.class;
	}
	
	@Autowired
	private TransmitService transmitService;

	@Autowired
	private UserService userService;
	
	public MessageModel getModelById(Integer id) {
		MessageModel model = transmitService.messageToModel(super.getById(id));
		return model;
	}
	
	public List<MessageModel> findAllModel(){
		List<Message> entities = super.findAll();
		List<MessageModel> messages = transmitService.mesageEntitiesToModels(entities);
		return messages;
	}
	
	@Transactional
	public MessageModel saveMessage(MessageModel model, Integer senderId, Integer receiverId) {
		model.setCreateDate(new Date());
		
		User sendUser = userService.getById(senderId);
		try{
			model.setSenderId(sendUser.getId());
		}catch(Exception e) {
			throw new NullPointerException("sender id not found, sender is null");
		}
		
		User receiveUser = userService.getById(receiverId);
		try{
			model.setReceiverId(receiveUser.getId());
		}catch(Exception e) {
			throw new NullPointerException("receiver id not found, receiver is null");
		}
		Message entity = transmitService.messsageToEntity(model);
		super.saveOrUpdate(entity);
		return model;
	}
	
	@Transactional
	public void deleteById(Integer id) {
		super.deleteById(id);
	}
	
	@Transactional
	public MessageModel updatePatient(MessageModel model, Integer id) {
		Message entity = super.getById(id);
		entity.setUpdateDate(new Date());
		entity.setContent(model.getContent());
		super.saveOrUpdate(entity);
		return model;
	}


	public List<MessageModel> findAllConversation(Integer senderId, Integer receiverId) {
		String sql = "SELECT * FROM cinimakhoa.tbl_message where sender_id="+ senderId +" and receiver_id="+ receiverId +" or sender_id="+ receiverId +" and receiver_id="+ senderId +";";
		List<Message> entities = super.executeNativeSql(sql);
		List<MessageModel> models = transmitService.mesageEntitiesToModels(entities);
		return models;
	}
}
