package br.com.forall.movierental.serviceTest;

import javax.persistence.EntityNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.forall.movierental.api.V1.dto.RentalDTO;
import br.com.forall.movierental.dataprovider.MovieCopyDataProvider;
import br.com.forall.movierental.dataprovider.RentalDataProvider;
import br.com.forall.movierental.dataprovider.UserDataProvider;
import br.com.forall.movierental.entity.MovieCopy;
import br.com.forall.movierental.entity.Rental;
import br.com.forall.movierental.factory.RentalDTOFactory;
import br.com.forall.movierental.factorytest.RentalFactoryTest;
import br.com.forall.movierental.service.RentalService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RentalServiceTest {

	@Autowired
	private RentalService service;
		
	@Autowired
	private UserDataProvider userDataProvider;
		
	@Autowired
	private MovieCopyDataProvider movieCopyDataProvider;
	
	@Autowired
	private RentalDataProvider rentalDataProvider;
		
	@Autowired 
	private RentalDTOFactory DTOfactory;
	
	@Autowired
	private RentalFactoryTest factory;
		
	@Before
	public void init() {
		clean();
	}
	
	@Test
	public void shouldInsertARental() {
		Rental rental = this.rentalDataProvider.build()	;	
	
		Assert.assertNotNull(rental.getUser());
		Assert.assertNotNull(rental.getMovieCopy());
		Assert.assertNotNull(rental.getRentalDate());
		MovieCopy copy = movieCopyDataProvider.findById(rental.getMovieCopy().getId()).get();			
		Assert.assertFalse(copy.isAvailable());
		
	}

	@Test(expected = EntityNotFoundException.class)
	public void shouldNotInsertARentalWithNoAvailableMovieCopy() {
		RentalDTO dto = DTOfactory.build(factory.buildWithNoAvailableMovieCopy());
		this.service.save(dto);	
	}

	@Test(expected = Exception.class)
	public void shouldNotInsertAUserlessRental() {
		RentalDTO dto = DTOfactory.build(factory. buildUserless());				
		this.service.save(dto);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void shouldNotInsertARentalWithNoMovieCopy() {
		RentalDTO dto = DTOfactory.build(factory.buildWithNoAvailableMovieCopy());
		this.service.save(dto);			
	}
	
	@Test
	public void shouldFinalizeARental() {
		Rental rental = rentalDataProvider.build();
		RentalDTO dto = DTOfactory.build(rental);
				
		rental = this.service.finalize(dto);
		MovieCopy copy = movieCopyDataProvider.findById(rental.getMovieCopy().getId()).get();
		
		Assert.assertEquals(rental.getUser().getId(), dto.getUserId());
		Assert.assertEquals(rental.getMovieCopy().getId(), copy.getId());
		Assert.assertNotNull(rental.getExecutedGiveBackDate());
		Assert.assertTrue(copy.isAvailable());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotFinalizeARentalIfAlreadyFinalized() {
		Rental rental = rentalDataProvider.buildExecuted();
		RentalDTO dto = DTOfactory.build(rental);
		
		this.service.finalize(dto);
		
	}

	private void clean() {
		this.service.getRepository().deleteAll();
		this.userDataProvider.deleteAll();
		this.movieCopyDataProvider.deleteAll();
	}

}
