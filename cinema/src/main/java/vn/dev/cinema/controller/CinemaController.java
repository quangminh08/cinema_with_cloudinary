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
	public List<CinemaModel> getCinemas(){
		return cinemaService.findAllModel();
	}
	
	@GetMapping("/search")
	public List<CinemaModel> searchCiname(@RequestParam(value="name", required = false) String name, 
			@RequestParam(value="address", required = false) String address){
		
		return cinemaService.getModelByModelSearch(name,address);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CinemaModel> getCinema(@PathVariable("id") Integer id) {
		CinemaModel model = cinemaService.getModelById(id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<CinemaModel> addCinema(@RequestBody CinemaModel model){
		cinemaService.saveCinema(model);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCinema(@PathVariable("id") Integer id){
		boolean status = false;
		status = cinemaService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("add cinema", status);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CinemaModel> updateCinema(@RequestBody CinemaModel model,
			@PathVariable("id") Integer id){
		cinemaService.updateCinema(model, id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
}
