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

import vn.dev.cinema.model.CategoryModel;
import vn.dev.cinema.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping()
	public ResponseEntity<Map<String, List<CategoryModel>>> getCategories(){
		List<CategoryModel> models = categoryService.findAllModel();
		if(models.size() == 0) {
			Map<String, List<CategoryModel>> jsonResult = new HashMap<String, List<CategoryModel>>();
			jsonResult.put("not found! ", models);
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, List<CategoryModel>> jsonResult = new HashMap<String, List<CategoryModel>>();
		jsonResult.put("successfully", models);
		return ResponseEntity.ok(jsonResult);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, CategoryModel>> getCinema(@PathVariable("id") Integer id) {
		CategoryModel model = categoryService.getModelById(id);
		if(model.getId() == null) {
			Map<String, CategoryModel> jsonResult = new HashMap<String, CategoryModel>();
			jsonResult.put("CategoryId = "+ id +" is not found!", model);
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, CategoryModel> jsonResult = new HashMap<String, CategoryModel>();
		jsonResult.put("successfully", model);
		return ResponseEntity.ok(jsonResult);
	}
	
	@PostMapping()
	public ResponseEntity<Object> addCinema(@RequestBody CategoryModel model){
		if(model.getId() == null || model.getId() <= 0) {
			categoryService.saveCategory(model);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
		return new ResponseEntity<>("Fault: The id field is not necessary.", HttpStatus.EXPECTATION_FAILED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCinema(@PathVariable("id") Integer id){
		boolean status = false;
		status = categoryService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete category is", status);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateCinema(@RequestBody CategoryModel model,
			@PathVariable("id") Integer id){
		categoryService.updateCategory(model, id);
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("Update completely ", model);
		return ResponseEntity.ok(jsonResult);
	}
}
