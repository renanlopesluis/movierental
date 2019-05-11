package br.com.forall.movierental.factory;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.api.V1.dto.RentalDTO;
import br.com.forall.movierental.entity.MovieCopy;
import br.com.forall.movierental.entity.Rental;
import br.com.forall.movierental.repository.MovieCopyRepository;
import br.com.forall.movierental.repository.UserRepository;

@Component
public class RentalFactory implements Factory<RentalDTO, Rental>{

	private UserRepository userRepository;
	private MovieCopyRepository movieCopyRepository;

    @Autowired
    public RentalFactory(UserRepository userRepository, MovieCopyRepository movieCopyRepository) {
        this.userRepository = userRepository;
        this.movieCopyRepository  = movieCopyRepository;
	}
    
    @Override
	public Rental build(RentalDTO dto) {
		 Rental rental = Rental.builder()
			.user(userRepository.findById(dto.getUserId())
            		.orElseThrow(EntityNotFoundException::new))
			.movieCopy(movieCopyRepository.findAvailablesByMovieId(dto.getMovieId()).
					stream().findFirst().orElseThrow(EntityNotFoundException::new))
			.rentalDate(LocalDateTime.now())	
            .foreseenGiveBackDate(LocalDateTime.now().plusDays(2))
            .executedGiveBackDate(null)
            .build();
		 	validateToRental(rental);    
	        rental.getMovieCopy().setAvailable(Boolean.FALSE);
	        movieCopyRepository.save(rental.getMovieCopy());
	        return rental;
	}
	
	private void validateToRental(Rental rental) {
		validateAvailabability(rental.getMovieCopy());
	}
	   
	private void validateAvailabability(MovieCopy movieCopy) throws IllegalStateException {
        if (!movieCopy.isAvailable()) {
            throw new IllegalArgumentException("Movie not available, please try it another day");
        }
	}

	
}