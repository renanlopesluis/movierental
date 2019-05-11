package br.com.forall.movierental.factory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.api.V1.dto.RentalDTO;
import br.com.forall.movierental.entity.Rental;

@Component
public class RentalDTOFactory implements Factory<Rental, RentalDTO>{
	
	private ModelMapper modelMapper;
	
	@Autowired
    public RentalDTOFactory(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }
	    
	@Override
    public RentalDTO build(Rental rental) {
    	RentalDTO rentalDTO = modelMapper.map(rental, RentalDTO.class);
    	rentalDTO.setRentalId(rental.getId());
    	rentalDTO.setCopyId(rental.getMovieCopy().getId());
    	rentalDTO.setUserId(rental.getUser() != null ? rental.getUser().getId() : null);
    	rentalDTO.setUserName(rental.getUser() != null ? rental.getUser().getName() : null);
    	rentalDTO.setMovieTitle(rental.getMovieCopy() != null ? rental.getMovieCopy().getMovie().getTitle() : null);
    	rentalDTO.setMovieId(rental.getMovieCopy() != null ? rental.getMovieCopy().getMovie().getId() : null);
    	rentalDTO.setRentalDate(rental.getRentalDate());
    	rentalDTO.setForeseenGiveBackDate(rental.getForeseenGiveBackDate());
    	rentalDTO.setExecutedGiveBackDate(rental.getExecutedGiveBackDate());
        return rentalDTO;
    }


}
