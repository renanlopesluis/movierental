package br.com.forall.movierental.api.V1;

import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.forall.movierental.api.V1.dto.RentalDTO;
import br.com.forall.movierental.factory.RentalDTOFactory;
import br.com.forall.movierental.service.RentalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Rentals REST API")
public class RentalApi implements V1{

	private RentalService rentalService;
	private RentalDTOFactory rentalDTOFactory;
	
	@Autowired
	public RentalApi(RentalService rentalService, RentalDTOFactory rentalDTOFactory) {
		this.rentalService = rentalService;
		this.rentalDTOFactory = rentalDTOFactory;
	}
	
	@RequestMapping(value="/private/rentals", method=RequestMethod.POST)
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value="This method is responsible for a new user rental", notes = "You must be logged on the api to execute this method.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Rental successfully registered!", response = RentalDTO.class),
	})
	@ApiImplicitParams({
        @ApiImplicitParam(
            name = "rental",
            dataType = "RentalDTO",
            value = "Fill only 'movieId' and 'userId' to rent a movie.") 
    })
	public ResponseEntity<?> rent(@Valid @RequestBody RentalDTO rental){
		try {
			return Stream.of(rental)
	                    .map(rentalService::save)
	                    .map(rentalDTOFactory::build)
	                    .map(rentalDTO -> ResponseEntity.status(HttpStatus.CREATED).body(rentalDTO))
	                    .findFirst()
	                    .orElseThrow(NoSuchFieldException::new);
			
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@RequestMapping(value="/private/giveBacks", method=RequestMethod.PATCH)
	@PatchMapping
	@PreAuthorize("hasRole('USER')")
	@ApiOperation(value="This method is responsible for a rental given back",  notes = "You must be logged on the api to execute this method.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Rental has successfully been given back!", response = RentalDTO.class),
	})
	@ApiImplicitParams({
        @ApiImplicitParam(
            name = "rental",
            dataType = "RentalDTO",
            value = "Fill only 'rentalId' to give back a movie.") 
    })
	public ResponseEntity<?> giveBack(@Valid @RequestBody RentalDTO rental){
		try {
			return Stream.of(rental)
	                    .map(rentalService::finalize)
	                    .map(rentalDTOFactory::build)
	                    .map(rentalDTO -> ResponseEntity.status(HttpStatus.OK).body(rentalDTO))
	                    .findFirst()
	                    .orElseThrow(NoSuchFieldException::new);
			
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
