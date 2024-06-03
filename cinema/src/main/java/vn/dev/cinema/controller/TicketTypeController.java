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

import vn.dev.cinema.model.TicketTypeModel;
import vn.dev.cinema.service.TicketTypeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/ticket-types")
public class TicketTypeController {

	@Autowired
	private TicketTypeService ticketTypeService;
	
	@GetMapping()
	public List<TicketTypeModel> getCinemas(){
		return ticketTypeService.findAllModel();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TicketTypeModel> getCinema(@PathVariable("id") Integer id) {
		TicketTypeModel model = ticketTypeService.getModelById(id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<TicketTypeModel> addCinema(@RequestBody TicketTypeModel model){
		ticketTypeService.saveTicketType(model);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCinema(@PathVariable("id") Integer id){
		boolean status = false;
		status = ticketTypeService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("add cinema", status);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TicketTypeModel> updateCinema(@RequestBody TicketTypeModel model,
			@PathVariable("id") Integer id){
		ticketTypeService.updateTiketType(model, id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
}
