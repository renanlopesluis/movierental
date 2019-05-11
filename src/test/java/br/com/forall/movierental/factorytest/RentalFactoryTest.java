package br.com.forall.movierental.factorytest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.dataprovider.MovieCopyDataProvider;
import br.com.forall.movierental.dataprovider.UserDataProvider;
import br.com.forall.movierental.entity.Rental;
import br.com.forall.movierental.entity.User;

@Component
public class RentalFactoryTest {
	
	@Autowired
	private UserDataProvider userDataProvider;
		
	@Autowired
	private MovieCopyDataProvider movieCopyDataProvider;
	
	public Rental buildWithNoAvailableMovieCopy() {
		 Rental rental = Rental.builder()
				.user(userDataProvider.build())
				.movieCopy(movieCopyDataProvider.build(false))
				.rentalDate(LocalDateTime.now())
				.foreseenGiveBackDate(LocalDateTime.now().plusDays(2)).build();
		        return rental;
	}
	
	public Rental buildUserless() {
		 Rental rental = Rental.builder()
				.user(new User())
				.movieCopy(movieCopyDataProvider.build(true))
				.rentalDate(LocalDateTime.now())
				.foreseenGiveBackDate(LocalDateTime.now().plusDays(2)).build();
		        return rental;
	}
}
