package br.com.forall.movierental.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.forall.movierental.api.V1.dto.RentalDTO;
import br.com.forall.movierental.entity.Rental;
import br.com.forall.movierental.factory.RentalFactory;
import br.com.forall.movierental.repository.RentalRepository;

@Service
public class RentalService {

	private RentalRepository repository;
	private RentalFactory factory;
	
	@Autowired
	public RentalService( RentalRepository repository, RentalFactory factory) {
		this.repository = repository;
		this.factory = factory;
	}
	
	public RentalRepository getRepository() {
		return this.repository;
	}

	public Rental save(RentalDTO dto) throws IllegalArgumentException {
		Rental rental = factory.build(dto);
		return repository.save(rental);
	}
	
	public Rental finalize(RentalDTO dto) throws IllegalArgumentException{
		Rental rental = repository.findById(dto.getRentalId())
				.filter(r->r.getExecutedGiveBackDate() == null
				).orElseThrow(IllegalArgumentException :: new);
		 rental.setExecutedGiveBackDate(LocalDateTime.now());
		 rental.getMovieCopy().setAvailable(Boolean.TRUE);
		return repository.save(rental);
	}
}
