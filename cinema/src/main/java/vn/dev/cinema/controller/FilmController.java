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
	public List<FilmModel> getCinemas(){
		return filmService.findAllModel();
	}
	
	@GetMapping("/search")
	public List<FilmModel> searchCiname(@RequestParam(value="title", required = false) String title, 
			@RequestParam(value="performer", required = false) String performer){
		return filmService.getModelByModelSearch(title,performer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FilmModel> getFilm(@PathVariable("id") Integer id) {
		FilmModel model = filmService.getModelById(id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<FilmModel> addFilm(@RequestBody FilmModel model){
		filmService.saveFilm(model);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteFilm(@PathVariable("id") Integer id){
		boolean status = false;
		status = filmService.delById(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("delete film ", status);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FilmModel> updateFilm(@RequestBody FilmModel model,
			@PathVariable("id") Integer id){
		filmService.updateFilm(model, id);
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@PostMapping("/upload-avatar/{filmId}")
    public FilmModel uploadImage(@RequestParam("file")MultipartFile file, @PathVariable("filmId") Integer filmId){
        Map data = this.cloudinaryService.upload(file);
        
        return filmService.uploadAvatar(data.get("url").toString(), filmId);
    }
}
