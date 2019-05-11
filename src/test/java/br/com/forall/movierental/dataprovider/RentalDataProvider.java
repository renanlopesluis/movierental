package br.com.forall.movierental.dataprovider;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.entity.Rental;
import br.com.forall.movierental.repository.RentalRepository;

@Component
public class RentalDataProvider {
	
	private RentalRepository repository;
	private MovieCopyDataProvider movieCopyDataProvider;
	private UserDataProvider userDataProvider;
	
    @Autowired
    public RentalDataProvider(RentalRepository repository, MovieCopyDataProvider movieCopyDataProvider,  UserDataProvider userDataProvider) {
        this.repository = repository;
        this.movieCopyDataProvider = movieCopyDataProvider;
        this.userDataProvider = userDataProvider;
    }

    public Rental build() {
        Rental rental = Rental.builder()
                .id(null)
        		.movieCopy(movieCopyDataProvider.build(false))
                .user(userDataProvider.build())
        		.rentalDate(LocalDateTime.now())
                .foreseenGiveBackDate(LocalDateTime.now().plusDays(2))
                .build();
        return repository.save(rental);
    }
    
    public Rental buildExecuted() {
        Rental rental = Rental.builder()
                .id(null)
        		.movieCopy(movieCopyDataProvider.build(false))
                .user(userDataProvider.build())
        		.rentalDate(LocalDateTime.now())
                .foreseenGiveBackDate(LocalDateTime.now().plusDays(2))
                .executedGiveBackDate(LocalDateTime.now())
                .build();
        return repository.save(rental);
    }
	
}
