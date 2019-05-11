package br.com.forall.movierental.factory;

import javax.persistence.EntityNotFoundException;

import org.hibernate.procedure.NoSuchParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.forall.movierental.api.V1.dto.MovieCopyDTO;
import br.com.forall.movierental.entity.MovieCopy;
import br.com.forall.movierental.repository.MovieRepository;

@Component
public class MovieCopyFactory implements Factory<MovieCopyDTO, MovieCopy>{
	
	private MovieRepository movieRepository;

    @Autowired
    public MovieCopyFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
	}
    
	@Override
	public MovieCopy build(MovieCopyDTO dto) throws NoSuchParameterException {
        MovieCopy movieCopy = MovieCopy.builder()
        		.id(dto.getMovieCopyId())
                .movie(movieRepository.findByTitle(dto.getMovieTitle())
                		.orElseThrow(EntityNotFoundException::new))
                .available(dto.getAvailable())
                .build();
        return movieCopy;
	}
}
