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

import vn.dev.cinema.model.SticketTypeModel;
import vn.dev.cinema.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	public List<SticketTypeModel> getCinemas(){
		return categoryService.findAllModel();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SticketTypeModel> getCinema(@PathVariable("id") Integer id) {
		SticketTypeModel model = categoryService.getModelById(id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<SticketTypeModel> addCinema(@RequestBody SticketTypeModel model){
		categoryService.saveCategory(model);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCinema(@PathVariable("id") Integer id){
		boolean status = false;
		status = categoryService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete category", status);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SticketTypeModel> updateCinema(@RequestBody SticketTypeModel model,
			@PathVariable("id") Integer id){
		categoryService.updateCategory(model, id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
}
