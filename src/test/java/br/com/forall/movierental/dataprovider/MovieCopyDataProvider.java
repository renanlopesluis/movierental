package br.com.forall.movierental.dataprovider;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.entity.Movie;
import br.com.forall.movierental.entity.MovieCopy;
import br.com.forall.movierental.repository.MovieCopyRepository;

@Component
public class MovieCopyDataProvider {
	
	private MovieCopyRepository repository;
	private MovieDataProvider movieDataProvider;
	
    @Autowired
    public MovieCopyDataProvider(MovieCopyRepository repository, MovieDataProvider movieDataProvider) {
        this.repository = repository;
        this.movieDataProvider = movieDataProvider;
    }

    public MovieCopy build() {
        MovieCopy movieCopy = MovieCopy.builder()
        		.available(Boolean.TRUE)
        		.movie(movieDataProvider.build())
        		.build();
        return repository.save(movieCopy);
    }
    
    public MovieCopy build(Boolean available) {
        MovieCopy movieCopy = MovieCopy.builder()
        		.available(available)
        		.movie(movieDataProvider.build())
        		.build();
        return repository.save(movieCopy);
    }
    
    public MovieCopy build(Movie movie, Boolean available) {
        MovieCopy movieCopy = MovieCopy.builder()
        		.available(available)
        		.movie(movie)
        		.build();
        return repository.save(movieCopy);
    }
    
    public Optional<MovieCopy> findById(Long id) {
    	return this.repository.findById(id);
    }
    public void deleteAll() {
		this.repository.deleteAll();
	}
}
