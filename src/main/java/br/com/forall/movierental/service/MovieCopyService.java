package br.com.forall.movierental.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.forall.movierental.entity.MovieCopy;
import br.com.forall.movierental.repository.MovieCopyRepository;

@Service
public class MovieCopyService {
		
	private MovieCopyRepository repository;
	
	@Autowired
	public MovieCopyService(MovieCopyRepository repository) {
		this.repository = repository;
	}
	
	public MovieCopy save(MovieCopy movieCopy) throws Exception {
		return this.repository.save(movieCopy);
	}
	
	public List<MovieCopy> findAvailablesByMovieId(Long movieId) throws EntityNotFoundException{
		return this.repository.findAvailablesByMovieId(movieId);
	}

}
