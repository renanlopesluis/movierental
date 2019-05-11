package br.com.forall.movierental.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.procedure.NoSuchParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.forall.movierental.api.V1.dto.MovieDTO;
import br.com.forall.movierental.entity.Movie;
import br.com.forall.movierental.factory.MovieFactory;
import br.com.forall.movierental.repository.MovieRepository;
import lombok.NonNull;

@Service
public class MovieService {
	
	private MovieRepository repository;
	private MovieFactory factory;
	
	@Autowired 
	public MovieService( MovieRepository repository, MovieFactory factory) {
		this.repository = repository;
		this.factory = factory;
	}
	
	public MovieRepository getRepository() {
		return this.repository;
	}

	public Movie save(MovieDTO dto) throws NoSuchParameterException {
		Movie movie = factory.build(dto);
		return this.repository.save(movie);
	}
	
	public Optional<Movie> findByTitle(@NonNull String title){
		return this.repository.findByTitle(title);
	}
	
	public List<Movie> findAllAvailable(){
		return this.repository.findAllAvailable();
	}
}
