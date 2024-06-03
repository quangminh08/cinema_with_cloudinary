package vn.dev.cinema.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dev.cinema.model.ScheduleModel;
import vn.dev.cinema.service.ScheduleService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/schedules")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping()
	public List<ScheduleModel> getCinemas(){
		return scheduleService.findAllModel();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ScheduleModel> getSchedule(@PathVariable("id") Integer id) {
		ScheduleModel model = scheduleService.getModelById(id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public List<ScheduleModel> searchSchedule(@RequestParam(value="date", required = false) String date) throws ParseException{
		return scheduleService.getModelByModelSearch(date);
	}
	
	@PostMapping()
	public ResponseEntity<ScheduleModel> addSchedule(@RequestBody ScheduleModel model){
		scheduleService.saveSchedule(model);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteSchedule(@PathVariable("id") Integer id){
		boolean status = false;
		status = scheduleService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("add cinema", status);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ScheduleModel> updateSchedule(@RequestBody ScheduleModel model,
			@PathVariable("id") Integer id){
		scheduleService.updateSchedule(model, id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
}
