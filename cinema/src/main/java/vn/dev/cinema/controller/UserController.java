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
import org.springframework.web.multipart.MultipartFile;

import vn.dev.cinema.model.UserModel;
import vn.dev.cinema.service.CloudinaryService;
import vn.dev.cinema.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("")
	public List<UserModel> accounts(){
		return userService.findAllActiveModels();
	}
	
	@GetMapping("/search")
	public List<UserModel> searchAccount(@RequestParam(value="name", required = false) String name, 
			@RequestParam(value="phone", required = false) String phone){
		
		return userService.getModelByModelSearch(name,phone);
	}
	
	@GetMapping("/{id}")
	public UserModel patient(@PathVariable("id") int id){
		return userService.getModelById(id);
	}
	
	@PostMapping("/add-customer")
	public ResponseEntity<UserModel> saveAddCustomer(@RequestBody UserModel model ) {		
		if (model.getId() == null || model.getId() <=0 ) {
//			return userService.saveAddCustomer(model);
			userService.saveAddCustomer(model);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
		return new ResponseEntity<>(model, HttpStatus.EXPECTATION_FAILED);
	}
	
	@PostMapping("/add-staff")
	public ResponseEntity<UserModel> saveAddStaff(@RequestBody UserModel model) {
		if (model.getId() == null || model.getId() <=0 ) {
			userService.saveAddStaff(model);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
		return new ResponseEntity<>(model, HttpStatus.EXPECTATION_FAILED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable("id") int id) {
		boolean status = false;
		status = userService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("change status user", status);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel model, @PathVariable("id") int id) {
		userService.updateUser(model, id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	
}
