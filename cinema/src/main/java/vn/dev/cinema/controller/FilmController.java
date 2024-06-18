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

import vn.dev.cinema.model.FilmModel;
import vn.dev.cinema.service.CloudinaryService;
import vn.dev.cinema.service.FilmService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/films")
public class FilmController {

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@GetMapping()
	public ResponseEntity<Map<String, List<FilmModel>>> getCinemas(){
		List<FilmModel> models = filmService.findAllModel();
		
		if(models.size() == 0) {
			Map<String, List<FilmModel>> jsonResult = new HashMap<String, List<FilmModel>>();
			jsonResult.put("not found! ", models);
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, List<FilmModel>> jsonResult = new HashMap<String, List<FilmModel>>();
		jsonResult.put("successfully", models);
		return ResponseEntity.ok(jsonResult);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Map<String, List<FilmModel>>> searchCiname(@RequestParam(value="title", required = false) String title, 
			@RequestParam(value="performer", required = false) String performer){
		List<FilmModel> models = filmService.getModelByModelSearch(title,performer);
		if(models.size() == 0) {
			Map<String, List<FilmModel>> jsonResult = new HashMap<String, List<FilmModel>>();
			jsonResult.put("not found! ", models);
			return ResponseEntity.ok(jsonResult);
		}
		Map<String, List<FilmModel>> jsonResult = new HashMap<String, List<FilmModel>>();
		jsonResult.put("successfully", models);
		return ResponseEntity.ok(jsonResult);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FilmModel> getFilm(@PathVariable("id") Integer id) {
		FilmModel model = filmService.getModelById(id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
	@PostMapping()
	public ResponseEntity<Object> addFilm(@RequestBody FilmModel model){
		if (model.getId() == null || model.getId() <=0 ) {
			filmService.saveFilm(model);
			return new ResponseEntity<>(model, HttpStatus.OK);
		}
		return new ResponseEntity<>("Fault: The id field is not necessary.", HttpStatus.EXPECTATION_FAILED);
	}
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteFilm(@PathVariable("id") Integer id){
		boolean status = false;
		status = filmService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete film ", status);
		return ResponseEntity.ok(response);
	}
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateFilm(@RequestBody FilmModel model,
			@PathVariable("id") Integer id){
		filmService.updateFilm(model, id);
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("Update completely ", model);
		return ResponseEntity.ok(jsonResult);
	}
	
	@PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
	@PostMapping("/upload-avatar/{filmId}")
    public FilmModel uploadImage(@RequestParam("file")MultipartFile file, @PathVariable("filmId") Integer filmId){
        Map data = this.cloudinaryService.upload(file);
        
        return filmService.uploadAvatar(data.get("url").toString(), filmId);
    }
}
