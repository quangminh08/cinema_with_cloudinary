package vn.dev.cinema.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.dev.cinema.auth.AuthenticationRequest;
import vn.dev.cinema.model.UserModel;
import vn.dev.cinema.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody AuthenticationRequest authenticationRequest){
		
		
		UserModel model = userService.getUserModelByUsernameAndPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		if(model == null) {
			Map<String, Object> jsonResult = new HashMap<String, Object>();
			jsonResult.put("code", 333);
			jsonResult.put("status", "Tài khoản hoặc mật khẩu không đúng");
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("successfully", model);
		return ResponseEntity.ok(jsonResult);
	}
	
}
