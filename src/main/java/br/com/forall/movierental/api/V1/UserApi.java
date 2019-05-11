package br.com.forall.movierental.api.V1;

import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.forall.movierental.api.V1.dto.UserDTO;
import br.com.forall.movierental.factory.UserDTOFactory;
import br.com.forall.movierental.service.LoginService;
import br.com.forall.movierental.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Users REST API")
@CrossOrigin(origins="*")
@RequestMapping(value="/api")
public class UserApi {
	
	private UserService userService;
	private LoginService loginService;
	private UserDTOFactory userDTOFactory;
	
	@Autowired
	public UserApi( UserService userService, LoginService loginService, UserDTOFactory userDTOFactory) {
		this.userService = userService;
		this.loginService = loginService;
		this.userDTOFactory = userDTOFactory;
	}
	
	@PostMapping
	@RequestMapping(value = "/public/users", method = RequestMethod.POST)
	@ApiOperation(value="This method is responsible for a new user insertion", notes="This method doesn't need authentication")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "User successfully registered!", response = UserDTO.class),
	})
	@ApiImplicitParams({
        @ApiImplicitParam(
            name = "user",
            dataType = "UserDTO",
            value = "Fill all the fields to create a user.") 
    })
	public ResponseEntity<?> insertUser(@Valid @RequestBody UserDTO user){
		try {
			return Stream.of(user)
	                    .map(userService::save)
	                    .map(userDTOFactory::build)
	                    .map(userDTO -> ResponseEntity.status(HttpStatus.CREATED).body(userDTO))
	                    .findFirst()
	                    .orElseThrow(NoSuchFieldException::new);
			
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}	  
	
	@PostMapping
	@RequestMapping(value = "/public/authentication", method = RequestMethod.POST)
	@ApiOperation(value="This method executes the user logon", notes = "This method doesn't need authentication")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User successfully authenticated!", response = String.class),
	})
	@ApiImplicitParams({
        @ApiImplicitParam(
            name = "user",
            dataType = "UserDTO",
            value = "Fill 'email' and 'password' only to log on the api.") 
    })
	public ResponseEntity<?> login(@Valid @RequestBody UserDTO user){
		try {
			loginService.logon(user.getEmail(), user.getPassword());
			return ResponseEntity.status(HttpStatus.OK).body("User successfully authenticated!");
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}	
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/private/disconnection", method = RequestMethod.POST)
	@ApiOperation(value="This method executes the user logout",  notes = "You must be logged on the api to execute this method.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User successfully disconnected!", response = String.class),
	})
	public ResponseEntity<?> logout(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body("User successfully disconnected!");
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}	
}
