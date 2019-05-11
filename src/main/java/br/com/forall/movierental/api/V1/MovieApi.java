package br.com.forall.movierental.api.V1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.forall.movierental.api.V1.dto.MovieDTO;
import br.com.forall.movierental.factory.MovieDTOFactory;
import br.com.forall.movierental.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Movies REST API")
public class MovieApi implements V1{
	
	private MovieService movieService;
	private MovieDTOFactory movieDTOFactory;
	
	
	@Autowired
	public MovieApi(MovieService movieService, MovieDTOFactory movieDTOFactory) {
		this.movieService = movieService;
		this.movieDTOFactory = movieDTOFactory;
	}
	
	@RequestMapping(value="/private/movies", method=RequestMethod.GET)
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value="This method returns all available movies for a rental",  notes = "You must be logged on the api to execute this method.")
	@ApiResponses(value = {
			@ApiResponse(code = 302, message = "Available movies has been found!", response = List.class),
	})
	public ResponseEntity<?>findAllAvailables(){
		try {
			return ResponseEntity.status(
					HttpStatus.FOUND).body(
							new Gson().toJson(
									movieDTOFactory.build(
											movieService.findAllAvailable()
									)
							)
					);
		
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/private/movie", method=RequestMethod.GET)
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value="This method returns a movie by its name",  notes = "You must be logged on the api to execute this method.")
	@ApiResponses(value = {
			@ApiResponse(code = 302, message = "Movie has been found!", response = MovieDTO.class),
	})
	public ResponseEntity<?> findByName(@RequestParam String title){
		try {
			return ResponseEntity.status(
					HttpStatus.FOUND).body(
							new Gson().toJson(
									movieDTOFactory.build(
											movieService.findByTitle(title).orElseThrow((NoSuchFieldException::new))
									)
							)
					);
		
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
