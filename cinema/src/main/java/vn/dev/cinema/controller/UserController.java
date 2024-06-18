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
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
	@GetMapping("")
	public ResponseEntity<Map<String, List<UserModel>>> accounts(){
		List<UserModel> models = userService.findAllActiveModels();
		
		if(models.size() == 0) {
			Map<String, List<UserModel>> jsonResult = new HashMap<String, List<UserModel>>();
			jsonResult.put("not found! ", models);
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, List<UserModel>> jsonResult = new HashMap<String, List<UserModel>>();
		jsonResult.put("successfully", models);
		return ResponseEntity.ok(jsonResult);
	}
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
	@GetMapping("/search")
	public ResponseEntity<Map<String, List<UserModel>>> searchAccount(@RequestParam(value="name", required = false) String name, 
			@RequestParam(value="phone", required = false) String phone){
		List<UserModel> models = userService.getModelByModelSearch(name,phone);
		
		if(models.size() == 0) {
			Map<String, List<UserModel>> jsonResult = new HashMap<String, List<UserModel>>();
			jsonResult.put("not found! ", models);
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, List<UserModel>> jsonResult = new HashMap<String, List<UserModel>>();
		jsonResult.put("successfully", models);
		return ResponseEntity.ok(jsonResult);
		
	}
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UserModel> getuser(@PathVariable("id") int id){
		return new ResponseEntity<>(userService.getModelById(id), HttpStatus.OK);
	}
	
	@GetMapping("profile")
	public ResponseEntity<UserModel> getUserModelLogined(){
		return new ResponseEntity<>(userService.getModelLogined(), HttpStatus.OK);
	}
	
	@PostMapping("/add-customer")
	public ResponseEntity<Object> saveAddCustomer(@RequestBody UserModel model ) {	
		if (model.getId() == null || model.getId() <=0 ) {
//			return userService.saveAddCustomer(model);
			userService.saveAddCustomer(model);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
		return new ResponseEntity<>("Fault: The id field is not necessary.", HttpStatus.EXPECTATION_FAILED);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/add-staff")
	public ResponseEntity<Object> saveAddStaff(@RequestBody UserModel model) {
		if (model.getId() == null || model.getId() <=0 ) {
			userService.saveAddStaff(model);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
		return new ResponseEntity<>("Fault: The id field is not necessary.", HttpStatus.EXPECTATION_FAILED);
	}
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
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
	
	@PostMapping("/upload-avatar/{userId}")
    public UserModel uploadImage(@RequestParam("file")MultipartFile file, @PathVariable("userId") Integer userId){
        Map data = this.cloudinaryService.upload(file);
        
        return userService.uploadAvatar(data.get("url").toString(), userId);
    }
}
