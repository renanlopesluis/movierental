package br.com.forall.movierental.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.entity.Movie;
import br.com.forall.movierental.repository.MovieRepository;

@Component
public class MovieDataProvider {
	
	private MovieRepository repository;
	private DirectorDataProvider directorDataProvider;
	
    @Autowired
    public MovieDataProvider(MovieRepository repository, DirectorDataProvider directorDataProvider) {
        this.repository = repository;
        this.directorDataProvider = directorDataProvider;
    }

    public Movie build() {
        Movie movie = Movie.builder()
        		.title("Pulp Fiction")
        		.director(directorDataProvider.build())
        		.build();
        return repository.save(movie);
    }
    
}
