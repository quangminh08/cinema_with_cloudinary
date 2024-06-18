package vn.dev.cinema.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	public ResponseEntity<Map<String, List<ScheduleModel>>> getCinemas(){
		List<ScheduleModel> models = scheduleService.findAllModel();
		
		if(models.size() == 0) {
			Map<String, List<ScheduleModel>> jsonResult = new HashMap<String,  List<ScheduleModel>>();
			jsonResult.put("not found! ", models);
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, List<ScheduleModel>> jsonResult = new HashMap<String,  List<ScheduleModel>>();
		jsonResult.put("successfully", models);
		return ResponseEntity.ok(jsonResult);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ScheduleModel> getSchedule(@PathVariable("id") Integer id) {
		ScheduleModel model = scheduleService.getModelById(id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Map<String, List<ScheduleModel>>> searchSchedule(@RequestParam(value="date", required = false) String date) throws ParseException{
		List<ScheduleModel> models = scheduleService.getModelByModelSearch(date);
		
		if(models.size() == 0) {
			Map<String, List<ScheduleModel>> jsonResult = new HashMap<String,  List<ScheduleModel>>();
			jsonResult.put("not found! ", models);
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, List<ScheduleModel>> jsonResult = new HashMap<String,  List<ScheduleModel>>();
		jsonResult.put("successfully", models);
		return ResponseEntity.ok(jsonResult);
	}
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
	@PostMapping()
	public ResponseEntity<ScheduleModel> addSchedule(@RequestBody ScheduleModel model){
		scheduleService.saveSchedule(model);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteSchedule(@PathVariable("id") Integer id){
		boolean status = false;
		status = scheduleService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete success", status);
		return ResponseEntity.ok(response);
	}
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, ScheduleModel>> updateSchedule(@RequestBody ScheduleModel model,
			@PathVariable("id") Integer id){
		if(model == null || id == null) {
			Map<String, ScheduleModel> response = new HashMap<String, ScheduleModel>();
			response.put("not found!", new ScheduleModel());
			return ResponseEntity.ok(response);
		}
		Map<String, ScheduleModel> response = new HashMap<String, ScheduleModel>();
		response.put("update completely", model);
		scheduleService.updateSchedule(model, id);
		return ResponseEntity.ok(response);
	}
}
