package br.com.forall.movierental.serviceTest;

import org.hibernate.procedure.NoSuchParameterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.forall.movierental.api.V1.dto.UserDTO;
import br.com.forall.movierental.entity.User;
import br.com.forall.movierental.factory.UserDTOFactory;
import br.com.forall.movierental.factorytest.UserFactoryTest;
import br.com.forall.movierental.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserService service;

	@Autowired
	private UserDTOFactory DTOfactory;
	
	@Autowired
	private UserFactoryTest factory;
			
	@Before
	public void init() {
		clean();
	}
	
	@Test
	public void shouldInsertAUser(){
		UserDTO dto = DTOfactory.build(factory.build());
		User user = service.save(dto);
	
		Assert.assertNotNull(user.getId());
	}

	@Test(expected = NoSuchParameterException.class)
	public void shouldNotInsertANamelessUser() throws NoSuchParameterException{
		UserDTO dto = DTOfactory.build(factory.buildNamelessUser());
		service.save(dto);			
	}
	
	@Test(expected = NoSuchParameterException.class)
	public void shouldNotInsertAUserWithNoEmail() throws NoSuchParameterException {		UserDTO dto = DTOfactory.build(factory.buildUserWithNoEmail());		service.save(dto);
	}
	
	
	@Test(expected = NoSuchParameterException.class)
	public void shouldNotInsertAUserWithNoPassword()  throws NoSuchParameterException{		UserDTO dto = DTOfactory.build(factory.buildUserWithNoPassword());		service.save(dto);
	}
	
	private void clean() {
		service.getRepository().deleteAll();
	}

}
