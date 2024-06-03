package vn.dev.cinema.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.dev.cinema.model.TicketModel;
import vn.dev.cinema.service.TicketService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@GetMapping()
	public List<TicketModel> getTickets(){
		return ticketService.findAllModel();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TicketModel> getTicket(@PathVariable("id") Integer id) {
		TicketModel model = ticketService.getModelById(id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<TicketModel> addTicket(@RequestBody TicketModel model){
		ticketService.saveTicket(model);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTicket(@PathVariable("id") Integer id){
		boolean status = false;
		status = ticketService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete ticket ", status);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TicketModel> updateTicket(@RequestBody TicketModel model,
			@PathVariable("id") Integer id){
		ticketService.updateTicket(model, id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	
	// Xác nhận và gửi vé
	@PostMapping("/status/{id}")
	public ResponseEntity<Map<String, Boolean>> confirmTicket(@PathVariable("id") Integer id){
		boolean status = false;
		status = ticketService.changeStatus(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("set status ticket ", status);
		return ResponseEntity.ok(response);
	}
}
