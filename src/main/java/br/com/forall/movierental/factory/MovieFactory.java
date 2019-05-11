package br.com.forall.movierental.factory;

import javax.persistence.EntityNotFoundException;

import org.hibernate.procedure.NoSuchParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.api.V1.dto.MovieDTO;
import br.com.forall.movierental.entity.Movie;
import br.com.forall.movierental.repository.DirectorRepository;

@Component
public class MovieFactory implements Factory<MovieDTO, Movie>{
	
	private DirectorRepository directorRepository;

    @Autowired
    public MovieFactory(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
	}
    
	@Override
	public Movie build(MovieDTO dto) throws NoSuchParameterException {
        Movie movie = Movie.builder()
        		.id(dto.getMovieId())
        		.title(dto.getTitle())
                .director(directorRepository.findByName(dto.getDirectorName())
                		.orElseThrow(EntityNotFoundException::new))
                .build();
        validate(movie);
        return movie;
	}

	
 	private void validate(Movie movie) throws NoSuchParameterException {
        validateTitle(movie.getTitle());
    }

    private void validateTitle(String title) throws NoSuchParameterException {
        if (title == null) {
            throw new NoSuchParameterException("You should inform a movie title");
        }
	}
}
