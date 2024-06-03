package vn.dev.cinema.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.dev.cinema.model.MessageModel;
import vn.dev.cinema.service.MessageService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	
	@GetMapping("/{id}")
	public MessageModel message(@PathVariable("id") Integer id) {
		return messageService.getModelById(id);
	}
	
	@GetMapping("/conversation/{senderId}/{receiverId}")
	public List<MessageModel> messages(@PathVariable("senderId") Integer senderId ,@PathVariable("receiverId") Integer receiverId){
		return messageService.findAllConversation(senderId, receiverId);
	}
	
	@PostMapping("conversation/{senderId}/{receiverId}")
	public MessageModel savePatientMessage(@RequestBody MessageModel message, @PathVariable("senderId") Integer senderId ,@PathVariable("receiverId") Integer receiverId) {
		return messageService.saveMessage(message, senderId, receiverId);
	}

	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") Integer id) {
		messageService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public MessageModel update(@RequestBody MessageModel model, @PathVariable("id") Integer id) {
		return messageService.updatePatient(model, id);
	}
	
}
