package br.com.forall.movierental.serviceTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.forall.movierental.api.V1.dto.UserDTO;
import br.com.forall.movierental.entity.User;
import br.com.forall.movierental.factory.UserDTOFactory;
import br.com.forall.movierental.service.LoginService;
import br.com.forall.movierental.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginServiceTest {
	
	@Autowired
	private UserService service;

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserDTOFactory factory;
	
	@Before
	public void init() {
		clean();
	}
	
	@Test
	public void shouldLogon() {
			UserDTO dto = factory.build(new User(null, "Renan", "renan@gmail.com", "12345"));
			service.save(dto);
			UserDetails userDetails = loginService.loadUserByUsername(dto.getEmail());
		
			Assert.assertNotNull(userDetails);	
	}

	@Test(expected = UsernameNotFoundException.class)
	public void shouldLogonByNonRegisteredEmail() {
			UserDTO dto = factory.build(new User(null, "Renan", "renan@gmail.com", "12345"));
			service.save(dto);
			loginService.loadUserByUsername("anyone@anymail.com");
	}
	
	@Test
	public void shouldLogonByInvalidPassword() {
		UserDetails userDetails = null;
		UserDTO dto = factory.build(new User(null, "Renan", "renan@gmail.com", "12345"));
		
		service.save(dto);
		dto.setPassword("anypassword");
		userDetails = loginService.loadUserByUsername(dto.getEmail());
	
		dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
		Assert.assertFalse(userDetails.getPassword().equals(dto.getPassword()));
		
	}
	
	private void clean() {
		service.getRepository().deleteAll();
	}

}
