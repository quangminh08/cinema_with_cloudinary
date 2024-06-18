package vn.dev.cinema.controller;

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

import vn.dev.cinema.model.CinemaModel;
import vn.dev.cinema.service.CinemaService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/cinemas")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	@GetMapping()
	public ResponseEntity<Map<String, List<CinemaModel>>> getCinemas(){
		List<CinemaModel> models = cinemaService.findAllModel();
		
		if(models.size() == 0) {
			Map<String, List<CinemaModel>> jsonResult = new HashMap<String, List<CinemaModel>>();
			jsonResult.put("not found! ", models);
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, List<CinemaModel>> jsonResult = new HashMap<String, List<CinemaModel>>();
		jsonResult.put("successfully", models);
		return ResponseEntity.ok(jsonResult);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Map<String, List<CinemaModel>>> searchCiname(@RequestParam(value="name", required = false) String name, 
			@RequestParam(value="address", required = false) String address){
		List<CinemaModel> models = cinemaService.getModelByModelSearch(name,address);
		
		if(models.size() == 0) {
			Map<String, List<CinemaModel>> jsonResult = new HashMap<String, List<CinemaModel>>();
			jsonResult.put("not found! ", models);
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, List<CinemaModel>> jsonResult = new HashMap<String, List<CinemaModel>>();
		jsonResult.put("successfully", models);
		return ResponseEntity.ok(jsonResult);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CinemaModel> getCinema(@PathVariable("id") Integer id) {
		CinemaModel model = cinemaService.getModelById(id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping()
	public ResponseEntity<Object> addCinema(@RequestBody CinemaModel model){
		if(model.getId() == null || model.getId() <= 0) {
			cinemaService.saveCinema(model);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
		return new ResponseEntity<>("Fault: The id field is not necessary.", HttpStatus.EXPECTATION_FAILED);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCinema(@PathVariable("id") Integer id){
		boolean status = false;
		status = cinemaService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("add cinema", status);
		return ResponseEntity.ok(response);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateCinema(@RequestBody CinemaModel model,
			@PathVariable("id") Integer id){
		cinemaService.updateCinema(model, id);
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("Update completely ", model);
		return ResponseEntity.ok(jsonResult);
	}
	
}
